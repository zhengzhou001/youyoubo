package com.chuxi.member.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.core.tools.BaseTools;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyCoupon;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.tcwy.distribute.controller.BaseController;
import com.tcwy.distribute.result.BaseResult;
import com.chuxi.config.BaseConfig;
import com.chuxi.member.service.IWX_MEMBERService;
import com.chuxi.order.service.IWX_ORDERService;
import com.chuxi.order.service.IWX_ORDER_PAY_BACKService;


/**
 * @Title: Controller
 * @Description: 微信支付
 * @author onlineGenerator
 * @date 2018-02-07 17:45:09
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/wxPay")
public class RESTWxPayController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RESTWxPayController.class);
	@Autowired
	private BaseConfig baseConfig; 

	@Autowired
	private IWX_MEMBERService WX_MEMBERService;
	@Autowired
	private IWX_ORDERService WX_ORDERService;
	@Autowired
	private IWX_ORDER_PAY_BACKService WX_ORDER_PAY_BACKService;


	/**
	 * 产生订单接口
	 * @param payType 支付类型
	 * @param id 支付信息id
	 * @return
	 * @throws WxPayException
	 */
	@RequestMapping(value = "/order",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult order(HttpServletRequest request,  @RequestBody Map map) throws WxPayException {
		BaseResult result = new BaseResult();

		try {
			List<Map> list =  WX_MEMBERService.selectWX_MEMBER(map);
			if (list==null||list.size()!=1) {
				code=-1;
				msg="用户数据异常";
				result.code=code;
				result.msg=msg;
				return result;
			}
			Map userMap = list.get(0);

			if (!StringUtils.equals(MapUtils.getString(userMap, "STATE"), "0")) {
				code=1;
				msg="用户已经付款";
				result.code=code;
				result.msg=msg;
				return result;
			}
			String tradeNo = BaseTools.getNextSeq();
			Integer fee = 1;
			String USER_TYPE=MapUtils.getString(userMap, "USER_TYPE");

			switch (USER_TYPE) {
			case "1":
				fee=1;
				break;
			case "2":
				fee=2;
				break;
			default:
				fee=0;
				break;
			}

			WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
			orderRequest.setSignType(WxPayConstants.SignType.MD5);
			orderRequest.setBody("会员办理费用");
			orderRequest.setOutTradeNo(tradeNo); //自己生成order_No
			orderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
			orderRequest.setTotalFee(fee);//直接分
			orderRequest.setOpenid(MapUtils.getString(map, "OPENID")); // 获取微信支付用户的openId
			orderRequest.setSpbillCreateIp(BaseTools.getIPAddress(request));
			orderRequest.setAttach("会员办理");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date now = new Date();
			Date afterDate = new Date(now .getTime() + 600000);
			orderRequest.setTimeStart(sdf.format(now));
			orderRequest.setTimeExpire(sdf.format(afterDate));
			WxPayService wxPayService = getWxPayService();
			WxPayMpOrderResult o = wxPayService.createOrder(orderRequest);
			//存储支付信息到数据库
			WX_ORDERService.insertWX_ORDER(ArrayUtils.toMap(new String[][]{
				{"SIGNTYPE",orderRequest.getSignType()},
				{"BODY",orderRequest.getBody()},
				{"OUTTRADENO",orderRequest.getOutTradeNo()},
				{"TRADETYPE",orderRequest.getTradeType()},
				{"TOTALFEE",String.valueOf(orderRequest.getTotalFee())},
				{"OPENID",orderRequest.getOpenid()},
				{"SPBILLCREATEIP",orderRequest.getSpbillCreateIp()},
				{"TIMESTART",orderRequest.getTimeStart()},
				{"TIMEEXPIRE",orderRequest.getTimeExpire()},
				{"GZHID",orderRequest.getAppid()},
				{"NOTIFYURL",orderRequest.getNotifyUrl()},
				{"APPID",o.getAppId()},
				{"TIMESTAMP",o.getTimeStamp()},
				{"NONCESTR",o.getNonceStr()},
				{"PACKAGEVALUE",o.getPackageValue()},
				{"SIGNTYPE_RET",o.getSignType()},
				{"PAYSIGN",o.getPaySign()},
			}));


			result.data =o;
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;

	}

	/**
	 * 支付回调通知处理
	 */
	@RequestMapping(value = "/notify",method = RequestMethod.POST)
	@ResponseBody
	public String  notify(@RequestBody String xmlData) throws WxPayException {
		final WxPayOrderNotifyResult notifyResult = this.getWxPayService().parseOrderNotifyResult(xmlData);
		try {
			int count = WX_ORDER_PAY_BACKService.selectWX_ORDER_PAY_BACKCount(ArrayUtils.toMap(new String[][]{
				{"OUT_TRADE_NO",notifyResult.getOutTradeNo()}
			}));
			if (count>0) {
				return WxPayNotifyResponse.success("成功");
			}
			
			List<WxPayOrderNotifyCoupon> list =notifyResult.getCouponList();
			WxPayOrderNotifyCoupon  coupon = new  WxPayOrderNotifyCoupon();
			if (list!=null&&list.size()>0) {
				coupon = list.get(0);
			}
			
			WX_ORDER_PAY_BACKService.insertWX_ORDER_PAY_BACK(ArrayUtils.toMap(new String[][]{
				{"RETURN_CODE",notifyResult.getReturnCode()},
				{"RETURN_MSG",notifyResult.getReturnMsg()},
				{"APPID",notifyResult.getAppid()},
				{"MCH_ID",notifyResult.getMchId()},
				{"DEVICE_INFO",notifyResult.getDeviceInfo()},
				{"NONCE_STR",notifyResult.getNonceStr()},
				{"SIGN",notifyResult.getSign()},
				//{"sign_type",notifyResult.gets},
				{"RESULT_CODE",notifyResult.getResultCode()},
				{"ERR_CODE",notifyResult.getErrCode()},
				{"ERR_CODE_DES",notifyResult.getErrCodeDes()},
				{"OPENID",notifyResult.getOpenid()},
				{"IS_SUBSCRIBE",notifyResult.getIsSubscribe()},
				{"TRADE_TYPE",notifyResult.getTradeType()},
				{"BANK_TYPE",notifyResult.getBankType()},
				{"TOTAL_FEE",String.valueOf(notifyResult.getTotalFee())},
				{"SETTLEMENT_TOTAL_FEE",String.valueOf(notifyResult.getSettlementTotalFee())},
				{"FEE_TYPE",notifyResult.getFeeType()},
				{"CASH_FEE",String.valueOf(notifyResult.getCashFee())},
				{"CASH_FEE_TYPE",notifyResult.getCashFeeType()},
				{"COUPON_FEE",String.valueOf(notifyResult.getCouponFee())},
				{"COUPON_COUNT",String.valueOf(notifyResult.getCouponCount())},
				{"COUPON_TYPE",String.valueOf(notifyResult.getCouponCount())},
				{"COUPON_ID_0",coupon.getCouponId()},
				{"COUPON_TYPE_0",String.valueOf(coupon.getCouponType())},
				{"COUPON_FEE_0",String.valueOf(coupon.getCouponFee())},
				{"TRANSACTION_ID",notifyResult.getTransactionId()},
				{"OUT_TRADE_NO",notifyResult.getOutTradeNo()},
				{"ATTACH",notifyResult.getAttach()},
				{"TIME_END",notifyResult.getTimeEnd()}
			}));
		} catch (Exception e) {
			logger.error(e);
			return WxPayNotifyResponse.fail("数据保存异常");
		}


		return WxPayNotifyResponse.success("成功");
	}



	/**
	 * 加载配置文件，生成微信payservice对象
	 * @return
	 */
	private WxPayService getWxPayService() {
		WxPayConfig payConfig = new WxPayConfig();
		payConfig.setAppId(baseConfig.getAppID());
		payConfig.setMchId(baseConfig.getMchId());
		payConfig.setMchKey(baseConfig.getMchKey());
		payConfig.setNotifyUrl(baseConfig.getNotifyUrl());
		WxPayService wxPayService = new WxPayServiceImpl();
		wxPayService.setConfig(payConfig);
		return wxPayService;
	}

}

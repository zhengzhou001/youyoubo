package com.youyoubo.wx.member.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.core.tools.BaseTools;
import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.tcwy.distribute.controller.BaseController;
import com.tcwy.distribute.result.BaseResult;
import com.youyoubo.wx.config.BaseConfig;


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
		String tradeNo = BaseTools.getNextSeq();
		Integer fee = 1;
		WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();

		orderRequest.setSignType(WxPayConstants.SignType.MD5);
		orderRequest.setBody("除夕网络");
		orderRequest.setOutTradeNo(tradeNo); //自己生成order_No
		orderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
		orderRequest.setTotalFee(fee);//直接分
		orderRequest.setOpenid(MapUtils.getString(map, "OPENID")); // 获取微信支付用户的openId
		orderRequest.setSpbillCreateIp(BaseTools.getIPAddress(request));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		Date afterDate = new Date(now .getTime() + 600000);
		orderRequest.setTimeStart(sdf.format(now));
		orderRequest.setTimeExpire(sdf.format(afterDate));

		WxPayService wxPayService = getWxPayService();
		System.out.println("AAAAAAAAAAAAAAAAAA");
		WxPayMpOrderResult o = wxPayService.createOrder(orderRequest);
		System.out.println("BBBBBBBBBBBBBBB");
		System.out.println(o.toString());
		// 存储支付信息到数据库
		result.data =o;
		return result;
	}

	/**
	 * 产生订单接口
	 * @param payType 支付类型
	 * @param id 支付信息id
	 * @return
	 * @throws WxPayException
	 */
	@RequestMapping(value = "/notify",method = RequestMethod.POST)
	@ResponseBody
	public BaseResult notify(@RequestBody Map map) throws WxPayException {
		BaseResult result = new BaseResult();
		System.out.println("=================");
		return result;
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

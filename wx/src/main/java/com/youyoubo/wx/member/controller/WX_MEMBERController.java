package com.youyoubo.wx.member.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.base.core.tools.BaseTools;
import com.tcwy.distribute.controller.BaseController;
import com.tcwy.distribute.result.BaseResult;
import com.youyoubo.wx.member.service.IWX_MEMBERService;
import com.youyoubo.wx.sms.service.ISMS_CODEService;
import com.youyoubo.wx.util.NewSmsUtil;

/**
 * <ol>
 * date:2019-08-08 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>会员Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Scope("prototype")
@RequestMapping(value="/member")
public class WX_MEMBERController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(WX_MEMBERController.class);
	@Autowired
	IWX_MEMBERService WX_MEMBERService;
	@Autowired
	ISMS_CODEService SMS_CODEService;

	@RequestMapping(value={"/insertWX_MEMBER"}, method={RequestMethod.POST})
	public BaseResult insertWX_MEMBER(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			//查看验证码是否正确

			List list = SMS_CODEService.selectSMS_CODE(ArrayUtils.toMap(new String[][]{
				{"CODE",MapUtils.getString(map, "YZM")},
				{"PHONE",MapUtils.getString(map, "PHONE")},
				{"STATE","1"},
				{"FLAG","1"},
			}));
			if (list==null||list.size()==0) {
				code=-1;
				msg="验证码已过期或不正确";
				result.code=code;
				result.msg=msg;
				return result;
			}
			for (int i = 0; i < list.size(); i++) {
				Map tmap = (Map) list.get(i);
				SMS_CODEService.updateSMS_CODE(ArrayUtils.toMap(new String[][]{
					{"ID",MapUtils.getString(tmap, "ID")},
					{"STATE_NEW","0"},
				}));
			}
			
			int count = WX_MEMBERService.selectWX_MEMBERCount(ArrayUtils.toMap(new String[][]{
					{"OPENID",MapUtils.getString(map, "OPENID")},
					{"GZHID",MapUtils.getString(map, "GZHID")},
				}));
			if (count>0) {
				code=-1;
				msg="已经是会员了，请勿重复办理";
				result.code=code;
				result.msg=msg;
				return result;
			}
			WX_MEMBERService.insertWX_MEMBER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}

	@RequestMapping(value={"/deleteWX_MEMBER"}, method={RequestMethod.POST})
	public BaseResult deleteWX_MEMBER(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_MEMBERService.deleteWX_MEMBER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}


	@RequestMapping(value={"/updateWX_MEMBER"}, method={RequestMethod.POST})
	public BaseResult updateWX_MEMBER(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_MEMBERService.updateWX_MEMBER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}

	@RequestMapping(value={"/selectWX_MEMBER"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectWX_MEMBER(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= WX_MEMBERService.selectWX_MEMBER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}


	@RequestMapping(value={"/selectWX_MEMBERCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectWX_MEMBERCount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= WX_MEMBERService.selectWX_MEMBERCount(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}

	//获取验证码
	@RequestMapping(value={"/getCode"}, method={RequestMethod.POST})
	public	BaseResult getCode(@RequestBody Map map){
		BaseResult result = new BaseResult<>();
		try{
			//发送验证码
			String valid_code = BaseTools.getRandomNumber(4);
			SendSmsResponse response = NewSmsUtil.sendSms(MapUtils.getString(map, "SIGN_NAME"), MapUtils.getString(map, "TPL_NAME"), MapUtils.getString(map, "PHONE"), "{\"code\":\""+valid_code +"\"}");
			if(response.getCode() != null && response.getCode().equals("OK")) {
				//请求成功
				SMS_CODEService.insertSMS_CODE(ArrayUtils.toMap(new String[][]{
					{"CODE",valid_code},
					{"STATE","1"},
					{"EXPIRE_COUNT",MapUtils.getString(map, "EXPIRE_COUNT")},
					{"PHONE",MapUtils.getString(map, "PHONE")},
				}));
			}else {
				code=-1;
				msg=response.getMessage();
			}

		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
}

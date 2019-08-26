package com.chuxi.sms.controller;

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
import com.chuxi.sms.service.ISMS_CODEService;
import com.chuxi.util.NewSmsUtil;
import com.tcwy.distribute.controller.BaseController;
import com.tcwy.distribute.result.BaseResult;

/**
 * <ol>
 * date:2019-08-09 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>短信验证码Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Scope("prototype")
@RequestMapping(value="/sms")
 public class SMS_CODEController extends BaseController {
 	private Logger logger = LoggerFactory.getLogger(SMS_CODEController.class);
 	@Autowired
	ISMS_CODEService SMS_CODEService;
	
 
	@RequestMapping(value={"/insertSMS_CODE"}, method={RequestMethod.POST})
	public BaseResult insertSMS_CODE(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			SMS_CODEService.insertSMS_CODE(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/deleteSMS_CODE"}, method={RequestMethod.POST})
	public BaseResult deleteSMS_CODE(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			SMS_CODEService.deleteSMS_CODE(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/updateSMS_CODE"}, method={RequestMethod.POST})
	public BaseResult updateSMS_CODE(@RequestBody Map map){
		BaseResult result = new BaseResult();
 		try{
			SMS_CODEService.updateSMS_CODE(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/selectSMS_CODE"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectSMS_CODE(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= SMS_CODEService.selectSMS_CODE(map);
 		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/selectSMS_CODECount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectSMS_CODECount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= SMS_CODEService.selectSMS_CODECount(map);
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
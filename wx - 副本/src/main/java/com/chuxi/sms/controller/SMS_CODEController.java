package com.chuxi.sms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tcwy.distribute.controller.BaseController;
import com.tcwy.distribute.result.BaseResult;
import com.chuxi.sms.service.ISMS_CODEService;

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
}
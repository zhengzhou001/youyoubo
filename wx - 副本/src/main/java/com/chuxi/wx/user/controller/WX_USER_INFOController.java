package com.chuxi.user.controller;

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
import com.chuxi.user.service.IWX_USER_INFOService;

/**
 * <ol>
 * date:2019-08-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户基本信息Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Scope("prototype")
@RequestMapping(value="/user")
 public class WX_USER_INFOController extends BaseController {
 	private Logger logger = LoggerFactory.getLogger(WX_USER_INFOController.class);
 	@Autowired
	IWX_USER_INFOService WX_USER_INFOService;
	
 
	@RequestMapping(value={"/insertWX_USER_INFO"}, method={RequestMethod.POST})
	public BaseResult insertWX_USER_INFO(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_USER_INFOService.insertWX_USER_INFO(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/deleteWX_USER_INFO"}, method={RequestMethod.POST})
	public BaseResult deleteWX_USER_INFO(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_USER_INFOService.deleteWX_USER_INFO(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/updateWX_USER_INFO"}, method={RequestMethod.POST})
	public BaseResult updateWX_USER_INFO(@RequestBody Map map){
		BaseResult result = new BaseResult();
 		try{
			WX_USER_INFOService.updateWX_USER_INFO(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/selectWX_USER_INFO"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectWX_USER_INFO(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= WX_USER_INFOService.selectWX_USER_INFO(map);
 		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/selectWX_USER_INFOCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectWX_USER_INFOCount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= WX_USER_INFOService.selectWX_USER_INFOCount(map);
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
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
import com.chuxi.user.service.IUSER_INFOService;

/**
 * <ol>
 * date:2019-08-23 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>Service实现类</li>
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
 public class USER_INFOController extends BaseController {
 	private Logger logger = LoggerFactory.getLogger(USER_INFOController.class);
 	@Autowired
	IUSER_INFOService USER_INFOService;
	
 
	@RequestMapping(value={"/insertUSER_INFO"}, method={RequestMethod.POST})
	public BaseResult insertUSER_INFO(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			USER_INFOService.insertUSER_INFO(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/deleteUSER_INFO"}, method={RequestMethod.POST})
	public BaseResult deleteUSER_INFO(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			USER_INFOService.deleteUSER_INFO(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/updateUSER_INFO"}, method={RequestMethod.POST})
	public BaseResult updateUSER_INFO(@RequestBody Map map){
		BaseResult result = new BaseResult();
 		try{
			USER_INFOService.updateUSER_INFO(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/selectUSER_INFO"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectUSER_INFO(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= USER_INFOService.selectUSER_INFO(map);
 		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/selectUSER_INFOCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectUSER_INFOCount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= USER_INFOService.selectUSER_INFOCount(map);
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
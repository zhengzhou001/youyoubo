package com.chuxi.dm.controller;

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
import com.chuxi.dm.service.IDM_CITYService;

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
@RequestMapping(value="/dm")
 public class DM_CITYController extends BaseController {
 	private Logger logger = LoggerFactory.getLogger(DM_CITYController.class);
 	@Autowired
	IDM_CITYService DM_CITYService;
	
 
	@RequestMapping(value={"/insertDM_CITY"}, method={RequestMethod.POST})
	public BaseResult insertDM_CITY(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			DM_CITYService.insertDM_CITY(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/deleteDM_CITY"}, method={RequestMethod.POST})
	public BaseResult deleteDM_CITY(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			DM_CITYService.deleteDM_CITY(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/updateDM_CITY"}, method={RequestMethod.POST})
	public BaseResult updateDM_CITY(@RequestBody Map map){
		BaseResult result = new BaseResult();
 		try{
			DM_CITYService.updateDM_CITY(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/selectDM_CITY"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectDM_CITY(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= DM_CITYService.selectDM_CITY(map);
 		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/selectDM_CITYCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectDM_CITYCount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= DM_CITYService.selectDM_CITYCount(map);
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
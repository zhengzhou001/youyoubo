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
import com.chuxi.dm.service.IDM_PROVINCEService;

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
 public class DM_PROVINCEController extends BaseController {
 	private Logger logger = LoggerFactory.getLogger(DM_PROVINCEController.class);
 	@Autowired
	IDM_PROVINCEService DM_PROVINCEService;
	
 
	@RequestMapping(value={"/insertDM_PROVINCE"}, method={RequestMethod.POST})
	public BaseResult insertDM_PROVINCE(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			DM_PROVINCEService.insertDM_PROVINCE(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/deleteDM_PROVINCE"}, method={RequestMethod.POST})
	public BaseResult deleteDM_PROVINCE(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			DM_PROVINCEService.deleteDM_PROVINCE(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/updateDM_PROVINCE"}, method={RequestMethod.POST})
	public BaseResult updateDM_PROVINCE(@RequestBody Map map){
		BaseResult result = new BaseResult();
 		try{
			DM_PROVINCEService.updateDM_PROVINCE(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/selectDM_PROVINCE"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectDM_PROVINCE(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= DM_PROVINCEService.selectDM_PROVINCE(map);
 		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/selectDM_PROVINCECount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectDM_PROVINCECount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= DM_PROVINCEService.selectDM_PROVINCECount(map);
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
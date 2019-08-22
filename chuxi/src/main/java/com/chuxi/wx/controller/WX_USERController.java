package com.chuxi.wx.controller;

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
import com.chuxi.wx.service.IWX_USERService;

/**
 * <ol>
 * date:2019-08-22 editor:dingshuangbo
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
@RequestMapping(value="/wx")
 public class WX_USERController extends BaseController {
 	private Logger logger = LoggerFactory.getLogger(WX_USERController.class);
 	@Autowired
	IWX_USERService WX_USERService;
	
 
	@RequestMapping(value={"/insertWX_USER"}, method={RequestMethod.POST})
	public BaseResult insertWX_USER(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_USERService.insertWX_USER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/deleteWX_USER"}, method={RequestMethod.POST})
	public BaseResult deleteWX_USER(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_USERService.deleteWX_USER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/updateWX_USER"}, method={RequestMethod.POST})
	public BaseResult updateWX_USER(@RequestBody Map map){
		BaseResult result = new BaseResult();
 		try{
			WX_USERService.updateWX_USER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/selectWX_USER"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectWX_USER(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= WX_USERService.selectWX_USER(map);
 		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/selectWX_USERCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectWX_USERCount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= WX_USERService.selectWX_USERCount(map);
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
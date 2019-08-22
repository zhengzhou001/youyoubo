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
import com.chuxi.wx.service.IWX_MSGService;

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
 public class WX_MSGController extends BaseController {
 	private Logger logger = LoggerFactory.getLogger(WX_MSGController.class);
 	@Autowired
	IWX_MSGService WX_MSGService;
	
 
	@RequestMapping(value={"/insertWX_MSG"}, method={RequestMethod.POST})
	public BaseResult insertWX_MSG(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_MSGService.insertWX_MSG(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/deleteWX_MSG"}, method={RequestMethod.POST})
	public BaseResult deleteWX_MSG(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_MSGService.deleteWX_MSG(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/updateWX_MSG"}, method={RequestMethod.POST})
	public BaseResult updateWX_MSG(@RequestBody Map map){
		BaseResult result = new BaseResult();
 		try{
			WX_MSGService.updateWX_MSG(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/selectWX_MSG"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectWX_MSG(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= WX_MSGService.selectWX_MSG(map);
 		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/selectWX_MSGCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectWX_MSGCount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= WX_MSGService.selectWX_MSGCount(map);
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
package com.chuxi.order.controller;

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
import com.chuxi.order.service.IWX_ORDERService;

/**
 * <ol>
 * date:2019-08-21 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>微信订单Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Scope("prototype")
@RequestMapping(value="/order")
 public class WX_ORDERController extends BaseController {
 	private Logger logger = LoggerFactory.getLogger(WX_ORDERController.class);
 	@Autowired
	IWX_ORDERService WX_ORDERService;
	
 
	@RequestMapping(value={"/insertWX_ORDER"}, method={RequestMethod.POST})
	public BaseResult insertWX_ORDER(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_ORDERService.insertWX_ORDER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/deleteWX_ORDER"}, method={RequestMethod.POST})
	public BaseResult deleteWX_ORDER(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_ORDERService.deleteWX_ORDER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/updateWX_ORDER"}, method={RequestMethod.POST})
	public BaseResult updateWX_ORDER(@RequestBody Map map){
		BaseResult result = new BaseResult();
 		try{
			WX_ORDERService.updateWX_ORDER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/selectWX_ORDER"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectWX_ORDER(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= WX_ORDERService.selectWX_ORDER(map);
 		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/selectWX_ORDERCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectWX_ORDERCount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= WX_ORDERService.selectWX_ORDERCount(map);
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
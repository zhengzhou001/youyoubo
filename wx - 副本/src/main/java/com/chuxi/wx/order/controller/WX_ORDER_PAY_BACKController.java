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
import com.chuxi.order.service.IWX_ORDER_PAY_BACKService;

/**
 * <ol>
 * date:2019-08-21 editor:dingshuangbo
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
@RequestMapping(value="/order")
 public class WX_ORDER_PAY_BACKController extends BaseController {
 	private Logger logger = LoggerFactory.getLogger(WX_ORDER_PAY_BACKController.class);
 	@Autowired
	IWX_ORDER_PAY_BACKService WX_ORDER_PAY_BACKService;
	
 
	@RequestMapping(value={"/insertWX_ORDER_PAY_BACK"}, method={RequestMethod.POST})
	public BaseResult insertWX_ORDER_PAY_BACK(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_ORDER_PAY_BACKService.insertWX_ORDER_PAY_BACK(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/deleteWX_ORDER_PAY_BACK"}, method={RequestMethod.POST})
	public BaseResult deleteWX_ORDER_PAY_BACK(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_ORDER_PAY_BACKService.deleteWX_ORDER_PAY_BACK(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/updateWX_ORDER_PAY_BACK"}, method={RequestMethod.POST})
	public BaseResult updateWX_ORDER_PAY_BACK(@RequestBody Map map){
		BaseResult result = new BaseResult();
 		try{
			WX_ORDER_PAY_BACKService.updateWX_ORDER_PAY_BACK(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/selectWX_ORDER_PAY_BACK"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectWX_ORDER_PAY_BACK(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= WX_ORDER_PAY_BACKService.selectWX_ORDER_PAY_BACK(map);
 		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/selectWX_ORDER_PAY_BACKCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectWX_ORDER_PAY_BACKCount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= WX_ORDER_PAY_BACKService.selectWX_ORDER_PAY_BACKCount(map);
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
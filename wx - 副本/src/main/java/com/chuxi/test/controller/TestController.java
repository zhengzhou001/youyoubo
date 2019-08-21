package com.chuxi.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.service.IBaseService;
import com.tcwy.distribute.controller.BaseController;
import com.tcwy.distribute.result.BaseResult;

@Controller
@RequestMapping("/test")
@Scope("prototype")
public class TestController extends BaseController{
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired(required=true)
	private IBaseService baseService;
	
	@RequestMapping(value={"/select"}  )
	@ResponseBody
	public BaseResult select( @RequestBody Map paramMap){
		BaseResult result = new BaseResult();
		try{
			Map map = new HashMap();
 			map.put("name", MapUtils.getString(paramMap, "name", ""));
			result.data=baseService.select("base.testSelect", map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	@RequestMapping(value={"/selectA"}  )
	@ResponseBody
	public BaseResult selectA( ){
		BaseResult result = new BaseResult();
		try{
			Map map = new HashMap();
 		 
			result.data=baseService.select("base.testSelect", map);
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

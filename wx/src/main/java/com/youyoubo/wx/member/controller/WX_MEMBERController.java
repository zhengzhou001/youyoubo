package com.youyoubo.wx.member.controller;

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
import com.youyoubo.wx.member.service.IWX_MEMBERService;

/**
 * <ol>
 * date:2019-08-08 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>会员Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Scope("prototype")
@RequestMapping(value="/member")
 public class WX_MEMBERController extends BaseController {
 	private Logger logger = LoggerFactory.getLogger(WX_MEMBERController.class);
 	@Autowired
	IWX_MEMBERService WX_MEMBERService;
	
 
	@RequestMapping(value={"/insertWX_MEMBER"}, method={RequestMethod.POST})
	public BaseResult insertWX_MEMBER(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_MEMBERService.insertWX_MEMBER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/deleteWX_MEMBER"}, method={RequestMethod.POST})
	public BaseResult deleteWX_MEMBER(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_MEMBERService.deleteWX_MEMBER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/updateWX_MEMBER"}, method={RequestMethod.POST})
	public BaseResult updateWX_MEMBER(@RequestBody Map map){
		BaseResult result = new BaseResult();
 		try{
			WX_MEMBERService.updateWX_MEMBER(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/selectWX_MEMBER"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectWX_MEMBER(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= WX_MEMBERService.selectWX_MEMBER(map);
 		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/selectWX_MEMBERCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectWX_MEMBERCount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= WX_MEMBERService.selectWX_MEMBERCount(map);
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
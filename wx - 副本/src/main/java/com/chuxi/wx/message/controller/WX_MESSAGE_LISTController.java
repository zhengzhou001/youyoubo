package com.chuxi.message.controller;

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
import com.chuxi.message.service.IWX_MESSAGE_LISTService;

/**
 * <ol>
 * date:2019-08-02 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>微信消息记录Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Scope("prototype")
@RequestMapping(value="/message")
public class WX_MESSAGE_LISTController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(WX_MESSAGE_LISTController.class);
	@Autowired
	IWX_MESSAGE_LISTService WX_MESSAGE_LISTService;


	@RequestMapping(value={"/insertWX_MESSAGE_LIST"}, method={RequestMethod.POST})
	public BaseResult insertWX_MESSAGE_LIST(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_MESSAGE_LISTService.insertWX_MESSAGE_LIST(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}

	@RequestMapping(value={"/deleteWX_MESSAGE_LIST"}, method={RequestMethod.POST})
	public BaseResult deleteWX_MESSAGE_LIST(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_MESSAGE_LISTService.deleteWX_MESSAGE_LIST(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}


	@RequestMapping(value={"/updateWX_MESSAGE_LIST"}, method={RequestMethod.POST})
	public BaseResult updateWX_MESSAGE_LIST(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			WX_MESSAGE_LISTService.updateWX_MESSAGE_LIST(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}

	@RequestMapping(value={"/selectWX_MESSAGE_LIST"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectWX_MESSAGE_LIST(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= WX_MESSAGE_LISTService.selectWX_MESSAGE_LIST(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}


	@RequestMapping(value={"/selectWX_MESSAGE_LISTCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectWX_MESSAGE_LISTCount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= WX_MESSAGE_LISTService.selectWX_MESSAGE_LISTCount(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}



	//注册用户页面 初始化
	@RequestMapping(value={"/init"}, method={RequestMethod.POST})
	public	BaseResult init(@RequestBody Map map){
		BaseResult result = new BaseResult<>();
		try{
			//先查询用户是否已经是会员
			
			//1 获取openid
			
			
			
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
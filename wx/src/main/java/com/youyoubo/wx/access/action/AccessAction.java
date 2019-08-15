package com.youyoubo.wx.access.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.youyoubo.wx.access.service.IMessageService;
import com.youyoubo.wx.config.BaseConfig;
import com.youyoubo.wx.util.SignUtil;

@Controller
@RequestMapping("/")
@Scope("prototype")
public class AccessAction {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IMessageService messageService;
	@Autowired 
	private BaseConfig baseConfig;
	

	@RequestMapping(value={"/access"}, method={RequestMethod.GET})
	@ResponseBody
	public String doGet(String signature,String timestamp,String nonce,String echostr){
		//微信接口验证
 		if (SignUtil.checkSignature(baseConfig.getToken(),signature, timestamp, nonce)) {
			return echostr;
		} 
		return "";
	}
	@RequestMapping(value={"/access"}, method={RequestMethod.POST})
	@ResponseBody
	public String doPost(HttpServletRequest request){
		//处理响应微信消息
		String respMessage ="";
		try {
			respMessage = messageService.processRequest(request);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return respMessage;
	}
	
	
	public static void main(String[] args) {
		 System.out.println(1);
		 
	}

}

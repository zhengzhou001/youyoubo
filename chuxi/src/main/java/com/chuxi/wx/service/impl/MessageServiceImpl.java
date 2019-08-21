package com.chuxi.wx.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuxi.config.BaseConfig;
import com.chuxi.wx.service.IMessageService;

import me.chanjar.weixin.mp.api.WxMpService;
@Service
public class MessageServiceImpl implements IMessageService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BaseConfig baseConfig;
 
	@Autowired
    private WxMpService wxMpService;

	public  String processRequest(HttpServletRequest request) throws Exception {
		String respMessage = null;
 		 
		return respMessage;
	}
	
	 

}

package com.chuxi.wx.service;

import javax.servlet.http.HttpServletRequest;

public interface IMessageService {
	public  String processRequest(HttpServletRequest request) throws Exception;
}

package com.youyoubo.wx.access.mapper;

import com.youyoubo.wx.access.entity.WxMessageListEntity;

 
public interface WxMessageListMapper {
	/**
	 *  微信消息日志保存
	 *  @param WxMessageListEntity 消息日志实体类
  	 */
	public void insertWxMessageList(WxMessageListEntity wxMessageListEntity);
	
	 
}
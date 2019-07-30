package com.youyoubo.wx.access.mapper;

import com.youyoubo.wx.access.entity.WxMessageListEntity;
import com.youyoubo.wx.access.entity.WxUserListEntity;

 
public interface WxUserListMapper {
	/**
	 *  粉丝关注
	 *  @param WxUserListEntity 粉丝实体类
  	 */
	public void insertWxUserList(WxUserListEntity wxUserListEntity);
	
	/**
	 *  粉丝取消关注
	 *  @param WxUserListEntity 粉丝实体类
  	 */
	public void deleteWxUserList(WxUserListEntity wxUserListEntity);
	
	/**
	 *  获取数量
	 *  @param WxUserListEntity 粉丝实体类
  	 */
	public int selectWxUserListCount(WxUserListEntity wxUserListEntity);
	 
}
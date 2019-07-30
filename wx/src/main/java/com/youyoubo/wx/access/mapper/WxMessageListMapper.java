package com.youyoubo.wx.access.mapper;

import com.youyoubo.wx.access.entity.WxMessageListEntity;

 
public interface WxMessageListMapper {
	/**
	 * 增加海关发票导入明细记录
	 * @param HgxtFpImportMxEntity 海关发票导入明细实体对象
  	 */
	public void insertWxMessageList(WxMessageListEntity wxMessageListEntity);
	
	 
}
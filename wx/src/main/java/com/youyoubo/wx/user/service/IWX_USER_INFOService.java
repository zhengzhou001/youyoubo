package com.youyoubo.wx.user.service;

import java.util.List;
import java.util.Map;
import com.base.service.IBaseService;

/**
 * <ol>
 * date:2019-08-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户基本信息Service接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface IWX_USER_INFOService extends IBaseService{
	 
	public void insertWX_USER_INFO(Map map) throws Exception;
	
	public void deleteWX_USER_INFO(Map map) throws Exception;
	
	public void updateWX_USER_INFO(Map map) throws Exception;
	 
	public	List<Map> selectWX_USER_INFO(Map map) throws Exception;
	
	public	int selectWX_USER_INFOCount(Map map);
}
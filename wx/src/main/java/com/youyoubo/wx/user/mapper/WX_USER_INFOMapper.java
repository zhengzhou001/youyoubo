package com.youyoubo.wx.user.mapper;

import java.util.List;
import java.util.Map;


/**
 * <ol>
 * date:2019-08-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户基本信息Mapper接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface WX_USER_INFOMapper {
	public void insertWX_USER_INFO(Map map);
	
	public void deleteWX_USER_INFO(Map map);
	
	public void updateWX_USER_INFO(Map map);
	
	public	List<Map> selectWX_USER_INFO(Map map);
	
	public	int selectWX_USER_INFOCount(Map map);
}
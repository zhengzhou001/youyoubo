package com.chuxi.wx.mapper;

import java.util.List;
import java.util.Map;


/**
 * <ol>
 * date:2019-08-22 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>Mapper接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface WX_MSGMapper {
	public void insertWX_MSG(Map map);
	
	public void deleteWX_MSG(Map map);
	
	public void updateWX_MSG(Map map);
	
	public	List<Map> selectWX_MSG(Map map);
	
	public	int selectWX_MSGCount(Map map);
}
package com.youyoubo.wx.message.mapper;

import java.util.List;
import java.util.Map;


/**
 * <ol>
 * date:2019-08-02 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>微信消息记录Mapper接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface WX_MESSAGE_LISTMapper {
	public void insertWX_MESSAGE_LIST(Map map);
	
	public void deleteWX_MESSAGE_LIST(Map map);
	
	public void updateWX_MESSAGE_LIST(Map map);
	
	public	List<Map> selectWX_MESSAGE_LIST(Map map);
	
	public	int selectWX_MESSAGE_LISTCount(Map map);
}
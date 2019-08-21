package com.chuxi.message.service;

import java.util.List;
import java.util.Map;
import com.base.service.IBaseService;

/**
 * <ol>
 * date:2019-08-02 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>微信消息记录Service接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface IWX_MESSAGE_LISTService extends IBaseService{
	 
	public void insertWX_MESSAGE_LIST(Map map) throws Exception;
	
	public void deleteWX_MESSAGE_LIST(Map map) throws Exception;
	
	public void updateWX_MESSAGE_LIST(Map map) throws Exception;
	 
	public	List<Map> selectWX_MESSAGE_LIST(Map map) throws Exception;
	
	public	int selectWX_MESSAGE_LISTCount(Map map);
}
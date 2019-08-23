package com.chuxi.user.service;

import java.util.List;
import java.util.Map;
import com.base.service.IBaseService;

/**
 * <ol>
 * date:2019-08-23 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>Service接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface IUSER_INFOService extends IBaseService{
	 
	public void insertUSER_INFO(Map map) throws Exception;
	
	public void deleteUSER_INFO(Map map) throws Exception;
	
	public void updateUSER_INFO(Map map) throws Exception;
	 
	public	List<Map> selectUSER_INFO(Map map) throws Exception;
	
	public	int selectUSER_INFOCount(Map map);
}
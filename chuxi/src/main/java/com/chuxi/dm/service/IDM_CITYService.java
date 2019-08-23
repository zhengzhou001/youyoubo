package com.chuxi.dm.service;

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
public interface IDM_CITYService extends IBaseService{
	 
	public void insertDM_CITY(Map map) throws Exception;
	
	public void deleteDM_CITY(Map map) throws Exception;
	
	public void updateDM_CITY(Map map) throws Exception;
	 
	public	List<Map> selectDM_CITY(Map map) throws Exception;
	
	public	int selectDM_CITYCount(Map map);
}
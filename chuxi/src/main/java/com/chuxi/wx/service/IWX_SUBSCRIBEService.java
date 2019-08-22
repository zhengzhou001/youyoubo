package com.chuxi.wx.service;

import java.util.List;
import java.util.Map;
import com.base.service.IBaseService;

/**
 * <ol>
 * date:2019-08-22 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>Service接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface IWX_SUBSCRIBEService extends IBaseService{
	 
	public void insertWX_SUBSCRIBE(Map map) throws Exception;
	
	public void deleteWX_SUBSCRIBE(Map map) throws Exception;
	
	public void updateWX_SUBSCRIBE(Map map) throws Exception;
	 
	public	List<Map> selectWX_SUBSCRIBE(Map map) throws Exception;
	
	public	int selectWX_SUBSCRIBECount(Map map);
}
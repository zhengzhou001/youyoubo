package com.chuxi.order.service;

import java.util.List;
import java.util.Map;
import com.base.service.IBaseService;

/**
 * <ol>
 * date:2019-08-21 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>Service接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface IWX_ORDER_PAY_BACKService extends IBaseService{
	 
	public void insertWX_ORDER_PAY_BACK(Map map) throws Exception;
	
	public void deleteWX_ORDER_PAY_BACK(Map map) throws Exception;
	
	public void updateWX_ORDER_PAY_BACK(Map map) throws Exception;
	 
	public	List<Map> selectWX_ORDER_PAY_BACK(Map map) throws Exception;
	
	public	int selectWX_ORDER_PAY_BACKCount(Map map);
}
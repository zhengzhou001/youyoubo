package com.chuxi.sms.service;

import java.util.List;
import java.util.Map;
import com.base.service.IBaseService;

/**
 * <ol>
 * date:2019-08-09 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>短信验证码Service接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface ISMS_CODEService extends IBaseService{
	 
	public void insertSMS_CODE(Map map) throws Exception;
	
	public void deleteSMS_CODE(Map map) throws Exception;
	
	public void updateSMS_CODE(Map map) throws Exception;
	 
	public	List<Map> selectSMS_CODE(Map map) throws Exception;
	
	public	int selectSMS_CODECount(Map map);
}
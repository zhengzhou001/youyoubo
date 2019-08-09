package com.youyoubo.wx.sms.mapper;

import java.util.List;
import java.util.Map;


/**
 * <ol>
 * date:2019-08-09 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>短信验证码Mapper接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface SMS_CODEMapper {
	public void insertSMS_CODE(Map map);
	
	public void deleteSMS_CODE(Map map);
	
	public void updateSMS_CODE(Map map);
	
	public	List<Map> selectSMS_CODE(Map map);
	
	public	int selectSMS_CODECount(Map map);
}
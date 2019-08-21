package com.chuxi.sms.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.chuxi.sms.service.ISMS_CODEService;
import com.chuxi.sms.mapper.SMS_CODEMapper;

/**
 * <ol>
 * date:2019-08-09 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>短信验证码Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class SMS_CODEServiceImpl extends BaseServiceImpl implements ISMS_CODEService {
	@Autowired
	SMS_CODEMapper SMS_CODEMapper;
	
	public void insertSMS_CODE(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		SMS_CODEMapper.insertSMS_CODE(map);
	}
	
	public void deleteSMS_CODE(Map map) throws Exception{
		SMS_CODEMapper.deleteSMS_CODE(map);
	}
	
	public void updateSMS_CODE(Map map) throws Exception{
		SMS_CODEMapper.updateSMS_CODE(map);
	}
	
	public	List<Map> selectSMS_CODE(Map map) throws Exception{
		return SMS_CODEMapper.selectSMS_CODE(map);
	}
	
	public	int selectSMS_CODECount(Map map){
		return SMS_CODEMapper.selectSMS_CODECount(map);
	}
}
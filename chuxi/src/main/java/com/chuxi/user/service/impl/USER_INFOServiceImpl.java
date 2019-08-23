package com.chuxi.user.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.chuxi.user.service.IUSER_INFOService;
import com.chuxi.user.mapper.USER_INFOMapper;

/**
 * <ol>
 * date:2019-08-23 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class USER_INFOServiceImpl extends BaseServiceImpl implements IUSER_INFOService {
	@Autowired
	USER_INFOMapper USER_INFOMapper;
	
	public void insertUSER_INFO(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		USER_INFOMapper.insertUSER_INFO(map);
	}
	
	public void deleteUSER_INFO(Map map) throws Exception{
		USER_INFOMapper.deleteUSER_INFO(map);
	}
	
	public void updateUSER_INFO(Map map) throws Exception{
		USER_INFOMapper.updateUSER_INFO(map);
	}
	
	public	List<Map> selectUSER_INFO(Map map) throws Exception{
		return USER_INFOMapper.selectUSER_INFO(map);
	}
	
	public	int selectUSER_INFOCount(Map map){
		return USER_INFOMapper.selectUSER_INFOCount(map);
	}
}
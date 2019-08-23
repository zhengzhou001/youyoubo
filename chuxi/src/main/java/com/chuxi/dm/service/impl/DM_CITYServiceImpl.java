package com.chuxi.dm.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.chuxi.dm.service.IDM_CITYService;
import com.chuxi.dm.mapper.DM_CITYMapper;

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
public class DM_CITYServiceImpl extends BaseServiceImpl implements IDM_CITYService {
	@Autowired
	DM_CITYMapper DM_CITYMapper;
	
	public void insertDM_CITY(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		DM_CITYMapper.insertDM_CITY(map);
	}
	
	public void deleteDM_CITY(Map map) throws Exception{
		DM_CITYMapper.deleteDM_CITY(map);
	}
	
	public void updateDM_CITY(Map map) throws Exception{
		DM_CITYMapper.updateDM_CITY(map);
	}
	
	public	List<Map> selectDM_CITY(Map map) throws Exception{
		return DM_CITYMapper.selectDM_CITY(map);
	}
	
	public	int selectDM_CITYCount(Map map){
		return DM_CITYMapper.selectDM_CITYCount(map);
	}
}
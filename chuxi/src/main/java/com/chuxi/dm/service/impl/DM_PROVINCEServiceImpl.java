package com.chuxi.dm.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.chuxi.dm.service.IDM_PROVINCEService;
import com.chuxi.dm.mapper.DM_PROVINCEMapper;

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
public class DM_PROVINCEServiceImpl extends BaseServiceImpl implements IDM_PROVINCEService {
	@Autowired
	DM_PROVINCEMapper DM_PROVINCEMapper;
	
	public void insertDM_PROVINCE(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		DM_PROVINCEMapper.insertDM_PROVINCE(map);
	}
	
	public void deleteDM_PROVINCE(Map map) throws Exception{
		DM_PROVINCEMapper.deleteDM_PROVINCE(map);
	}
	
	public void updateDM_PROVINCE(Map map) throws Exception{
		DM_PROVINCEMapper.updateDM_PROVINCE(map);
	}
	
	public	List<Map> selectDM_PROVINCE(Map map) throws Exception{
		return DM_PROVINCEMapper.selectDM_PROVINCE(map);
	}
	
	public	int selectDM_PROVINCECount(Map map){
		return DM_PROVINCEMapper.selectDM_PROVINCECount(map);
	}
}
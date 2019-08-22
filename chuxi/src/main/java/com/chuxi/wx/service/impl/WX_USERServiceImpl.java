package com.chuxi.wx.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.chuxi.wx.service.IWX_USERService;
import com.chuxi.wx.mapper.WX_USERMapper;

/**
 * <ol>
 * date:2019-08-22 editor:dingshuangbo
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
public class WX_USERServiceImpl extends BaseServiceImpl implements IWX_USERService {
	@Autowired
	WX_USERMapper WX_USERMapper;
	
	public void insertWX_USER(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		WX_USERMapper.insertWX_USER(map);
	}
	
	public void deleteWX_USER(Map map) throws Exception{
		WX_USERMapper.deleteWX_USER(map);
	}
	
	public void updateWX_USER(Map map) throws Exception{
		WX_USERMapper.updateWX_USER(map);
	}
	
	public	List<Map> selectWX_USER(Map map) throws Exception{
		return WX_USERMapper.selectWX_USER(map);
	}
	
	public	int selectWX_USERCount(Map map){
		return WX_USERMapper.selectWX_USERCount(map);
	}
}
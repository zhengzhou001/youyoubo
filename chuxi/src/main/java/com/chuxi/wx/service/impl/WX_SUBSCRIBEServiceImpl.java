package com.chuxi.wx.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.chuxi.wx.service.IWX_SUBSCRIBEService;
import com.chuxi.wx.mapper.WX_SUBSCRIBEMapper;

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
public class WX_SUBSCRIBEServiceImpl extends BaseServiceImpl implements IWX_SUBSCRIBEService {
	@Autowired
	WX_SUBSCRIBEMapper WX_SUBSCRIBEMapper;
	
	public void insertWX_SUBSCRIBE(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		WX_SUBSCRIBEMapper.insertWX_SUBSCRIBE(map);
	}
	
	public void deleteWX_SUBSCRIBE(Map map) throws Exception{
		WX_SUBSCRIBEMapper.deleteWX_SUBSCRIBE(map);
	}
	
	public void updateWX_SUBSCRIBE(Map map) throws Exception{
		WX_SUBSCRIBEMapper.updateWX_SUBSCRIBE(map);
	}
	
	public	List<Map> selectWX_SUBSCRIBE(Map map) throws Exception{
		return WX_SUBSCRIBEMapper.selectWX_SUBSCRIBE(map);
	}
	
	public	int selectWX_SUBSCRIBECount(Map map){
		return WX_SUBSCRIBEMapper.selectWX_SUBSCRIBECount(map);
	}
}
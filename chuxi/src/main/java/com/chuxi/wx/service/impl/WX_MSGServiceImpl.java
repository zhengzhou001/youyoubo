package com.chuxi.wx.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.chuxi.wx.service.IWX_MSGService;
import com.chuxi.wx.mapper.WX_MSGMapper;

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
public class WX_MSGServiceImpl extends BaseServiceImpl implements IWX_MSGService {
	@Autowired
	WX_MSGMapper WX_MSGMapper;
	
	public void insertWX_MSG(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		WX_MSGMapper.insertWX_MSG(map);
	}
	
	public void deleteWX_MSG(Map map) throws Exception{
		WX_MSGMapper.deleteWX_MSG(map);
	}
	
	public void updateWX_MSG(Map map) throws Exception{
		WX_MSGMapper.updateWX_MSG(map);
	}
	
	public	List<Map> selectWX_MSG(Map map) throws Exception{
		return WX_MSGMapper.selectWX_MSG(map);
	}
	
	public	int selectWX_MSGCount(Map map){
		return WX_MSGMapper.selectWX_MSGCount(map);
	}
}
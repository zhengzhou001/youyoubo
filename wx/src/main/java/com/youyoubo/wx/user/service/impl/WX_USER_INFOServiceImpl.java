package com.youyoubo.wx.user.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.youyoubo.wx.user.service.IWX_USER_INFOService;
import com.youyoubo.wx.user.mapper.WX_USER_INFOMapper;

/**
 * <ol>
 * date:2019-08-01 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>用户基本信息Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class WX_USER_INFOServiceImpl extends BaseServiceImpl implements IWX_USER_INFOService {
	@Autowired
	WX_USER_INFOMapper WX_USER_INFOMapper;
	
	public void insertWX_USER_INFO(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		WX_USER_INFOMapper.insertWX_USER_INFO(map);
	}
	
	public void deleteWX_USER_INFO(Map map) throws Exception{
		WX_USER_INFOMapper.deleteWX_USER_INFO(map);
	}
	
	public void updateWX_USER_INFO(Map map) throws Exception{
		WX_USER_INFOMapper.updateWX_USER_INFO(map);
	}
	
	public	List<Map> selectWX_USER_INFO(Map map) throws Exception{
		return WX_USER_INFOMapper.selectWX_USER_INFO(map);
	}
	
	public	int selectWX_USER_INFOCount(Map map){
		return WX_USER_INFOMapper.selectWX_USER_INFOCount(map);
	}
}
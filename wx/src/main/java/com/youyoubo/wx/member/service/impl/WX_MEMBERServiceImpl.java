package com.youyoubo.wx.member.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.youyoubo.wx.member.service.IWX_MEMBERService;
import com.youyoubo.wx.member.mapper.WX_MEMBERMapper;

/**
 * <ol>
 * date:2019-08-08 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>会员Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class WX_MEMBERServiceImpl extends BaseServiceImpl implements IWX_MEMBERService {
	@Autowired
	WX_MEMBERMapper WX_MEMBERMapper;
	
	public void insertWX_MEMBER(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		WX_MEMBERMapper.insertWX_MEMBER(map);
	}
	
	public void deleteWX_MEMBER(Map map) throws Exception{
		WX_MEMBERMapper.deleteWX_MEMBER(map);
	}
	
	public void updateWX_MEMBER(Map map) throws Exception{
		WX_MEMBERMapper.updateWX_MEMBER(map);
	}
	
	public	List<Map> selectWX_MEMBER(Map map) throws Exception{
		return WX_MEMBERMapper.selectWX_MEMBER(map);
	}
	
	public	int selectWX_MEMBERCount(Map map){
		return WX_MEMBERMapper.selectWX_MEMBERCount(map);
	}
}
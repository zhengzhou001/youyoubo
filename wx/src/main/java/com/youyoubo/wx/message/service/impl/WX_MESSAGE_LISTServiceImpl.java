package com.youyoubo.wx.message.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.youyoubo.wx.message.service.IWX_MESSAGE_LISTService;
import com.youyoubo.wx.message.mapper.WX_MESSAGE_LISTMapper;

/**
 * <ol>
 * date:2019-08-02 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>微信消息记录Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class WX_MESSAGE_LISTServiceImpl extends BaseServiceImpl implements IWX_MESSAGE_LISTService {
	@Autowired
	WX_MESSAGE_LISTMapper WX_MESSAGE_LISTMapper;
	
	public void insertWX_MESSAGE_LIST(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		WX_MESSAGE_LISTMapper.insertWX_MESSAGE_LIST(map);
	}
	
	public void deleteWX_MESSAGE_LIST(Map map) throws Exception{
		WX_MESSAGE_LISTMapper.deleteWX_MESSAGE_LIST(map);
	}
	
	public void updateWX_MESSAGE_LIST(Map map) throws Exception{
		WX_MESSAGE_LISTMapper.updateWX_MESSAGE_LIST(map);
	}
	
	public	List<Map> selectWX_MESSAGE_LIST(Map map) throws Exception{
		return WX_MESSAGE_LISTMapper.selectWX_MESSAGE_LIST(map);
	}
	
	public	int selectWX_MESSAGE_LISTCount(Map map){
		return WX_MESSAGE_LISTMapper.selectWX_MESSAGE_LISTCount(map);
	}
}
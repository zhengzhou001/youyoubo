package com.chuxi.order.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.chuxi.order.service.IWX_ORDERService;
import com.chuxi.order.mapper.WX_ORDERMapper;

/**
 * <ol>
 * date:2019-08-21 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>微信订单Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class WX_ORDERServiceImpl extends BaseServiceImpl implements IWX_ORDERService {
	@Autowired
	WX_ORDERMapper WX_ORDERMapper;
	
	public void insertWX_ORDER(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		WX_ORDERMapper.insertWX_ORDER(map);
	}
	
	public void deleteWX_ORDER(Map map) throws Exception{
		WX_ORDERMapper.deleteWX_ORDER(map);
	}
	
	public void updateWX_ORDER(Map map) throws Exception{
		WX_ORDERMapper.updateWX_ORDER(map);
	}
	
	public	List<Map> selectWX_ORDER(Map map) throws Exception{
		return WX_ORDERMapper.selectWX_ORDER(map);
	}
	
	public	int selectWX_ORDERCount(Map map){
		return WX_ORDERMapper.selectWX_ORDERCount(map);
	}
}
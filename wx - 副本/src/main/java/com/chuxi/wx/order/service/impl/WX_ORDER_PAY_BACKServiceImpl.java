package com.chuxi.order.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.chuxi.order.service.IWX_ORDER_PAY_BACKService;
import com.chuxi.order.mapper.WX_ORDER_PAY_BACKMapper;

/**
 * <ol>
 * date:2019-08-21 editor:dingshuangbo
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
public class WX_ORDER_PAY_BACKServiceImpl extends BaseServiceImpl implements IWX_ORDER_PAY_BACKService {
	@Autowired
	WX_ORDER_PAY_BACKMapper WX_ORDER_PAY_BACKMapper;
	
	public void insertWX_ORDER_PAY_BACK(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		WX_ORDER_PAY_BACKMapper.insertWX_ORDER_PAY_BACK(map);
	}
	
	public void deleteWX_ORDER_PAY_BACK(Map map) throws Exception{
		WX_ORDER_PAY_BACKMapper.deleteWX_ORDER_PAY_BACK(map);
	}
	
	public void updateWX_ORDER_PAY_BACK(Map map) throws Exception{
		WX_ORDER_PAY_BACKMapper.updateWX_ORDER_PAY_BACK(map);
	}
	
	public	List<Map> selectWX_ORDER_PAY_BACK(Map map) throws Exception{
		return WX_ORDER_PAY_BACKMapper.selectWX_ORDER_PAY_BACK(map);
	}
	
	public	int selectWX_ORDER_PAY_BACKCount(Map map){
		return WX_ORDER_PAY_BACKMapper.selectWX_ORDER_PAY_BACKCount(map);
	}
}
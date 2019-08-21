package com.chuxi.order.mapper;

import java.util.List;
import java.util.Map;


/**
 * <ol>
 * date:2019-08-21 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>微信订单Mapper接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface WX_ORDERMapper {
	public void insertWX_ORDER(Map map);
	
	public void deleteWX_ORDER(Map map);
	
	public void updateWX_ORDER(Map map);
	
	public	List<Map> selectWX_ORDER(Map map);
	
	public	int selectWX_ORDERCount(Map map);
}
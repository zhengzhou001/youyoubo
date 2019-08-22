package com.chuxi.wx.mapper;

import java.util.List;
import java.util.Map;


/**
 * <ol>
 * date:2019-08-22 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>Mapper接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface WX_SUBSCRIBEMapper {
	public void insertWX_SUBSCRIBE(Map map);
	
	public void deleteWX_SUBSCRIBE(Map map);
	
	public void updateWX_SUBSCRIBE(Map map);
	
	public	List<Map> selectWX_SUBSCRIBE(Map map);
	
	public	int selectWX_SUBSCRIBECount(Map map);
}
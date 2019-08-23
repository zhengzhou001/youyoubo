package com.chuxi.user.mapper;

import java.util.List;
import java.util.Map;


/**
 * <ol>
 * date:2019-08-23 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>Mapper接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface USER_INFOMapper {
	public void insertUSER_INFO(Map map);
	
	public void deleteUSER_INFO(Map map);
	
	public void updateUSER_INFO(Map map);
	
	public	List<Map> selectUSER_INFO(Map map);
	
	public	int selectUSER_INFOCount(Map map);
}
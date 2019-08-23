package com.chuxi.dm.mapper;

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
public interface DM_PROVINCEMapper {
	public void insertDM_PROVINCE(Map map);
	
	public void deleteDM_PROVINCE(Map map);
	
	public void updateDM_PROVINCE(Map map);
	
	public	List<Map> selectDM_PROVINCE(Map map);
	
	public	int selectDM_PROVINCECount(Map map);
}
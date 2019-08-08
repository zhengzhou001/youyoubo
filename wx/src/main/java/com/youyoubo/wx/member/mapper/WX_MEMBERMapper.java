package com.youyoubo.wx.member.mapper;

import java.util.List;
import java.util.Map;


/**
 * <ol>
 * date:2019-08-08 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>会员Mapper接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface WX_MEMBERMapper {
	public void insertWX_MEMBER(Map map);
	
	public void deleteWX_MEMBER(Map map);
	
	public void updateWX_MEMBER(Map map);
	
	public	List<Map> selectWX_MEMBER(Map map);
	
	public	int selectWX_MEMBERCount(Map map);
}
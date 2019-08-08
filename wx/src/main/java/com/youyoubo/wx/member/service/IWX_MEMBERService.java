package com.youyoubo.wx.member.service;

import java.util.List;
import java.util.Map;
import com.base.service.IBaseService;

/**
 * <ol>
 * date:2019-08-08 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>会员Service接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
public interface IWX_MEMBERService extends IBaseService{
	 
	public void insertWX_MEMBER(Map map) throws Exception;
	
	public void deleteWX_MEMBER(Map map) throws Exception;
	
	public void updateWX_MEMBER(Map map) throws Exception;
	 
	public	List<Map> selectWX_MEMBER(Map map) throws Exception;
	
	public	int selectWX_MEMBERCount(Map map);
}
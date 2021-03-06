package com.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.IBaseDao;
import com.base.service.IBaseService;

@Service(value="baseService")
public class BaseServiceImpl implements IBaseService {
	@Autowired
	private IBaseDao baseDao;
	public int insert(String sqlid,Object object){
		return baseDao.insert(sqlid, object);
	}
	
	public int update(String sqlid,Object object){
		return baseDao.insert(sqlid, object);
	}
	
	public int delete(String sqlid,Object object){
		return baseDao.insert(sqlid, object);
	}
	public List select(String sqlid,Object object){
		return baseDao.select(sqlid, object);
	}
	public Object selectOne(String sqlid,Object object){
		return baseDao.selectOne(sqlid, object);
	}
}

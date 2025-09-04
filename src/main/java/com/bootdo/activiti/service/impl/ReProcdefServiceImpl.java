package com.bootdo.activiti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.activiti.dao.ReProcdefDao;
import com.bootdo.activiti.domain.ReProcdefDO;
import com.bootdo.activiti.service.ReProcdefService;



@Service
public class ReProcdefServiceImpl implements ReProcdefService {
	@Autowired
	private ReProcdefDao reProcdefDao;
	
	@Override
	public ReProcdefDO get(String id){
		return reProcdefDao.get(id);
	}
	
	@Override
	public List<ReProcdefDO> list(Map<String, Object> map){
		return reProcdefDao.list(map);
	}
	
	@Override
	public List<ReProcdefDO> listNew(Map<String, Object> map){
		return reProcdefDao.listNew(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return reProcdefDao.count(map);
	}
}

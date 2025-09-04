package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.LawyerInfoDao;
import com.bootdo.system.domain.LawyerInfoDO;
import com.bootdo.system.service.LawyerInfoService;



@Service
public class LawyerInfoServiceImpl implements LawyerInfoService {
	@Autowired
	private LawyerInfoDao lawyerInfoDao;
	
	@Override
	public LawyerInfoDO get(Integer number){
		return lawyerInfoDao.get(number);
	}
	
	@Override
	public List<LawyerInfoDO> list(Map<String, Object> map){
		return lawyerInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lawyerInfoDao.count(map);
	}
	
	@Override
	public int save(LawyerInfoDO lawyerInfo){
		return lawyerInfoDao.save(lawyerInfo);
	}
	
	@Override
	public int update(LawyerInfoDO lawyerInfo){
		return lawyerInfoDao.update(lawyerInfo);
	}
	
	@Override
	public int remove(Integer number){
		return lawyerInfoDao.remove(number);
	}
	
	@Override
	public int batchRemove(Integer[] numbers){
		return lawyerInfoDao.batchRemove(numbers);
	}
	
}

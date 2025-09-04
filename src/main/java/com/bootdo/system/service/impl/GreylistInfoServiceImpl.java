package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.GreylistInfoDao;
import com.bootdo.system.domain.GreylistInfoDO;
import com.bootdo.system.service.GreylistInfoService;



@Service
public class GreylistInfoServiceImpl implements GreylistInfoService {
	@Autowired
	private GreylistInfoDao greylistInfoDao;
	
	@Override
	public GreylistInfoDO get(Integer number){
		return greylistInfoDao.get(number);
	}
	
	@Override
	public List<GreylistInfoDO> list(Map<String, Object> map){
		return greylistInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return greylistInfoDao.count(map);
	}
	
	@Override
	public int save(GreylistInfoDO greylistInfo){
		return greylistInfoDao.save(greylistInfo);
	}
	
	@Override
	public int update(GreylistInfoDO greylistInfo){
		return greylistInfoDao.update(greylistInfo);
	}
	
	@Override
	public int remove(Integer number){
		return greylistInfoDao.remove(number);
	}
	
	@Override
	public int batchRemove(Integer[] numbers){
		return greylistInfoDao.batchRemove(numbers);
	}
	
}

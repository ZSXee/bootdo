package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.GreylistDao;
import com.bootdo.system.domain.GreylistDO;
import com.bootdo.system.service.GreylistService;



@Service
public class GreylistServiceImpl implements GreylistService {
	@Autowired
	private GreylistDao greylistDao;
	
	@Override
	public GreylistDO get(Integer number){
		return greylistDao.get(number);
	}
	
	@Override
	public List<GreylistDO> list(Map<String, Object> map){
		return greylistDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return greylistDao.count(map);
	}
	
	@Override
	public int save(GreylistDO greylist){
		return greylistDao.save(greylist);
	}
	
	@Override
	public int update(GreylistDO greylist){
		return greylistDao.update(greylist);
	}
	
	@Override
	public int remove(Integer number){
		return greylistDao.remove(number);
	}
	
	@Override
	public int batchRemove(Integer[] numbers){
		return greylistDao.batchRemove(numbers);
	}
	
}

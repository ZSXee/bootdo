package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.DailyDao;
import com.bootdo.system.domain.DailyDO;
import com.bootdo.system.service.DailyService;



@Service
public class DailyServiceImpl implements DailyService {
	@Autowired
	private DailyDao dailyDao;
	
	@Override
	public DailyDO get(String cntId){
		return dailyDao.get(cntId);
	}
	
	@Override
	public List<DailyDO> list(Map<String, Object> map){
		return dailyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dailyDao.count(map);
	}
	
	@Override
	public int save(DailyDO daily){
		return dailyDao.save(daily);
	}
	
	@Override
	public int update(DailyDO daily){
		return dailyDao.update(daily);
	}
	
	@Override
	public int remove(String cntId){
		return dailyDao.remove(cntId);
	}
	
	@Override
	public int batchRemove(String[] cntIds){
		return dailyDao.batchRemove(cntIds);
	}
	
}

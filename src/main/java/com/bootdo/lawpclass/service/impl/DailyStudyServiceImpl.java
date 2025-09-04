package com.bootdo.lawpclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.lawpclass.dao.DailyStudyDao;
import com.bootdo.lawpclass.domain.DailyStudyDO;
import com.bootdo.lawpclass.service.DailyStudyService;



@Service
public class DailyStudyServiceImpl implements DailyStudyService {
	@Autowired
	private DailyStudyDao dailystudyDao;
	
	@Override
	public DailyStudyDO get(String id){
		return dailystudyDao.get(id);
	}
	
	@Override
	public List<DailyStudyDO> list(Map<String, Object> map){
		return dailystudyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return dailystudyDao.count(map);
	}
	
	@Override
	public int save(DailyStudyDO dailystudy){
		return dailystudyDao.save(dailystudy);
	}
	
	@Override
	public int update(DailyStudyDO dailystudy){
		return dailystudyDao.update(dailystudy);
	}
	
	@Override
	public int remove(String id){
		return dailystudyDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return dailystudyDao.batchRemove(ids);
	}
	
}

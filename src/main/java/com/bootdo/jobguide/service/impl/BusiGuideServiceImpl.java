package com.bootdo.jobguide.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.jobguide.dao.BusiGuideDao;
import com.bootdo.jobguide.domain.BusiGuideDO;
import com.bootdo.jobguide.service.BusiGuideService;



@Service
public class BusiGuideServiceImpl implements BusiGuideService {
	@Autowired
	private BusiGuideDao busiguideDao;
	
	@Override
	public BusiGuideDO get(String id){
		return busiguideDao.get(id);
	}
	
	@Override
	public List<BusiGuideDO> list(Map<String, Object> map){
		return busiguideDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return busiguideDao.count(map);
	}
	
	@Override
	public int save(BusiGuideDO busiguide){
		return busiguideDao.save(busiguide);
	}
	
	@Override
	public int update(BusiGuideDO busiguide){
		return busiguideDao.update(busiguide);
	}
	
	@Override
	public int remove(String id){
		return busiguideDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return busiguideDao.batchRemove(ids);
	}
	
}

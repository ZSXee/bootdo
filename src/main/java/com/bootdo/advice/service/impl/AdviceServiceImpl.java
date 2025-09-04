package com.bootdo.advice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.advice.dao.AdviceDao;
import com.bootdo.advice.domain.AdviceDO;
import com.bootdo.advice.service.AdviceService;



@Service
public class AdviceServiceImpl implements AdviceService {
	@Autowired
	private AdviceDao adviceDao;
	
	@Override
	public AdviceDO get(String id){
		return adviceDao.get(id);
	}
	
	@Override
	public List<AdviceDO> list(Map<String, Object> map){
		return adviceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return adviceDao.count(map);
	}
	
	@Override
	public int save(AdviceDO advice){
		return adviceDao.save(advice);
	}
	
	@Override
	public int update(AdviceDO advice){
		return adviceDao.update(advice);
	}
	
	@Override
	public int remove(String id){
		return adviceDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return adviceDao.batchRemove(ids);
	}
	
}

package com.bootdo.lawpclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.lawpclass.dao.TypicalCaseDao;
import com.bootdo.lawpclass.domain.TypicalCaseDO;
import com.bootdo.lawpclass.service.TypicalCaseService;



@Service
public class TypicalCaseServiceImpl implements TypicalCaseService {
	@Autowired
	private TypicalCaseDao typicalcaseDao;
	
	@Override
	public TypicalCaseDO get(String id){
		return typicalcaseDao.get(id);
	}
	
	@Override
	public List<TypicalCaseDO> list(Map<String, Object> map){
		return typicalcaseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return typicalcaseDao.count(map);
	}
	
	@Override
	public int save(TypicalCaseDO typicalcase){
		return typicalcaseDao.save(typicalcase);
	}
	
	@Override
	public int update(TypicalCaseDO typicalcase){
		return typicalcaseDao.update(typicalcase);
	}
	
	@Override
	public int remove(String id){
		return typicalcaseDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return typicalcaseDao.batchRemove(ids);
	}
	
}

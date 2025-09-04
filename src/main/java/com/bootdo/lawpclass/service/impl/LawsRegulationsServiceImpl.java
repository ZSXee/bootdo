package com.bootdo.lawpclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.lawpclass.dao.LawsRegulationsDao;
import com.bootdo.lawpclass.domain.LawsRegulationsDO;
import com.bootdo.lawpclass.service.LawsRegulationsService;



@Service
public class LawsRegulationsServiceImpl implements LawsRegulationsService {
	@Autowired
	private LawsRegulationsDao lawsregulationsDao;
	
	@Override
	public LawsRegulationsDO get(String id){
		return lawsregulationsDao.get(id);
	}
	
	@Override
	public List<LawsRegulationsDO> list(Map<String, Object> map){
		return lawsregulationsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return lawsregulationsDao.count(map);
	}
	
	@Override
	public int save(LawsRegulationsDO lawsregulations){
		return lawsregulationsDao.save(lawsregulations);
	}
	
	@Override
	public int update(LawsRegulationsDO lawsregulations){
		return lawsregulationsDao.update(lawsregulations);
	}
	
	@Override
	public int remove(String id){
		return lawsregulationsDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return lawsregulationsDao.batchRemove(ids);
	}
	
}

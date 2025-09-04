package com.bootdo.htdzk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.htdzk.dao.GshtdzkDao;
import com.bootdo.htdzk.domain.GshtdzkDO;
import com.bootdo.htdzk.service.GshtdzkService;



@Service
public class GshtdzkServiceImpl implements GshtdzkService {
	@Autowired
	private GshtdzkDao gshtdzkDao;
	
	@Override
	public GshtdzkDO get(String id){
		return gshtdzkDao.get(id);
	}
	
	@Override
	public List<GshtdzkDO> list(Map<String, Object> map){
		return gshtdzkDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return gshtdzkDao.count(map);
	}
	
	@Override
	public int save(GshtdzkDO gshtdzk){
		return gshtdzkDao.save(gshtdzk);
	}
	
	@Override
	public int update(GshtdzkDO gshtdzk){
		return gshtdzkDao.update(gshtdzk);
	}
	
	@Override
	public int remove(String id){
		return gshtdzkDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return gshtdzkDao.batchRemove(ids);
	}
	
}

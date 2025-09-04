package com.bootdo.htdzk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.htdzk.dao.FgshtdzkDao;
import com.bootdo.htdzk.domain.FgshtdzkDO;
import com.bootdo.htdzk.service.FgshtdzkService;



@Service
public class FgshtdzkServiceImpl implements FgshtdzkService {
	@Autowired
	private FgshtdzkDao fgshtdzkDao;
	
	@Override
	public FgshtdzkDO get(String id){
		return fgshtdzkDao.get(id);
	}
	
	@Override
	public List<FgshtdzkDO> list(Map<String, Object> map){
		return fgshtdzkDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fgshtdzkDao.count(map);
	}
	
	@Override
	public int save(FgshtdzkDO fgshtdzk){
		return fgshtdzkDao.save(fgshtdzk);
	}
	
	@Override
	public int update(FgshtdzkDO fgshtdzk){
		return fgshtdzkDao.update(fgshtdzk);
	}
	
	@Override
	public int remove(String id){
		return fgshtdzkDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return fgshtdzkDao.batchRemove(ids);
	}
	
}

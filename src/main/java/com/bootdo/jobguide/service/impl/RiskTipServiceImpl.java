package com.bootdo.jobguide.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.jobguide.dao.RiskTipDao;
import com.bootdo.jobguide.domain.RiskTipDO;
import com.bootdo.jobguide.service.RiskTipService;



@Service
public class RiskTipServiceImpl implements RiskTipService {
	@Autowired
	private RiskTipDao risktipDao;
	
	@Override
	public RiskTipDO get(String id){
		return risktipDao.get(id);
	}
	
	@Override
	public List<RiskTipDO> list(Map<String, Object> map){
		return risktipDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return risktipDao.count(map);
	}
	
	@Override
	public int save(RiskTipDO risktip){
		return risktipDao.save(risktip);
	}
	
	@Override
	public int update(RiskTipDO risktip){
		return risktipDao.update(risktip);
	}
	
	@Override
	public int remove(String id){
		return risktipDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return risktipDao.batchRemove(ids);
	}
	
}

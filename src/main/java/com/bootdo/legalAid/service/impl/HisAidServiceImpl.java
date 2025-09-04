package com.bootdo.legalAid.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.legalAid.dao.HisAidDao;
import com.bootdo.legalAid.domain.HisAidDO;
import com.bootdo.legalAid.service.HisAidService;

@Service
public class HisAidServiceImpl implements HisAidService {
	@Autowired
	private HisAidDao hisAidDao;
	
	@Override
	public HisAidDO get(String id){
		return hisAidDao.get(id);
	}
	
	@Override
	public List<HisAidDO> list(Map<String, Object> map){
		return hisAidDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return hisAidDao.count(map);
	}

	@Override
	public List<HisAidDO> list() {
		return hisAidDao.list();
	}
	
}

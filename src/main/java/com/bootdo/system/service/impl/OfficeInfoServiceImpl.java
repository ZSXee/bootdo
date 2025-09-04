package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.OfficeInfoDao;
import com.bootdo.system.domain.OfficeInfoDO;
import com.bootdo.system.service.OfficeInfoService;



@Service
public class OfficeInfoServiceImpl implements OfficeInfoService {
	@Autowired
	private OfficeInfoDao officeInfoDao;
	
	@Override
	public OfficeInfoDO get(String cntId){
		return officeInfoDao.get(cntId);
	}
	
	@Override
	public List<OfficeInfoDO> list(Map<String, Object> map){
		return officeInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return officeInfoDao.count(map);
	}
	
	@Override
	public int save(OfficeInfoDO officeInfo){
		return officeInfoDao.save(officeInfo);
	}
	
	@Override
	public int update(OfficeInfoDO officeInfo){
		return officeInfoDao.update(officeInfo);
	}
	
	@Override
	public int remove(String cntId){
		return officeInfoDao.remove(cntId);
	}
	
	@Override
	public int batchRemove(String[] cntIds){
		return officeInfoDao.batchRemove(cntIds);
	}
	
}

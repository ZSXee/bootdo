package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.DeptExtendDao;
import com.bootdo.system.domain.DeptExtendDO;
import com.bootdo.system.service.DeptExtendService;



@Service
public class DeptExtendServiceImpl implements DeptExtendService {
	@Autowired
	private DeptExtendDao deptExtendDao;
	
	@Override
	public DeptExtendDO get(Long deptIdE){
		return deptExtendDao.get(deptIdE);
	}
	
	@Override
	public List<DeptExtendDO> list(Map<String, Object> map){
		return deptExtendDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deptExtendDao.count(map);
	}
	
	@Override
	public int save(DeptExtendDO deptExtend){
		return deptExtendDao.save(deptExtend);
	}
	
	@Override
	public int update(DeptExtendDO deptExtend){
		return deptExtendDao.update(deptExtend);
	}
	
	@Override
	public int remove(Long deptIdE){
		return deptExtendDao.remove(deptIdE);
	}
	
	@Override
	public int batchRemove(Long[] deptIdEs){
		return deptExtendDao.batchRemove(deptIdEs);
	}
	
}

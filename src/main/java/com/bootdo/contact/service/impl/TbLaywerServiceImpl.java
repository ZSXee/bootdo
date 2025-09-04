package com.bootdo.contact.service.impl;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.contact.dao.TbLaywerDao;
import com.bootdo.contact.domain.TbLaywerDO;
import com.bootdo.contact.service.TbLaywerService;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;



@Service
public class TbLaywerServiceImpl implements TbLaywerService {
	@Autowired
	private TbLaywerDao tbLaywerDao;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	@Autowired
	private RuntimeService runtimeService;
    @Autowired
    UserRoleDao userRoleMapper;
	@Autowired
	DeptService sysDeptService;
	@Override
	public TbLaywerDO get(String procInsId){
		return tbLaywerDao.get(procInsId);
	}
	
	@Override
	public List<TbLaywerDO> list(Map<String, Object> map){
		return tbLaywerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tbLaywerDao.count(map);
	}
	
	
	@Override
	public int remove(String cntId){
		return tbLaywerDao.remove(cntId);
	}
	
	@Override
	public int batchRemove(String[] cntIds){
		return tbLaywerDao.batchRemove(cntIds);
	}

	@Override
	public int save(TbLaywerDO tbLaywer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(TbLaywerDO tbLaywer, UserDO userDO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(TbLaywerDO tbLaywer, UserDO userDO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void commit(TbLaywerDO tbLaywer, UserDO userDO) {
		// TODO Auto-generated method stub
		
	}
	
}

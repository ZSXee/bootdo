package com.bootdo.legalAid.service.impl;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.legalAid.dao.LegalAidDao;
import com.bootdo.legalAid.domain.LegalAidDO;
import com.bootdo.legalAid.service.LegalAidService;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;



@Service
public class LegalAidServiceImpl implements LegalAidService {
	
	@Autowired
	private ActTaskServiceImpl actTaskService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private LegalAidDao legalAidDao;
	
	@Autowired
	DeptService sysDeptService;
	
    @Autowired
    UserRoleDao userRoleMapper;
	
	@Override
	public LegalAidDO get(String aidId){
		return legalAidDao.get(aidId);
	}
	
	@Override
	public List<LegalAidDO> list(Map<String, Object> map){
		return legalAidDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return legalAidDao.count(map);
	}
	
	@Override
	public int save(LegalAidDO legalAid){
		Map<String, Object> variables = new HashMap<String, Object>();
		actTaskService.startProcess(legalAid.getKey(),null,legalAid.getAidId(),legalAid.getAidType(),variables);
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(legalAid.getAidId()).singleResult();
		runtimeService.setProcessInstanceName(processInstance.getId(),legalAid.getAidType());
		legalAid.setProcInsId(processInstance.getId());
		return legalAidDao.save(legalAid);
	}
	
	@Override
	public void commit(LegalAidDO legalAid, UserDO userDO){
		Map<String,Object> variables = new HashMap<>(16);
		variables.put("pass",  legalAid.getTaskPass() );
		actTaskService.complete(legalAid.getTaskId(), variables, legalAid.getTaskComment(), userDO);
		legalAidDao.update(legalAid);
	}
	
	@Override
	public int update(LegalAidDO legalAid, UserDO userDO){
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("pass",  legalAid.getTaskPass() );
		actTaskService.complete(legalAid.getTaskId(), vars, legalAid.getTaskComment(), userDO);
		return legalAidDao.update(legalAid);
	}
	
	@Override
	public int remove(String aidId){
		return legalAidDao.remove(aidId);
	}
	
	@Override
	public int batchRemove(String[] aidIds){
		return legalAidDao.batchRemove(aidIds);
	}

	@Override
	public LegalAidDO getByProc(String procId) {
		// TODO Auto-generated method stub
		return legalAidDao.getByProc(procId);
	}

	@Override
	public int update(LegalAidDO legalAid) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

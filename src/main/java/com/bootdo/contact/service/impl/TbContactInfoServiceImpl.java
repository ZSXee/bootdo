package com.bootdo.contact.service.impl;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.listener.H1TaksListener;
import com.bootdo.activiti.service.impl.ActTaskServiceImpl;
import com.bootdo.contact.dao.TbContactInfoDao;
import com.bootdo.contact.domain.TbContactInfoDO;
import com.bootdo.contact.service.TbContactInfoService;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;



@Service
public class TbContactInfoServiceImpl implements TbContactInfoService {
	protected static final Logger LOGGER = LoggerFactory.getLogger(TbContactInfoServiceImpl.class);
	@Autowired
	private TbContactInfoDao tbContactInfoDao;
	@Autowired
	private ActTaskServiceImpl actTaskService;
	@Autowired
	private RuntimeService runtimeService;
    @Autowired
    UserRoleDao userRoleMapper;
	@Autowired
	DeptService sysDeptService;
	@Override
	public TbContactInfoDO get(String procInsId){
		return tbContactInfoDao.get(procInsId);
	}
	
	@Override
	public List<TbContactInfoDO> list(Map<String, Object> map){
		return tbContactInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tbContactInfoDao.count(map);
	}
	
	@Override
	public int save(TbContactInfoDO tbContactInfo, UserDO userDO){
		Map<String, Object> variables = new HashMap<String, Object>();
		
		//设置是否会签标志
		if(tbContactInfo.getCntReserve2()!=null && tbContactInfo.getCntReserve2().equals("on")){
			variables.put("countersign", "1");
			//设置会签部门
	        String[] arr = tbContactInfo.getCntReserve1().split(",");
	        List<String> assigneeList = Arrays.asList(arr);
			variables.put("assigneeList", assigneeList);
			LOGGER.info("assigneeList:"+assigneeList.toString());
			LOGGER.info("countersign:1");
		}else{
			variables.put("countersign", "0");
		}
		actTaskService.startProcess(tbContactInfo.getKey(),null,tbContactInfo.getCntId(),tbContactInfo.getCntName(),variables);
		
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(tbContactInfo.getCntId()).singleResult();
		runtimeService.setProcessInstanceName(processInstance.getId(),tbContactInfo.getCntName());
		
		tbContactInfo.setProcInsId(processInstance.getId());
		
		return tbContactInfoDao.save(tbContactInfo);
	}
	
	@Override
	public int update(TbContactInfoDO tbContactInfo, UserDO userDO){
		
		Map<String,Object> vars = new HashMap<>(16);
		vars.put("pass",  tbContactInfo.getTaskPass() );
		actTaskService.complete(tbContactInfo.getTaskId(), vars, tbContactInfo.getTaskComment(), userDO);
		return tbContactInfoDao.update(tbContactInfo);
	}
	@Override
	public void commit(TbContactInfoDO tbContactInfo, UserDO userDO){
		Map<String,Object> variables = new HashMap<>(16);
		
        DeptDO sysDept = sysDeptService.get(userDO.getDeptId());
        
        TbContactInfoDO tbContactInfo_tmp = tbContactInfoDao.get(tbContactInfo.getProcInsId());
        //获取用户角色
        List<Long> list = userRoleMapper.listRoleId(userDO.getUserId());
        //合规风险部的员工/流程发起人可以再次设置是否会签
        if(((sysDept.getDeptType()==4||sysDept.getDeptType()==5)&&(list.get(0)==2||list.get(0)==6))||tbContactInfo_tmp.getCntAdmin().equals(userDO.getUserId().toString())){
    		//设置是否会签标志
    		if(tbContactInfo.getCntReserve2()!=null && tbContactInfo.getCntReserve2().equals("on")){
    			variables.put("countersign", "1");
    			//设置会签部门
    	        String[] arr = tbContactInfo.getCntReserve1().split(",");
    	        List<String> assigneeList = Arrays.asList(arr);
    			variables.put("assigneeList", assigneeList);
    		}else{
    			variables.put("countersign", "0");
    		}
        }
		
		variables.put("pass",  tbContactInfo.getTaskPass() );
		actTaskService.complete(tbContactInfo.getTaskId(), variables, tbContactInfo.getTaskComment(), userDO);
		tbContactInfoDao.update(tbContactInfo);
	}	
	@Override
	public int remove(String cntId){
		return tbContactInfoDao.remove(cntId);
	}
	
	@Override
	public int batchRemove(String[] cntIds){
		return tbContactInfoDao.batchRemove(cntIds);
	}

	@Override
	public int save(TbContactInfoDO tbContactInfo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

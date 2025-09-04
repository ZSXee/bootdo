package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.ReProcdefDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-10-09 08:04:54
 */
public interface ReProcdefService {
	
	ReProcdefDO get(String id);
	
	List<ReProcdefDO> list(Map<String, Object> map);
	
	List<ReProcdefDO> listNew(Map<String, Object> map);
	
	int count(Map<String, Object> map);

}

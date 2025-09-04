package com.bootdo.activiti.dao;

import com.bootdo.activiti.domain.ReProcdefDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-10-09 08:04:54
 */
@Mapper
public interface ReProcdefDao {

	ReProcdefDO get(String id);
	
	List<ReProcdefDO> list(Map<String,Object> map);
	
	List<ReProcdefDO> listNew(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ReProcdefDO reProcdef);
	
	int update(ReProcdefDO reProcdef);
	
	int remove(String ID_);
	
	int batchRemove(String[] ids);
}

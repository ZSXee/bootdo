package com.bootdo.activiti.dao;

import com.bootdo.activiti.domain.HiTaskinstDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-11-02 18:55:51
 */
@Mapper
public interface HiTaskinstDao {
	
	List<HiTaskinstDO> list(Map<String,Object> map);
	int count(Map<String, Object> map);
}

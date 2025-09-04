package com.bootdo.system.dao;

import com.bootdo.system.domain.LawyerInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-05-26 14:45:29
 */
@Mapper
public interface LawyerInfoDao {

	LawyerInfoDO get(Integer number);
	
	List<LawyerInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LawyerInfoDO lawyerInfo);
	
	int update(LawyerInfoDO lawyerInfo);
	
	int remove(Integer number);
	
	int batchRemove(Integer[] numbers);
}

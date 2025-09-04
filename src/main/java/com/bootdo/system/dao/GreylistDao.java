package com.bootdo.system.dao;

import com.bootdo.system.domain.GreylistDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-06-23 10:24:34
 */
@Mapper
public interface GreylistDao {

	GreylistDO get(Integer number);
	
	List<GreylistDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GreylistDO greylist);
	
	int update(GreylistDO greylist);
	
	int remove(Integer number);
	
	int batchRemove(Integer[] numbers);
}

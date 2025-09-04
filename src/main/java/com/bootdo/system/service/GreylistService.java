package com.bootdo.system.service;

import com.bootdo.system.domain.GreylistDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-06-23 10:24:34
 */
public interface GreylistService {
	
	GreylistDO get(Integer number);
	
	List<GreylistDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GreylistDO greylist);
	
	int update(GreylistDO greylist);
	
	int remove(Integer number);
	
	int batchRemove(Integer[] numbers);
}

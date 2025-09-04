package com.bootdo.system.service;

import com.bootdo.system.domain.GreylistInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-06-24 09:58:53
 */
public interface GreylistInfoService {
	
	GreylistInfoDO get(Integer number);
	
	List<GreylistInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GreylistInfoDO greylistInfo);
	
	int update(GreylistInfoDO greylistInfo);
	
	int remove(Integer number);
	
	int batchRemove(Integer[] numbers);
}

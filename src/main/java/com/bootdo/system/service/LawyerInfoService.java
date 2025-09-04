package com.bootdo.system.service;

import com.bootdo.system.domain.LawyerInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-05-26 14:45:29
 */
public interface LawyerInfoService {
	
	LawyerInfoDO get(Integer number);
	
	List<LawyerInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LawyerInfoDO lawyerInfo);
	
	int update(LawyerInfoDO lawyerInfo);
	
	int remove(Integer number);
	
	int batchRemove(Integer[] numbers);
}

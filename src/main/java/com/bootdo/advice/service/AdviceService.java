package com.bootdo.advice.service;

import com.bootdo.advice.domain.AdviceDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-11-16 13:58:58
 */
public interface AdviceService {
	
	AdviceDO get(String id);
	
	List<AdviceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AdviceDO advice);
	
	int update(AdviceDO advice);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

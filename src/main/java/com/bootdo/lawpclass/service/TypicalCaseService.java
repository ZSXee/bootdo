package com.bootdo.lawpclass.service;

import com.bootdo.lawpclass.domain.TypicalCaseDO;

import java.util.List;
import java.util.Map;

/**
 * 学习培训
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public interface TypicalCaseService {
	
	TypicalCaseDO get(String id);
	
	List<TypicalCaseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TypicalCaseDO typicalcase);
	
	int update(TypicalCaseDO typicalcase);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

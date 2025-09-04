package com.bootdo.system.service;

import com.bootdo.system.domain.DailyDO;

import java.util.List;
import java.util.Map;

/**
 * 学习培训
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public interface DailyService {
	
	DailyDO get(String cntId);
	
	List<DailyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DailyDO daily);
	
	int update(DailyDO daily);
	
	int remove(String cntId);
	
	int batchRemove(String[] cntIds);
}

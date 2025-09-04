package com.bootdo.lawpclass.service;

import com.bootdo.lawpclass.domain.DailyStudyDO;

import java.util.List;
import java.util.Map;

/**
 * 学习培训
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public interface DailyStudyService {
	
	DailyStudyDO get(String id);
	
	List<DailyStudyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DailyStudyDO dailystudy);
	
	int update(DailyStudyDO dailystudy);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

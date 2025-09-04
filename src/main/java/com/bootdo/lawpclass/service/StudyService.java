package com.bootdo.lawpclass.service;

import com.bootdo.lawpclass.domain.StudyDO;

import java.util.List;
import java.util.Map;

/**
 * 学习培训
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public interface StudyService {
	
	StudyDO get(String id);
	
	List<StudyDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudyDO study);
	
	int update(StudyDO study);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

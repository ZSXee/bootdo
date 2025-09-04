package com.bootdo.system.service;

import com.bootdo.system.domain.StudyerDO;

import java.util.List;
import java.util.Map;

/**
 * 学习培训
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public interface StudyerService {
	
	StudyerDO get(String id);
	
	List<StudyerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(StudyerDO study);
	
	int update(StudyerDO study);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

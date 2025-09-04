package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.AddhiCommentDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2023-07-12 11:40:21
 */
public interface AddhiCommentService {
	
	AddhiCommentDO get(String id);
	
	List<AddhiCommentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AddhiCommentDO addhiComment);
	
	int update(AddhiCommentDO addhiComment);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

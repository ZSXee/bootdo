package com.bootdo.activiti.dao;

import com.bootdo.activiti.domain.AddhiCommentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2023-07-12 11:40:21
 */
@Mapper
public interface AddhiCommentDao {

	AddhiCommentDO get(String id);
	
	List<AddhiCommentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AddhiCommentDO addhiComment);
	
	int update(AddhiCommentDO addhiComment);
	
	int remove(String ID_);
	
	int batchRemove(String[] ids);
}

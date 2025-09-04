package com.bootdo.lawpclass.dao;

import com.bootdo.lawpclass.domain.StudyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 学习培训
 * @author zcf
 * @email zcf@163.com
 * @date 2020-05-22
 */
@Mapper
public interface StudyDao {

	StudyDO get(String id);
	
	List<StudyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudyDO study);
	
	int update(StudyDO study);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

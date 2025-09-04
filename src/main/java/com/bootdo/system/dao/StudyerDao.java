package com.bootdo.system.dao;

import com.bootdo.system.domain.StudyerDO;

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
public interface StudyerDao {

	StudyerDO get(String id);
	
	List<StudyerDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(StudyerDO studyer);
	
	int update(StudyerDO studyer);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

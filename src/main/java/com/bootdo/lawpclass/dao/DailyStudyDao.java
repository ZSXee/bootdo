package com.bootdo.lawpclass.dao;

import com.bootdo.lawpclass.domain.DailyStudyDO;

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
public interface DailyStudyDao {

	DailyStudyDO get(String id);
	
	List<DailyStudyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DailyStudyDO dailystudy);
	
	int update(DailyStudyDO dailystudy);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

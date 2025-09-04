package com.bootdo.system.dao;

import com.bootdo.system.domain.DailyDO;

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
public interface DailyDao {

	DailyDO get(String cntId);
	
	List<DailyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DailyDO daily);
	
	int update(DailyDO daily);
	
	int remove(String cntId);
	
	int batchRemove(String[] ids);
}

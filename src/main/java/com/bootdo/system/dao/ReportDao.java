package com.bootdo.system.dao;

import com.bootdo.system.domain.ReportDO;

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
public interface ReportDao {

	ReportDO get(String id);
	
	List<ReportDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ReportDO report);
	
	int update(ReportDO report);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

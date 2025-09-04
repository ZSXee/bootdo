package com.bootdo.lawpclass.dao;

import com.bootdo.lawpclass.domain.TypicalCaseDO;

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
public interface TypicalCaseDao {

	TypicalCaseDO get(String id);
	
	List<TypicalCaseDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TypicalCaseDO typicalcase);
	
	int update(TypicalCaseDO typicalcase);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

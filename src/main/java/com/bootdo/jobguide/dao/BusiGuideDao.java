package com.bootdo.jobguide.dao;

import com.bootdo.jobguide.domain.BusiGuideDO;

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
public interface BusiGuideDao {

	BusiGuideDO get(String id);
	
	List<BusiGuideDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(BusiGuideDO busiguide);
	
	int update(BusiGuideDO busiguide);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

package com.bootdo.jobguide.service;

import com.bootdo.jobguide.domain.BusiGuideDO;

import java.util.List;
import java.util.Map;

/**
 * 学习培训
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public interface BusiGuideService {
	
	BusiGuideDO get(String id);
	
	List<BusiGuideDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(BusiGuideDO busiguide);
	
	int update(BusiGuideDO busiguide);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

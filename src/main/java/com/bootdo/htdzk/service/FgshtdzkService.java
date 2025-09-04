package com.bootdo.htdzk.service;

import com.bootdo.htdzk.domain.FgshtdzkDO;

import java.util.List;
import java.util.Map;

/**
 * 学习培训
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public interface FgshtdzkService {
	
	FgshtdzkDO get(String id);
	
	List<FgshtdzkDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FgshtdzkDO typicalcase);
	
	int update(FgshtdzkDO typicalcase);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

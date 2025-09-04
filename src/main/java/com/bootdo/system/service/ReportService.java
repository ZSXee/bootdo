package com.bootdo.system.service;

import com.bootdo.system.domain.ReportDO;

import java.util.List;
import java.util.Map;

/**
 * 学习培训
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public interface ReportService {
	
	ReportDO get(String id);
	
	List<ReportDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ReportDO report);
	
	int update(ReportDO report);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

package com.bootdo.jobguide.service;

import com.bootdo.jobguide.domain.RiskTipDO;

import java.util.List;
import java.util.Map;

/**
 * 学习培训
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public interface RiskTipService {
	
	RiskTipDO get(String id);
	
	List<RiskTipDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RiskTipDO risktip);
	
	int update(RiskTipDO risktip);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

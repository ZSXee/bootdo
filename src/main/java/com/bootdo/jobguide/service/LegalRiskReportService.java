package com.bootdo.jobguide.service;

import com.bootdo.jobguide.domain.LegalRiskReportDO;

import java.util.List;
import java.util.Map;

/**
 * 法律风险报告
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public interface LegalRiskReportService {
	
	LegalRiskReportDO get(String id);
	
	List<LegalRiskReportDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LegalRiskReportDO legalriskreport);
	
	int update(LegalRiskReportDO legalriskreport);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

package com.bootdo.jobguide.dao;

import com.bootdo.jobguide.domain.LegalRiskReportDO;

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
public interface LegalRiskReportDao {

	LegalRiskReportDO get(String id);
	
	List<LegalRiskReportDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LegalRiskReportDO legalriskreport);
	
	int update(LegalRiskReportDO legalriskreport);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

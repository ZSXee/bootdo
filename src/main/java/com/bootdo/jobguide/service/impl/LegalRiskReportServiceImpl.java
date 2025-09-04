package com.bootdo.jobguide.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.jobguide.dao.LegalRiskReportDao;
import com.bootdo.jobguide.domain.LegalRiskReportDO;
import com.bootdo.jobguide.service.LegalRiskReportService;



@Service
public class LegalRiskReportServiceImpl implements LegalRiskReportService {
	@Autowired
	private LegalRiskReportDao legalriskreportDao;
	
	@Override
	public LegalRiskReportDO get(String id){
		return legalriskreportDao.get(id);
	}
	
	@Override
	public List<LegalRiskReportDO> list(Map<String, Object> map){
		return legalriskreportDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return legalriskreportDao.count(map);
	}
	
	@Override
	public int save(LegalRiskReportDO legalriskreport){
		return legalriskreportDao.save(legalriskreport);
	}
	
	@Override
	public int update(LegalRiskReportDO legalriskreport){
		return legalriskreportDao.update(legalriskreport);
	}
	
	@Override
	public int remove(String id){
		return legalriskreportDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return legalriskreportDao.batchRemove(ids);
	}
	
}

package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.ReportDao;
import com.bootdo.system.domain.ReportDO;
import com.bootdo.system.service.ReportService;



@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportDao reportDao;
	
	@Override
	public ReportDO get(String id){
		return reportDao.get(id);
	}
	
	@Override
	public List<ReportDO> list(Map<String, Object> map){
		return reportDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return reportDao.count(map);
	}
	
	@Override
	public int save(ReportDO report){
		return reportDao.save(report);
	}
	
	@Override
	public int update(ReportDO report){
		return reportDao.update(report);
	}
	
	@Override
	public int remove(String id){
		return reportDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return reportDao.batchRemove(ids);
	}
	
}

package com.bootdo.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.file.dao.TbOfficeInfoFilesDao;
import com.bootdo.file.domain.TbOfficeInfoFilesDO;
import com.bootdo.file.service.TbOfficeInfoFilesService;



@Service
public class TbOfficeInfoFilesServiceImpl implements TbOfficeInfoFilesService {
	@Autowired
	private TbOfficeInfoFilesDao TbOfficeInfoFilesDao;
	
	@Override
	public TbOfficeInfoFilesDO get(String id){
		return TbOfficeInfoFilesDao.get(id);
	}
	
	@Override
	public List<TbOfficeInfoFilesDO> list(Map<String, Object> map){
		return TbOfficeInfoFilesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return TbOfficeInfoFilesDao.count(map);
	}
	
	@Override
	public int save(TbOfficeInfoFilesDO tbOfficeInfo){
		return TbOfficeInfoFilesDao.save(tbOfficeInfo);
	}
	
	@Override
	public int update(TbOfficeInfoFilesDO tbOfficeInfo){
		return TbOfficeInfoFilesDao.update(tbOfficeInfo);
	}
	
	@Override
	public int remove(String id){
		return TbOfficeInfoFilesDao.remove(id);
	}

	@Override
	public int batchRemove(String[] ids){
		return TbOfficeInfoFilesDao.batchRemove(ids);
	}
	
}

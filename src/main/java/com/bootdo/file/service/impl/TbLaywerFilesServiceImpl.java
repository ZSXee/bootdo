package com.bootdo.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.file.dao.TbLaywerFilesDao;
import com.bootdo.file.domain.TbLaywerFilesDO;
import com.bootdo.file.service.TbLaywerFilesService;



@Service
public class TbLaywerFilesServiceImpl implements TbLaywerFilesService {
	@Autowired
	private TbLaywerFilesDao TbLaywerFilesDao;
	
	@Override
	public TbLaywerFilesDO get(String id){
		return TbLaywerFilesDao.get(id);
	}
	
	@Override
	public List<TbLaywerFilesDO> list(Map<String, Object> map){
		return TbLaywerFilesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return TbLaywerFilesDao.count(map);
	}
	
	@Override
	public int save(TbLaywerFilesDO tbLaywer){
		return TbLaywerFilesDao.save(tbLaywer);
	}
	
	@Override
	public int update(TbLaywerFilesDO tbLaywer){
		return TbLaywerFilesDao.update(tbLaywer);
	}
	
	@Override
	public int remove(String id){
		return TbLaywerFilesDao.remove(id);
	}

	@Override
	public int batchRemove(String[] ids){
		return TbLaywerFilesDao.batchRemove(ids);
	}
	
}

package com.bootdo.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.file.dao.TbContactFilesDao;
import com.bootdo.file.domain.TbContactFilesDO;
import com.bootdo.file.service.TbContactFilesService;



@Service
public class TbContactFilesServiceImpl implements TbContactFilesService {
	@Autowired
	private TbContactFilesDao tbContactFilesDao;
	
	@Override
	public TbContactFilesDO get(String id){
		return tbContactFilesDao.get(id);
	}
	
	@Override
	public List<TbContactFilesDO> list(Map<String, Object> map){
		return tbContactFilesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return tbContactFilesDao.count(map);
	}
	
	@Override
	public int save(TbContactFilesDO tbContactFiles){
		return tbContactFilesDao.save(tbContactFiles);
	}
	
	@Override
	public int update(TbContactFilesDO tbContactFiles){
		return tbContactFilesDao.update(tbContactFiles);
	}
	
	@Override
	public int remove(String id){
		return tbContactFilesDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return tbContactFilesDao.batchRemove(ids);
	}
	
}

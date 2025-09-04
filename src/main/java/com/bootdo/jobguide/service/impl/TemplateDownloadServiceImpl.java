package com.bootdo.jobguide.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.jobguide.dao.TemplateDownloadDao;
import com.bootdo.jobguide.domain.TemplateDownloadDO;
import com.bootdo.jobguide.service.TemplateDownloadService;



@Service
public class TemplateDownloadServiceImpl implements TemplateDownloadService {
	@Autowired
	private TemplateDownloadDao templatedownloadDao;
	
	@Override
	public TemplateDownloadDO get(String id){
		return templatedownloadDao.get(id);
	}
	
	@Override
	public List<TemplateDownloadDO> list(Map<String, Object> map){
		return templatedownloadDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return templatedownloadDao.count(map);
	}
	
	@Override
	public int save(TemplateDownloadDO templatedownload){
		return templatedownloadDao.save(templatedownload);
	}
	
	@Override
	public int update(TemplateDownloadDO templatedownload){
		return templatedownloadDao.update(templatedownload);
	}
	
	@Override
	public int remove(String id){
		return templatedownloadDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return templatedownloadDao.batchRemove(ids);
	}
	
}

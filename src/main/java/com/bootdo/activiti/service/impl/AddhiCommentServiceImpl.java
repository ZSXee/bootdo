package com.bootdo.activiti.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.activiti.dao.AddhiCommentDao;
import com.bootdo.activiti.domain.AddhiCommentDO;
import com.bootdo.activiti.service.AddhiCommentService;



@Service
public class AddhiCommentServiceImpl implements AddhiCommentService {
	@Autowired
	private AddhiCommentDao addhiCommentDao;
	
	@Override
	public AddhiCommentDO get(String id){
		return addhiCommentDao.get(id);
	}
	
	@Override
	public List<AddhiCommentDO> list(Map<String, Object> map){
		return addhiCommentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return addhiCommentDao.count(map);
	}
	
	@Override
	public int save(AddhiCommentDO addhiComment){
		return addhiCommentDao.save(addhiComment);
	}
	
	@Override
	public int update(AddhiCommentDO addhiComment){
		return addhiCommentDao.update(addhiComment);
	}
	
	@Override
	public int remove(String id){
		return addhiCommentDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return addhiCommentDao.batchRemove(ids);
	}
	
}

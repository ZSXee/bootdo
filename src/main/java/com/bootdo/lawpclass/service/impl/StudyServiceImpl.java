package com.bootdo.lawpclass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.lawpclass.dao.StudyDao;
import com.bootdo.lawpclass.domain.StudyDO;
import com.bootdo.lawpclass.service.StudyService;



@Service
public class StudyServiceImpl implements StudyService {
	@Autowired
	private StudyDao studyDao;
	
	@Override
	public StudyDO get(String id){
		return studyDao.get(id);
	}
	
	@Override
	public List<StudyDO> list(Map<String, Object> map){
		return studyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studyDao.count(map);
	}
	
	@Override
	public int save(StudyDO study){
		return studyDao.save(study);
	}
	
	@Override
	public int update(StudyDO study){
		return studyDao.update(study);
	}
	
	@Override
	public int remove(String id){
		return studyDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studyDao.batchRemove(ids);
	}
	
}

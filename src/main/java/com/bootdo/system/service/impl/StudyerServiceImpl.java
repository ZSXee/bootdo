package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.StudyerDao;
import com.bootdo.system.domain.StudyerDO;
import com.bootdo.system.service.StudyerService;



@Service
public class StudyerServiceImpl implements StudyerService {
	@Autowired
	private StudyerDao studyerDao;
	
	@Override
	public StudyerDO get(String id){
		return studyerDao.get(id);
	}
	
	@Override
	public List<StudyerDO> list(Map<String, Object> map){
		return studyerDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return studyerDao.count(map);
	}
	
	@Override
	public int save(StudyerDO study){
		return studyerDao.save(study);
	}
	
	@Override
	public int update(StudyerDO study){
		return studyerDao.update(study);
	}
	
	@Override
	public int remove(String id){
		return studyerDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return studyerDao.batchRemove(ids);
	}
	
}

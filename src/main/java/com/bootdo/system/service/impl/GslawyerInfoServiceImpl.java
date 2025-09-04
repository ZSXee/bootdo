package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.GslawyerInfoDao;
import com.bootdo.system.domain.GslawyerInfoDO;
import com.bootdo.system.service.GslawyerInfoService;



@Service
public class GslawyerInfoServiceImpl implements GslawyerInfoService {
	@Autowired
	private GslawyerInfoDao gslawyerInfoDao;

	@Override
	public GslawyerInfoDO get(String number){
		return gslawyerInfoDao.get(number);
	}

	@Override
	public List<GslawyerInfoDO> list(Map<String, Object> map){
		return gslawyerInfoDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return gslawyerInfoDao.count(map);
	}

	@Override
	public int save(GslawyerInfoDO gslawyerInfo){
		return gslawyerInfoDao.save(gslawyerInfo);
	}

	@Override
	public int update(GslawyerInfoDO gslawyerInfo){
		return gslawyerInfoDao.update(gslawyerInfo);
	}

	@Override
	public int remove(Integer number){
		return gslawyerInfoDao.remove(number);
	}

	@Override
	public int batchRemove(Integer[] numbers){
		return gslawyerInfoDao.batchRemove(numbers);
	}

}

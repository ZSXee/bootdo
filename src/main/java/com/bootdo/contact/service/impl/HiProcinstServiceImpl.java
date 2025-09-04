package com.bootdo.contact.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.common.service.DictService;
import com.bootdo.contact.dao.HiProcinstDao;
import com.bootdo.contact.domain.HiProcinstDO;
import com.bootdo.contact.service.HiProcinstService;
import com.bootdo.oa.domain.NotifyDO;



@Service
public class HiProcinstServiceImpl implements HiProcinstService {
	@Autowired
	private HiProcinstDao hiProcinstDao;
    @Autowired
    private DictService dictService;
	
	@Override
	public HiProcinstDO get(String id){
		return hiProcinstDao.get(id);
	}
	
	@Override
	public List<HiProcinstDO> list(Map<String, Object> map){
		
//        List<HiProcinstDO> list = hiProcinstDao.list(map);
//        for (HiProcinstDO hiProcinstDO : list) {
//        	hiProcinstDO.setCntType(dictService.getName("cntType", hiProcinstDO.getCntType()));
//        }
//		
		return hiProcinstDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return hiProcinstDao.count(map);
	}

	@Override
	public List<HiProcinstDO> list() {
		return hiProcinstDao.list();
	}
	
}

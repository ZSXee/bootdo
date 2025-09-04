package com.bootdo.contact.service;

import com.bootdo.contact.domain.HiProcinstDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-30 15:04:37
 */
public interface HiProcinstService {
	
	HiProcinstDO get(String id);
	
	List<HiProcinstDO> list(Map<String, Object> map);
	
	List<HiProcinstDO> list();
	
	int count(Map<String, Object> map);

}

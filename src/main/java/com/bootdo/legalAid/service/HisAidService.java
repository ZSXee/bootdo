package com.bootdo.legalAid.service;

import com.bootdo.legalAid.domain.HisAidDO;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-30 15:04:37
 */
public interface HisAidService {
	
	HisAidDO get(String id);
	
	List<HisAidDO> list(Map<String, Object> map);
	
	List<HisAidDO> list();
	
	int count(Map<String, Object> map);

}

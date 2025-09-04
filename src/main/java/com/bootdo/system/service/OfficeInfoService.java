package com.bootdo.system.service;

import com.bootdo.system.domain.OfficeInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-06-12 08:54:14
 */
public interface OfficeInfoService {
	
	OfficeInfoDO get(String cntId);
	
	List<OfficeInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OfficeInfoDO officeInfo);
	
	int update(OfficeInfoDO officeInfo);
	
	int remove(String cntId);
	
	int batchRemove(String[] cntIds);
}

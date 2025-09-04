package com.bootdo.legalAid.service;

import com.bootdo.legalAid.domain.LegalAidDO;
import com.bootdo.system.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 法律援助信息表
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-08-13 09:26:47
 */
public interface LegalAidService {
	
	LegalAidDO get(String aidId);
	
	LegalAidDO getByProc(String procId);
	
	List<LegalAidDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LegalAidDO legalAid);
	
	int update(LegalAidDO legalAid);
	
	int remove(String aidId);
	
	int batchRemove(String[] aidIds);

	int update(LegalAidDO legalAid, UserDO userDO);

	void commit(LegalAidDO legalAid, UserDO userDO);
}

package com.bootdo.contact.service;

import com.bootdo.contact.domain.TbLaywerDO;
import com.bootdo.system.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 合同信息表
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-12 11:03:48
 */
public interface TbLaywerService {
	
	TbLaywerDO get(String cntId);
	
	List<TbLaywerDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TbLaywerDO tbLaywer);
	
	int remove(String cntId);
	
	int batchRemove(String[] cntIds);

	int update(TbLaywerDO tbLaywer, UserDO userDO);

	int save(TbLaywerDO tbLaywer, UserDO userDO);

	void commit(TbLaywerDO tbLaywer, UserDO userDO);
}

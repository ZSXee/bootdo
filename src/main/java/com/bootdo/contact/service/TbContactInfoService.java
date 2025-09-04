package com.bootdo.contact.service;

import com.bootdo.contact.domain.TbContactInfoDO;
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
public interface TbContactInfoService {
	
	TbContactInfoDO get(String cntId);
	
	
	List<TbContactInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TbContactInfoDO tbContactInfo);
	
	int remove(String cntId);
	
	int batchRemove(String[] cntIds);

	int update(TbContactInfoDO tbContactInfo, UserDO userDO);

	int save(TbContactInfoDO tbContactInfo, UserDO userDO);

	void commit(TbContactInfoDO tbContactInfo, UserDO userDO);
}

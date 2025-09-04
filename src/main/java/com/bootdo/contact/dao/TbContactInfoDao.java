package com.bootdo.contact.dao;

import com.bootdo.contact.domain.TbContactInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 合同信息表
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-12 11:03:48
 */
@Mapper
public interface TbContactInfoDao {

	TbContactInfoDO get(String cntId);
	
	List<TbContactInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TbContactInfoDO tbContactInfo);
	
	int update(TbContactInfoDO tbContactInfo);
	
	int remove(String CNT_ID);
	
	int batchRemove(String[] cntIds);
}

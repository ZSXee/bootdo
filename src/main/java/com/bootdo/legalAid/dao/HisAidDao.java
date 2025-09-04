package com.bootdo.legalAid.dao;

import com.bootdo.legalAid.domain.HisAidDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-30 15:04:37
 */
@Mapper
public interface HisAidDao {

	HisAidDO get(String id);
	
	List<HisAidDO> list(Map<String,Object> map);
	
	List<HisAidDO> list();
	
	int count(Map<String,Object> map);
	
	int save(HisAidDO hisAid);
	
	int update(HisAidDO hisAid);
	
	int remove(String ID_);
	
	int batchRemove(String[] ids);
}

package com.bootdo.system.dao;

import com.bootdo.system.domain.OfficeInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-06-12 08:54:14
 */
@Mapper
public interface OfficeInfoDao {

	OfficeInfoDO get(String cntId);
	
	List<OfficeInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OfficeInfoDO officeInfo);
	
	int update(OfficeInfoDO officeInfo);
	
	int remove(String cntId);
	
	int batchRemove(String[] cntIds);
}

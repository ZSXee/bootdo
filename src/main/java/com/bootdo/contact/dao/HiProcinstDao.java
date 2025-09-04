package com.bootdo.contact.dao;

import com.bootdo.contact.domain.HiProcinstDO;

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
public interface HiProcinstDao {

	HiProcinstDO get(String id);
	
	List<HiProcinstDO> list(Map<String,Object> map);
	
	List<HiProcinstDO> list();
	
	int count(Map<String,Object> map);
	
	int save(HiProcinstDO hiProcinst);
	
	int update(HiProcinstDO hiProcinst);
	
	int remove(String ID_);
	
	int batchRemove(String[] ids);
}

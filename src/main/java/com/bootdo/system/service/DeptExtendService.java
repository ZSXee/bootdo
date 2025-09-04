package com.bootdo.system.service;

import com.bootdo.system.domain.DeptExtendDO;

import java.util.List;
import java.util.Map;

/**
 * 机构信息扩展表
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-29 15:17:22
 */
public interface DeptExtendService {
	
	DeptExtendDO get(Long deptIdE);
	
	List<DeptExtendDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeptExtendDO deptExtend);
	
	int update(DeptExtendDO deptExtend);
	
	int remove(Long deptIdE);
	
	int batchRemove(Long[] deptIdEs);
}

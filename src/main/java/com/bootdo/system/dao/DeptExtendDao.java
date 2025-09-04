package com.bootdo.system.dao;

import com.bootdo.system.domain.DeptExtendDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 机构信息扩展表
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-29 15:17:22
 */
@Mapper
public interface DeptExtendDao {

	DeptExtendDO get(Long deptIdE);
	
	List<DeptExtendDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(DeptExtendDO deptExtend);
	
	int update(DeptExtendDO deptExtend);
	
	int remove(Long DEPT_ID_E);
	
	int batchRemove(Long[] deptIdEs);
}

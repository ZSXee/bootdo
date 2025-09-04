package com.bootdo.system.dao;

import com.bootdo.system.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserDao {

	UserDO get(Long userId);
	
	List<UserDO> list(Map<String,Object> map);
	
	List<String> listByRoleDept(Map<String,Object> map);
	List<String> listByRoleDept1(Map<String,Object> map);

	
	int count(Map<String,Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();

	UserDO getUserName(String username);

	List<UserDO> listUser();

	List<UserDO> listByDeptType(Map<String, Object> map);
	List<UserDO> listByDeptType1(Map<String, Object> map);

	
	int countByDeptType(Map<String,Object> map);

}

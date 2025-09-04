package com.bootdo.htdzk.dao;

import com.bootdo.htdzk.domain.GshtdzkDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 格式合同电子库
 * @author zcf
 * @email zcf@163.com
 * @date 2020-05-22
 */
@Mapper
public interface GshtdzkDao {

	GshtdzkDO get(String id);
	
	List<GshtdzkDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GshtdzkDO gshtdzk);
	
	int update(GshtdzkDO gshtdzk);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

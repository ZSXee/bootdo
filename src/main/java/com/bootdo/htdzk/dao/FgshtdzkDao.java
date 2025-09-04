package com.bootdo.htdzk.dao;

import com.bootdo.htdzk.domain.FgshtdzkDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 非格式合同电子库
 * @author zcf
 * @email zcf@163.com
 * @date 2020-05-22
 */
@Mapper
public interface FgshtdzkDao {

	FgshtdzkDO get(String id);
	
	List<FgshtdzkDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(FgshtdzkDO fgshtdzk);
	
	int update(FgshtdzkDO fgshtdzk);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

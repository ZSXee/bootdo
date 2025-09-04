package com.bootdo.file.dao;

import com.bootdo.file.domain.TbContactFilesDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 合同附件
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-27 08:55:20
 */
@Mapper
public interface TbContactFilesDao {

	TbContactFilesDO get(String id);
	
	List<TbContactFilesDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TbContactFilesDO tbContactFiles);
	
	int update(TbContactFilesDO tbContactFiles);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

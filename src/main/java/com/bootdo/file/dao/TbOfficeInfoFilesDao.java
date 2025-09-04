package com.bootdo.file.dao;

import com.bootdo.file.domain.TbOfficeInfoFilesDO;

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
public interface TbOfficeInfoFilesDao {

	TbOfficeInfoFilesDO get(String id);
	
	List<TbOfficeInfoFilesDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TbOfficeInfoFilesDO tbOfficeInfoFiles);
	
	int update(TbOfficeInfoFilesDO tbOfficeInfoFiles);
	
	int remove(String id);
	
	int batchRemove(String[] id);
}

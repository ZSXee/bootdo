package com.bootdo.file.service;

import com.bootdo.file.domain.TbLaywerFilesDO;

import java.util.List;
import java.util.Map;

/**
 * 合同附件
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-27 08:55:20
 */
public interface TbLaywerFilesService {
	
	TbLaywerFilesDO get(String id);
	
	List<TbLaywerFilesDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TbLaywerFilesDO tbLaywerFiles);
	
	int update(TbLaywerFilesDO tbLaywerFiles);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

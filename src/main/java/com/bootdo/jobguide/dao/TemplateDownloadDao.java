package com.bootdo.jobguide.dao;

import com.bootdo.jobguide.domain.TemplateDownloadDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 学习培训
 * @author zcf
 * @email zcf@163.com
 * @date 2020-05-22
 */
@Mapper
public interface TemplateDownloadDao {

	TemplateDownloadDO get(String id);
	
	List<TemplateDownloadDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TemplateDownloadDO templatedownload);
	
	int update(TemplateDownloadDO templatedownload);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

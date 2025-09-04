package com.bootdo.jobguide.service;

import com.bootdo.jobguide.domain.TemplateDownloadDO;

import java.util.List;
import java.util.Map;

/**
 * 学习培训
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public interface TemplateDownloadService {
	
	TemplateDownloadDO get(String id);
	
	List<TemplateDownloadDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TemplateDownloadDO templatedownload);
	
	int update(TemplateDownloadDO templatedownload);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

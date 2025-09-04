package com.bootdo.contact.service;

import com.bootdo.contact.domain.ContactTemplatesDO;

import java.util.List;
import java.util.Map;

/**
 * 合同模板
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-30 09:38:20
 */
public interface ContactTemplatesService {
	
	ContactTemplatesDO get(String id);
	
	List<ContactTemplatesDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContactTemplatesDO contactTemplates);
	
	int update(ContactTemplatesDO contactTemplates);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

package com.bootdo.contact.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.contact.dao.ContactTemplatesDao;
import com.bootdo.contact.domain.ContactTemplatesDO;
import com.bootdo.contact.service.ContactTemplatesService;



@Service
public class ContactTemplatesServiceImpl implements ContactTemplatesService {
	@Autowired
	private ContactTemplatesDao contactTemplatesDao;
	
	@Override
	public ContactTemplatesDO get(String id){
		return contactTemplatesDao.get(id);
	}
	
	@Override
	public List<ContactTemplatesDO> list(Map<String, Object> map){
		return contactTemplatesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return contactTemplatesDao.count(map);
	}
	
	@Override
	public int save(ContactTemplatesDO contactTemplates){
		return contactTemplatesDao.save(contactTemplates);
	}
	
	@Override
	public int update(ContactTemplatesDO contactTemplates){
		return contactTemplatesDao.update(contactTemplates);
	}
	
	@Override
	public int remove(String id){
		return contactTemplatesDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return contactTemplatesDao.batchRemove(ids);
	}
	
}

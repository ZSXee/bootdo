package com.bootdo.lawpclass.service;

import com.bootdo.lawpclass.domain.LawsRegulationsDO;

import java.util.List;
import java.util.Map;

/**
 * 法律法规解读
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2020-05-28 09:38:20
 */
public interface LawsRegulationsService {
	
	LawsRegulationsDO get(String id);
	
	List<LawsRegulationsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LawsRegulationsDO lawsregulations);
	
	int update(LawsRegulationsDO lawsregulations);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

package com.bootdo.lawpclass.dao;

import com.bootdo.lawpclass.domain.LawsRegulationsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 法律法规解读
 * @author zcf
 * @email zcf@163.com
 * @date 2020-05-28
 */
@Mapper
public interface LawsRegulationsDao {

	LawsRegulationsDO get(String id);
	
	List<LawsRegulationsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LawsRegulationsDO lawsregulations);
	
	int update(LawsRegulationsDO lawsregulations);
	
	int remove(String ID);
	
	int batchRemove(String[] ids);
}

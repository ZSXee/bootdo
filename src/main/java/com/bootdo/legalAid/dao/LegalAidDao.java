package com.bootdo.legalAid.dao;

import com.bootdo.legalAid.domain.LegalAidDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 法律援助信息表
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-08-13 09:26:47
 */
@Mapper
public interface LegalAidDao {

	LegalAidDO get(String aidId);
	
	LegalAidDO getByProc(String procId);
	
	List<LegalAidDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(LegalAidDO legalAid);
	
	int update(LegalAidDO legalAid);
	
	int remove(String AID_ID);
	
	int batchRemove(String[] aidIds);
}

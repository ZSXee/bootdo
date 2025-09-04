package com.bootdo.system.dao;

import com.bootdo.system.domain.GslawyerInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-06-17 15:01:04
 */
@Mapper
public interface GslawyerInfoDao {

	GslawyerInfoDO get(String number);

	List<GslawyerInfoDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(GslawyerInfoDO gslawyerInfo);

	int update(GslawyerInfoDO gslawyerInfo);

	int remove(Integer number);

	int batchRemove(Integer[] numbers);
}

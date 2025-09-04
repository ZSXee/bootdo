package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.dao.DeptExtendDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.DeptExtendDO;
import com.bootdo.system.service.DeptService;



@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao sysDeptMapper;
	@Autowired
	private DeptExtendDao sysDeptExtendMapper;

	@Override
	public DeptDO get(Long deptId){
		DeptDO deptDO = sysDeptMapper.get(deptId);
		DeptExtendDO deptExtendDO = sysDeptExtendMapper.get(deptId);
		
		deptDO.setDeptType(deptExtendDO.getDeptType());
		deptDO.setReserve1(deptExtendDO.getReserve1());
		return deptDO;
	}

	@Override
	public DeptDO getHG(Map<String, Object> map) {
		return sysDeptMapper.getHG(map);
	}

	
	@Override
	public List<DeptDO> list(Map<String, Object> map){
		return sysDeptMapper.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return sysDeptMapper.count(map);
	}

	@Override
	public int save(DeptDO sysDept){
		//设置机构扩展表信息
		DeptExtendDO deptExtendDO = new DeptExtendDO();
		deptExtendDO.setDeptIdE(sysDept.getDeptId());
		deptExtendDO.setDeptType(sysDept.getDeptType());
		deptExtendDO.setReserve1(sysDept.getReserve1());
		sysDeptExtendMapper.save(deptExtendDO);
		return sysDeptMapper.save(sysDept);
	}

	@Override
	public int update(DeptDO sysDept){
		//设置机构扩展表信息
		DeptExtendDO deptExtendDO = new DeptExtendDO();
		deptExtendDO.setDeptIdE(sysDept.getDeptId());
		deptExtendDO.setDeptType(sysDept.getDeptType());
		deptExtendDO.setReserve1(sysDept.getReserve1());
		sysDeptExtendMapper.update(deptExtendDO);
		
		return sysDeptMapper.update(sysDept);
	}

	@Override
	public int remove(Long deptId){
		sysDeptExtendMapper.remove(deptId);
		return sysDeptMapper.remove(deptId);
	}

	@Override
	public int batchRemove(Long[] deptIds){
		sysDeptExtendMapper.batchRemove(deptIds);
		return sysDeptMapper.batchRemove(deptIds);
	}

	@Override
	public Tree<DeptDO> getTree() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String,Object>(16));
		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", false);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}
	@Override
	//获取总行部室
	public Tree<DeptDO> getTreeZh() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> sysDepts = sysDeptMapper.listzh();
		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId("1");
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		Map<String, Object> state = new HashMap<>(16);
		Tree<DeptDO> tree = new Tree<DeptDO>();
		state.put("opened", true);
		tree.setState(state);
		tree.setId("1");
		tree.setText("莱商银行");
		tree.setParentId("0");
		trees.add(tree);
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}
	//获取分行部室
	public Tree<DeptDO> getTreeFh(Long parentID, Long deptID, String name) {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> sysDepts = sysDeptMapper.listfh(parentID);
		for (DeptDO sysDept : sysDepts) {
			//排除自己
//			if(sysDept.getDeptId()==deptID)
//				continue;
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId("1");
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		Map<String, Object> state = new HashMap<>(16);
		Tree<DeptDO> tree = new Tree<DeptDO>();
		state.put("opened", true);
		tree.setState(state);
		tree.setId("1");
		tree.setText(name);
		tree.setParentId("0");
		trees.add(tree);
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}
	@Override
	public boolean checkDeptHasUser(Long deptId) {
		// TODO Auto-generated method stub
		//查询部门以及此部门的下级部门
		int result = sysDeptMapper.getDeptUserNumber(deptId);
		return result==0?true:false;
	}

	public Tree<DeptDO> getTreeZhih(Long parentID, Long deptID, String name) {	
	List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
	List<DeptDO> sysDepts = sysDeptMapper.listzhih(parentID);
	for (DeptDO sysDept : sysDepts) {
		//排除自己
		//if(sysDept.getDeptId()==deptID)
		//	continue;
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree.setId(sysDept.getDeptId().toString());
		tree.setParentId("1");
		tree.setText(sysDept.getName());
		Map<String, Object> state = new HashMap<>(16);
		state.put("opened", true);
		tree.setState(state);
		trees.add(tree);
	}
	Map<String, Object> state = new HashMap<>(16);
	Tree<DeptDO> tree = new Tree<DeptDO>();
	state.put("opened", true);
	tree.setState(state);
	tree.setId("1");
	tree.setText(name);
	tree.setParentId("0");
	trees.add(tree);
	// 默认顶级菜单为０，根据数据库实际情况调整
	Tree<DeptDO> t = BuildTree.build(trees);
	return t;
}
}

package com.bootdo.system.controller;

import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.DeptExtendDO;
import com.bootdo.system.service.DeptExtendService;
import com.bootdo.system.service.DeptService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:40:36
 */

@Controller
@RequestMapping("/system/sysDept")
public class DeptController extends BaseController {
	private String prefix = "system/dept";
	@Autowired
	private DeptService sysDeptService;
	@Autowired
	private DeptExtendService deptExtendService;
	@Autowired
	DictService dictService;
	

	@GetMapping()
	@RequiresPermissions("system:sysDept:sysDept")
	String dept() {
		return prefix + "/dept";
	}

	@ApiOperation(value="获取部门列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:sysDept:sysDept")
	public List<DeptDO> list() {
		Map<String, Object> query = new HashMap<>(16);
		List<DeptDO> sysDeptList = sysDeptService.list(query);
		return sysDeptList;
	}

	@GetMapping("/add/{pId}")
	@RequiresPermissions("system:sysDept:add")
	String add(@PathVariable("pId") Long pId, Model model) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "总部门");
		} else {
			model.addAttribute("pName", sysDeptService.get(pId).getName());
		}
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("deptType");
		model.addAttribute("deptTypes",dictDOS);
		
		return  prefix + "/add";
	}

	@GetMapping("/edit/{deptId}")
	@RequiresPermissions("system:sysDept:edit")
	String edit(@PathVariable("deptId") Long deptId, Model model) {
		DeptDO sysDept = sysDeptService.get(deptId);
		model.addAttribute("sysDept", sysDept);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("deptType");
		Long type = sysDept.getDeptType();
		for (DictDO dictDO:dictDOS){
			if(type.toString().equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("deptTypes",dictDOS);
		
		if(Constant.DEPT_ROOT_ID.equals(sysDept.getParentId())) {
			model.addAttribute("parentDeptName", "无");
		}else {
			DeptDO parDept = sysDeptService.get(sysDept.getParentId());
			model.addAttribute("parentDeptName", parDept.getName());
		}
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:sysDept:add")
	public R save(DeptDO sysDept) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		sysDept.setDeptId(new Long(sysDept.getReserve1()));
		if (sysDeptService.save(sysDept) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:sysDept:edit")
	public R update(DeptDO sysDept) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (sysDeptService.update(sysDept) > 0) {
			DeptExtendDO deptExtend = new DeptExtendDO();
			deptExtend.setDeptIdE(sysDept.getDeptId());
			deptExtend.setDeptType(sysDept.getDeptType());
			deptExtendService.update(deptExtend);
			
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:remove")
	public R remove(Long deptId) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", deptId);
		if(sysDeptService.count(map)>0) {
			return R.error(1, "包含下级部门,不允许修改");
		}
		if(sysDeptService.checkDeptHasUser(deptId)) {
			if (sysDeptService.remove(deptId) > 0) {
				deptExtendService.remove(deptId);
				return R.ok();
			}
		}else {
			return R.error(1, "部门包含用户,不允许修改");
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] deptIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		sysDeptService.batchRemove(deptIds);
		return R.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		DeptDO dept = sysDeptService.get(this.getUser().getDeptId());
		DeptDO deptParent = sysDeptService.get(dept.getParentId());
		//机构类型0：分行 1：总行部室2：分行部室3：支行4：总行合规风险部5：分行合规风险部
		if(dept.getDeptType()==5){
			tree = sysDeptService.getTreeZhih(dept.getParentId(),dept.getDeptId(),deptParent.getName());
		}else{
			tree = sysDeptService.getTree();
		}
		return tree;
	}
	
	@GetMapping("/treezh")
	@ResponseBody
	public Tree<DeptDO> treezh() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		
		
		DeptDO dept = sysDeptService.get(this.getUser().getDeptId());
		
		DeptDO deptParent = sysDeptService.get(dept.getParentId());
		//机构类型0：分行 1：总行部室2：分行部室3：支行4：总行合规风险部5：分行合规风险部
		if(dept.getDeptType()==1||dept.getDeptType()==4)
			tree = sysDeptService.getTreeZh();
		else{
			tree = sysDeptService.getTreeFh(dept.getParentId(),dept.getDeptId(),deptParent.getName());
		}
		
		return tree;
	}
	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/deptTree";
	}

	@GetMapping("/treeView2")
	String treeView2() {
		return  prefix + "/deptTree2";
	}
}

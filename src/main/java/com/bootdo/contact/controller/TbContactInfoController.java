package com.bootdo.contact.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.activiti.domain.AddhiCommentDO;
import com.bootdo.activiti.service.AddhiCommentService;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.service.RoleService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.contact.domain.TbContactInfoDO;
import com.bootdo.contact.service.TbContactInfoService;
import com.bootdo.file.domain.TbContactFilesDO;
import com.bootdo.file.service.TbContactFilesService;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.domain.UserRoleDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.system.service.UserService;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 合同信息表
 *
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-12 11:03:48
 */

@Controller
@RequestMapping("/contact/tbContactInfo")
public class TbContactInfoController extends BaseController{
	@Autowired
	TbContactInfoService tbContactInfoService;
	@Autowired
	TbContactFilesService tbContactFilesService;
    @Autowired
    ActivitiUtils activitiUtils;
	@Autowired
	UserService userService;
    @Autowired
    UserRoleDao userRoleMapper;
	@Autowired
	DeptDao sysDeptMapper;
	@Autowired
	DictService dictService;
    @Autowired
    HistoryService historyService;
	@Autowired
	DeptService sysDeptService;
	@Autowired
	RoleService roleService;
	@Autowired
	AddhiCommentService addhiCommentService;

	@GetMapping()
	@RequiresPermissions("contact:tbContactInfo:tbContactInfo")
	String TbContactInfo(){
	    return "contact/tbContactInfo/tbContactInfo";
	}

	@GetMapping("/comment/{procInsId}")
	String comment(@PathVariable("procInsId") String procInsId){
    	UserDO userDO  = userService.get(getUserId());

		HistoricProcessInstance ins = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInsId).singleResult();
		UserDO userDO1  = userService.get(ins.getStartUserId());

		List<RoleDO> roleList = roleService.list(userDO.getUserId());
		Set<Long> roles = new HashSet<>();
		for (RoleDO item : roleList) {
			roles.add(item.getRoleId());
		}

		DeptDO deptDo = sysDeptMapper.get(userDO.getDeptId());
		//是否为发起部门负责人(返回的批注页面不同)
		if((userDO.getDeptId().longValue()==userDO1.getDeptId().longValue())&&roles.contains(3))
			return "contact/tbContactInfo/comment1";
		//员工、总行合规风险部、分行合规风险部
		else if(roles.contains(2)||deptDo.getDeptType()==4||deptDo.getDeptType()==5){
			return "contact/tbContactInfo/comment3";
		}
		else
			return "contact/tbContactInfo/comment2";

	}

	@GetMapping("/scanComment/{type}")
	String scanComment(@PathVariable("type") String type ){

		if(type.equals("12"))
			return "contact/tbContactInfo/comment12";
		else if(type.equals("11")){
			return "contact/tbContactInfo/comment11";
		}
		else
			return "contact/tbContactInfo/comment13";

	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contact:tbContactInfo:tbContactInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TbContactInfoDO> tbContactInfoList = tbContactInfoService.list(query);
		int total = tbContactInfoService.count(query);
		PageUtils pageUtils = new PageUtils(tbContactInfoList, total);
		return pageUtils;
	}

	@GetMapping("/add/{key}")
	@RequiresPermissions("contact:tbContactInfo:add")
	String add(Model model, @PathVariable("key") String key){
		model.addAttribute("cntId", UUID.randomUUID().toString());
		model.addAttribute("key", key);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("cntType");
		model.addAttribute("cntTypes",dictDOS);

	    //return "contact/tbContactInfo/add";
	    //return "contact/tbContactInfo/add_gsht";
		if (key.substring(0,4).equals("gesh"))
			return "contact/tbContactInfo/add_gsht";
		else if (key.substring(0,4).equals("wpls"))
				return "contact/tbContactInfo/add_wpls";
		else if (key.substring(4,6).equals("wb"))
				return "contact/tbContactInfo/add_hfls";
		else if (key.substring(0,4).equals("zyzg"))
				return "contact/tbContactInfo/add_zyzg";
		else if (key.substring(0,4).equals("flhg"))
				return "contact/tbContactInfo/add_flhg";

		else if (key.substring(0,4).equals("fhfl"))
			return "contact/tbContactInfo/add_zyzg";

		else if(key.substring(0,4).equals("zhbm"))
			return "contact/tbContactInfo/add_wpls";

		else
			return "contact/tbContactInfo/add";
	}
	/**
	 * 处理
	 */
	@GetMapping("/edit/{taskId}/{processId}/{processDefinitionId}")
	//@RequiresPermissions("contact:tbContactInfo:edit")
	String edit(@PathVariable("taskId") String taskId ,@PathVariable("processId") String processId , @PathVariable("processDefinitionId") String processDefinitionId ,Model model){
		TbContactInfoDO tbContactInfo = tbContactInfoService.get(processId);

		tbContactInfo.setTaskId(taskId);

        UserDO userDO  = userService.get(getUserId());
        //获取用户角色
        List<Long> list = userRoleMapper.listRoleId(userDO.getUserId());

        DeptDO sysDept = sysDeptService.get(userDO.getDeptId());
        //只有合规风险部的员工可以再次设置是否会签
        if((sysDept.getDeptType()==4||sysDept.getDeptType()==5)&&(list.get(0)==2||list.get(0)==6)){
        	model.addAttribute("countersign", "on");
        }
        //只有合规风险部的人员可以编辑批注附件
        if(sysDept.getDeptType()==4||sysDept.getDeptType()==5){
        	model.addAttribute("comment_file", "on");
        }

		//添加字典
		List<DictDO> dictDOS = dictService.listByType("cntType");
		String type = tbContactInfo.getCntType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}

		}
		model.addAttribute("cntTypes",dictDOS);
		model.addAttribute("tbContactInfo", tbContactInfo);

	    //return "contact/tbContactInfo/edit";
		if (processDefinitionId.substring(0,4).equals("gesh"))
			return "contact/tbContactInfo/edit_gsht";
		else
			if (processDefinitionId.substring(0,4).equals("wpls"))
				return "contact/tbContactInfo/edit_wpls";
			else
				if (processDefinitionId.substring(4,6).equals("wb"))
					return "contact/tbContactInfo/edit_hfls";

			else
				if (processDefinitionId.substring(0,4).equals("zyzg"))
					return "contact/tbContactInfo/edit_zyzg";
				else
					if (processDefinitionId.substring(0,4).equals("flhg"))
						return "contact/tbContactInfo/edit_flhg";

					else
						if (processDefinitionId.substring(0,4).equals("fhfl"))
							return "contact/tbContactInfo/edit_zyzg";
						else
							if(processDefinitionId.substring(0,4).equals("zhbm"))
								return "contact/tbContactInfo/edit_wpls";

					else
			return "contact/tbContactInfo/edit";
	}
	/**
	 * 查看
	 */
	@GetMapping("/scan/{instanceId}/{processDefinitionId}")
	//@RequiresPermissions("contact:tbContactInfo:edit")
	String scan(@PathVariable("instanceId") String instanceId ,@PathVariable("processDefinitionId") String processDefinitionId , Model model){
		TbContactInfoDO tbContactInfo = tbContactInfoService.get(instanceId);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("cntType");
		if(tbContactInfo != null){
			String type = tbContactInfo.getCntType();
			for (DictDO dictDO:dictDOS){
				if(type.equals(dictDO.getValue())){
					dictDO.setRemarks("checked");
				}
			}
		}

		model.addAttribute("cntTypes",dictDOS);
        model.addAttribute("tbContactInfo", tbContactInfo);
        //return "contact/tbContactInfo/scan";
		UserDO userDO  = userService.get(getUserId());
		//获取用户角色
		List<Long> list = userRoleMapper.listRoleId(userDO.getUserId());
		DeptDO sysDept = sysDeptService.get(userDO.getDeptId());
		//只有合规风险部的部门负责人可以再次编辑
		if((sysDept.getDeptType()==4)&&(list.get(0)==3)){
			if(processDefinitionId.substring(0,4).equals("fhfl"))
				return "contact/tbContactInfo/scan_zyzg";
			else
			    return "contact/tbContactInfo/scan_hgfzr";
		}

       if (processDefinitionId.substring(0,4).equals("gesh"))
			return "contact/tbContactInfo/scan_gsht";
       else if(processDefinitionId.substring(0,4).equals("wpls"))
			return "contact/tbContactInfo/scan_wpls";
       else if(processDefinitionId.substring(4,6).equals("wb"))
			return "contact/tbContactInfo/scan_hfls";
       else if(processDefinitionId.substring(0,4).equals("zyzg"))
			return "contact/tbContactInfo/scan_zyzg";

       else if(processDefinitionId.substring(0,4).equals("fhfl"))
			return "contact/tbContactInfo/scan_zyzg";

       else if(processDefinitionId.substring(0,4).equals("flhg"))
			return "contact/tbContactInfo/scan_flhg";

       else if(processDefinitionId.substring(0,4).equals("zhbm"))
			return "contact/tbContactInfo/scan_wpls";
       else
    	   return "contact/tbContactInfo/scan";

	}
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contact:tbContactInfo:add")
	public R save( TbContactInfoDO tbContactInfo){
		UserDO userDO  = userService.get(getUserId());
		tbContactInfo.setCntAdmin(getUserId().toString());
		if(tbContactInfoService.save(tbContactInfo, userDO)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@GetMapping("/update/{taskId}/{processId}/{processDefinitionId}")
	String update(@PathVariable("taskId") String taskId ,@PathVariable("processId") String processId,@PathVariable("processDefinitionId") String processDefinitionId , Model model){
		TbContactInfoDO tbContactInfo = tbContactInfoService.get(processId);

		tbContactInfo.setTaskId(taskId);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("cntType");
		String type = tbContactInfo.getCntType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("cntTypes",dictDOS);
        model.addAttribute("tbContactInfo", tbContactInfo);
	    //return "contact/tbContactInfo/update";
       if (processDefinitionId.substring(0,4).equals("gesh"))
			return "contact/tbContactInfo/update_gsht";
		else
			if (processDefinitionId.substring(0,4).equals("wpls"))
				return "contact/tbContactInfo/update_wpls";
			else
				if (processDefinitionId.substring(4,6).equals("wb"))
					return "contact/tbContactInfo/update_hfls";
			else
				if (processDefinitionId.substring(0,4).equals("zyzg"))
					return "contact/tbContactInfo/update_zyzg";

				else
					if (processDefinitionId.substring(0,4).equals("fhfl"))
						return "contact/tbContactInfo/update_zyzg";

				else
					if (processDefinitionId.substring(0,4).equals("flhg"))
						return "contact/tbContactInfo/update_flhg";

					else
						if (processDefinitionId.substring(0,4).equals("zhbm"))
							return "contact/tbContactInfo/update_wpls";

					else
			return "contact/tbContactInfo/update";
	}
	/**
	 * 提交
	 */
	@ResponseBody
	@RequestMapping("/commit")
	public R commit( TbContactInfoDO tbContactInfo){
		UserDO userDO  = userService.get(getUserId());

		//更新批注附件状态为1有效
		TbContactFilesDO tbContactFiles = new TbContactFilesDO();
		tbContactFiles.setCntId(tbContactInfo.getCntId());
		tbContactFiles.setReserve2("1");
		tbContactFilesService.update(tbContactFiles);
		//删除批注附件状态为0无效
		tbContactFiles.setReserve2("0");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", "2");
		params.put("reserve2", "0");
		params.put("cntId", tbContactInfo.getCntId());

		List<TbContactFilesDO> tbContactFilesList = tbContactFilesService.list(params);
		for(TbContactFilesDO file : tbContactFilesList){
			String fileName = file.getPath()+file.getReserve1();
			if (tbContactFilesService.remove(file.getId()) > 0) {
				boolean b = FileUtil.deleteFile(fileName);
				if (!b) {
					return R.error("数据库记录删除成功，文件删除失败");
				}
			}
		}

		tbContactInfoService.commit(tbContactInfo, userDO);
		return R.ok();
	}
	/**
	 * 添加备注
	 */
	@ResponseBody
	@RequestMapping("/addComment")
	public R addComment( TbContactInfoDO tbContactInfo){
		UserDO userDO  = userService.get(getUserId());
		AddhiCommentDO addhiCommentDO = new AddhiCommentDO();
		String uuid = UUID.randomUUID().toString();
		addhiCommentDO.setId(uuid);
		addhiCommentDO.setUserId(userDO.getName());
		addhiCommentDO.setMessage(tbContactInfo.getTaskComment());
		addhiCommentDO.setTime(new Date());
		addhiCommentDO.setProcInstId(tbContactInfo.getProcInsId());
		addhiCommentService.save(addhiCommentDO);
		return R.ok();
	}
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("contact:tbContactInfo:remove")
	public R remove( String cntId){
		if(tbContactInfoService.remove(cntId)>0){
		return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("contact:tbContactInfo:batchRemove")
	public R remove(@RequestParam("ids[]") String[] cntIds){
		tbContactInfoService.batchRemove(cntIds);
		return R.ok();
	}

}

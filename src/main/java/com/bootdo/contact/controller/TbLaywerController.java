package com.bootdo.contact.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.bootdo.contact.domain.TbLaywerDO;
import com.bootdo.contact.service.TbLaywerService;
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
@RequestMapping("/contact/tbLaywer")
public class TbLaywerController extends BaseController{
	@Autowired
	TbLaywerService tbLaywerService;
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
	
	@GetMapping()
	@RequiresPermissions("contact:tbLaywer:tbLaywer")
	String TbLaywer(){
	    return "contact/tbLaywer/tbLaywer";
	}
	
	@GetMapping("/comment/{procInsId}")
	String comment(@PathVariable("procInsId") String procInsId){
    	UserDO userDO  = userService.get(getUserId());
    	
		HistoricProcessInstance ins = historyService.createHistoricProcessInstanceQuery().processInstanceId(procInsId).singleResult();
		UserDO userDO1  = userService.get(ins.getStartUserId());
		UserRoleDO uDao = userRoleMapper.getByUserID(userDO.getUserId());
		
		DeptDO deptDo = sysDeptMapper.get(userDO.getDeptId());
		//是否为发起部门负责人(返回的批注页面不同)
		if((userDO.getDeptId().longValue()==userDO1.getDeptId().longValue())&&(uDao.getRoleId()==3))
			return "contact/tbLaywer/comment1";
		//员工、总行合规风险部、分行合规风险部
		else if(uDao.getRoleId()==2||deptDo.getDeptType()==4||deptDo.getDeptType()==5){
			return "contact/tbLaywer/comment3";
		}
		else
			return "contact/tbLaywer/comment2";
	    
	}
	
	@GetMapping("/scanComment/{type}")
	String scanComment(@PathVariable("type") String type ){
    	
		if(type.equals("12"))
			return "contact/tbLaywer/comment12";
		else if(type.equals("11")){
			return "contact/tbLaywer/comment11";
		}
		else
			return "contact/tbLaywer/comment13";
	    
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contact:tbLaywer:tbLaywer")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TbLaywerDO> tbLaywerList = tbLaywerService.list(query);
		int total = tbLaywerService.count(query);
		PageUtils pageUtils = new PageUtils(tbLaywerList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{key}")
	@RequiresPermissions("contact:tbLaywer:add")
	String add(Model model, @PathVariable("key") String key){
		model.addAttribute("cntId", UUID.randomUUID().toString());
		model.addAttribute("key", key);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("cntType");
		model.addAttribute("cntTypes",dictDOS);
		
	    return "contact/tbLaywer/add";
	}
	/**
	 * 处理
	 */	
	@GetMapping("/edit/{taskId}/{processId}")
	//@RequiresPermissions("contact:tbLaywer:edit")
	String edit(@PathVariable("taskId") String taskId ,@PathVariable("processId") String processId , Model model){
		TbLaywerDO tbLaywer = tbLaywerService.get(processId);
		
		tbLaywer.setTaskId(taskId);
        
        UserDO userDO  = userService.get(getUserId());
        //获取用户角色
        List<Long> list = userRoleMapper.listRoleId(userDO.getUserId());
        
        DeptDO sysDept = sysDeptService.get(userDO.getDeptId());
        //只有合规风险部的员工可以再次设置是否会签
        if((sysDept.getDeptType()==4||sysDept.getDeptType()==5)&&list.get(0)==2){
        	model.addAttribute("countersign", "on");
        }
        //只有合规风险部的人员可以编辑批注附件
        if(sysDept.getDeptType()==4||sysDept.getDeptType()==5){
        	model.addAttribute("comment_file", "on");
        }
        
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("cntType");
		String type = tbLaywer.getCntType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("cntTypes",dictDOS);
		model.addAttribute("tbLaywer", tbLaywer);
		
	    return "contact/tbLaywer/edit";
	}
	/**
	 * 查看
	 */	
	@GetMapping("/scan/{instanceId}")
	//@RequiresPermissions("contact:tbLaywer:edit")
	String scan(@PathVariable("instanceId") String instanceId , Model model){
		TbLaywerDO tbLaywer = tbLaywerService.get(instanceId);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("cntType");
		String type = tbLaywer.getCntType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("cntTypes",dictDOS);
        model.addAttribute("tbLaywer", tbLaywer);
	    return "contact/tbLaywer/scan";
	}	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contact:tbLaywer:add")
	public R save( TbLaywerDO tbLaywer){
		UserDO userDO  = userService.get(getUserId());
//		tbLaywer.setCntAdmin(getUserId().toString());
		if(tbLaywerService.save(tbLaywer, userDO)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@GetMapping("/update/{taskId}/{processId}")
	String update(@PathVariable("taskId") String taskId ,@PathVariable("processId") String processId , Model model){
		TbLaywerDO tbLaywer = tbLaywerService.get(processId);
		
		tbLaywer.setTaskId(taskId);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("cntType");
		String type = tbLaywer.getCntType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("cntTypes",dictDOS);
        model.addAttribute("tbLaywer", tbLaywer);
	    return "contact/tbLaywer/update";
	}
	/**
	 * 提交
	 */
	@ResponseBody
	@RequestMapping("/commit")
	public R commit( TbLaywerDO tbLaywer){
		UserDO userDO  = userService.get(getUserId());
		
		//更新批注附件状态为1有效
		TbContactFilesDO tbContactFiles = new TbContactFilesDO();
		tbContactFiles.setCntId(tbLaywer.getCntId());
		tbContactFiles.setReserve2("1");
		tbContactFilesService.update(tbContactFiles);
		//删除批注附件状态为0无效
		tbContactFiles.setReserve2("0");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", "2");
		params.put("reserve2", "0");
		params.put("cntId", tbLaywer.getCntId());
		
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
		
		tbLaywerService.commit(tbLaywer, userDO);
		return R.ok();
	}	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("contact:tbLaywer:remove")
	public R remove( String cntId){
		if(tbLaywerService.remove(cntId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("contact:tbLaywer:batchRemove")
	public R remove(@RequestParam("ids[]") String[] cntIds){
		tbLaywerService.batchRemove(cntIds);
		return R.ok();
	}
	
}

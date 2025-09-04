package com.bootdo.legalAid.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.bootdo.legalAid.domain.LegalAidDO;
import com.bootdo.legalAid.service.LegalAidService;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.file.service.TbContactFilesService;

/**
 * 法律援助信息表
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-08-13 09:26:47
 */
 
@Controller
@RequestMapping("/legalAid/legalAid")
public class LegalAidController extends BaseController{
	@Autowired
	private LegalAidService legalAidService;
	@Autowired
	UserService userService;
	@Autowired
	TbContactFilesService tbContactFilesService;
	
	@GetMapping()
	@RequiresPermissions("legalAid:legalAid:legalAid")
	String LegalAid(){
	    return "legalAid/legalAid/legalAid";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("legalAid:legalAid:legalAid")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LegalAidDO> legalAidList = legalAidService.list(query);
		int total = legalAidService.count(query);
		PageUtils pageUtils = new PageUtils(legalAidList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{key}")
	String add(Model model, @PathVariable("key") String key){
		model.addAttribute("aidId", UUID.randomUUID().toString());
		model.addAttribute("key", key);
	    return "legalAid/legalAid/add";
	}

	@GetMapping("/edit/{taskId}/{processId}")
	String edit(@PathVariable("taskId") String taskId ,@PathVariable("processId") String processId , Model model){
		LegalAidDO legalAid = legalAidService.getByProc(processId);
		legalAid.setTaskId(taskId);
		model.addAttribute("legalAid", legalAid);
	    return "legalAid/legalAid/edit";
	}
	/**
	 * 查看
	 */	
	@GetMapping("/scan/{instanceId}")
	String scan(@PathVariable("instanceId") String instanceId , Model model){
		LegalAidDO legalAid = legalAidService.getByProc(instanceId);
        model.addAttribute("legalAid", legalAid);
	    return "legalAid/legalAid/scan";
	}	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	public R save( LegalAidDO legalAid){
		if(legalAidService.save(legalAid)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update( LegalAidDO legalAid){
		legalAidService.update(legalAid);
		return R.ok();
	}
	/**
	 * 提交
	 */
	@ResponseBody
	@RequestMapping("/commit")
	public R commit( LegalAidDO legalAid){
		UserDO userDO  = userService.get(getUserId());
		legalAidService.commit(legalAid, userDO);
		return R.ok();
	}	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("legalAid:legalAid:remove")
	public R remove( String aidId){
		if(legalAidService.remove(aidId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("legalAid:legalAid:batchRemove")
	public R remove(@RequestParam("ids[]") String[] aidIds){
		legalAidService.batchRemove(aidIds);
		return R.ok();
	}
	
}

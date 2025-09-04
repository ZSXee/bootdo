package com.bootdo.advice.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.bootdo.advice.domain.AdviceDO;
import com.bootdo.advice.service.AdviceService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-11-16 13:58:58
 */
 
@Controller
@RequestMapping("/advice/advice")
public class AdviceController extends BaseController{
	@Autowired
	private AdviceService adviceService;
	@Autowired
	UserService userService;
	
	@GetMapping()
	@RequiresPermissions("advice:advice:advice")
	String Advice(){
	    return "advice/advice/advice";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("advice:advice:advice")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AdviceDO> adviceList = adviceService.list(query);
		int total = adviceService.count(query);
		PageUtils pageUtils = new PageUtils(adviceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("advice:advice:add")
	String add(){
	    return "advice/advice/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("advice:advice:edit")
	String edit(@PathVariable("id") String id,Model model){
		AdviceDO advice = adviceService.get(id);
		model.addAttribute("advice", advice);
	    return "advice/advice/edit";
	}
	
	@GetMapping("/scan/{id}")
	@RequiresPermissions("advice:advice:scan")
	String scan(@PathVariable("id") String id,Model model){
		AdviceDO advice = adviceService.get(id);
		model.addAttribute("advice", advice);
	    return "advice/advice/scan";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("advice:advice:add")
	public R save( AdviceDO advice){
		UserDO userDO  = userService.get(getUserId());
		
		advice.setStarttime(new Date());
		advice.setUserid(userDO.getUsername());
		advice.setUsername(userDO.getName());
		
		if(adviceService.save(advice)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("advice:advice:edit")
	public R update( AdviceDO advice){
		UserDO userDO  = userService.get(getUserId());
		
		advice.setEndtime(new Date());
		advice.setResponder(userDO.getName());
		
		adviceService.update(advice);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("advice:advice:remove")
	public R remove( String id){
		if(adviceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("advice:advice:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		adviceService.batchRemove(ids);
		return R.ok();
	}
	
}

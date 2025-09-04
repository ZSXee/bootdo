package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.GreylistDO;
import com.bootdo.system.service.GreylistService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-05-26 14:45:29
 */
 
@Controller
@RequestMapping("/system/greylist")
public class GreylistController {
	@Autowired
	private GreylistService greylistService;
	
	@GetMapping()
	@RequiresPermissions("system:greylist:greylist")
	String Greylist(){
	    return "system/greylist/greylist";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:greylist:greylist")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GreylistDO> greylistList = greylistService.list(query);
		int total = greylistService.count(query);
		PageUtils pageUtils = new PageUtils(greylistList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:greylist:add")
	String add(){
	    return "system/greylist/add";
	}

	@GetMapping("/edit/{number}")
	@RequiresPermissions("system:greylist:edit")
	String edit(@PathVariable("number") Integer number,Model model){
		GreylistDO greylist = greylistService.get(number);
		model.addAttribute("greylist", greylist);
	    return "system/greylist/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:greylist:add")
	public R save( GreylistDO greylist){
		if(greylistService.save(greylist)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:greylist:edit")
	public R update( GreylistDO greylist){
		greylistService.update(greylist);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:greylist:remove")
	public R remove( Integer number){
		if(greylistService.remove(number)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:greylist:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] numbers){
		greylistService.batchRemove(numbers);
		return R.ok();
	}
	
}

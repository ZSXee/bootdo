package com.bootdo.activiti.controller;

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

import com.bootdo.activiti.domain.AddhiCommentDO;
import com.bootdo.activiti.service.AddhiCommentService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2023-07-12 11:40:21
 */
 
@Controller
@RequestMapping("/activiti/addhiComment")
public class AddhiCommentController {
	@Autowired
	private AddhiCommentService addhiCommentService;
	
	@GetMapping()
	@RequiresPermissions("activiti:addhiComment:addhiComment")
	String AddhiComment(){
	    return "activiti/addhiComment/addhiComment";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AddhiCommentDO> addhiCommentList = addhiCommentService.list(query);
		int total = addhiCommentService.count(query);
		PageUtils pageUtils = new PageUtils(addhiCommentList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("activiti:addhiComment:add")
	String add(){
	    return "activiti/addhiComment/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("activiti:addhiComment:edit")
	String edit(@PathVariable("id") String id,Model model){
		AddhiCommentDO addhiComment = addhiCommentService.get(id);
		model.addAttribute("addhiComment", addhiComment);
	    return "activiti/addhiComment/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("activiti:addhiComment:add")
	public R save( AddhiCommentDO addhiComment){
		if(addhiCommentService.save(addhiComment)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("activiti:addhiComment:edit")
	public R update( AddhiCommentDO addhiComment){
		addhiCommentService.update(addhiComment);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("activiti:addhiComment:remove")
	public R remove( String id){
		if(addhiCommentService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("activiti:addhiComment:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		addhiCommentService.batchRemove(ids);
		return R.ok();
	}
	
}

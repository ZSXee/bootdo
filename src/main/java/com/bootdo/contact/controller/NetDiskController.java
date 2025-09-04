package com.bootdo.contact.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.contact.domain.HiProcinstDO;
import com.bootdo.contact.service.HiProcinstService;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.excel.AjaxResult;
import com.bootdo.common.utils.excel.ExcelUtil;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-30 15:04:37
 */
 
@Controller
@RequestMapping("/contact/netDisk")
public class NetDiskController extends BaseController{
	protected static final Logger LOGGER = LoggerFactory.getLogger(ContactTemplatesController.class);

	@Autowired
	private DeptService sysDeptService;
	@Autowired
	private DeptDao sysDeptMapper;
	
	@GetMapping()
	@RequiresPermissions("contact:netDisk:netDisk")
	String HiProcinst(Model model){
		UserDO userDO  = getUser();
		DeptDO sysDept = sysDeptService.get(userDO.getDeptId());
		
	    return "contact/netDisk/login";
	}
}

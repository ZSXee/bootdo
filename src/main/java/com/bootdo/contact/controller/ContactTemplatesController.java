package com.bootdo.contact.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.contact.domain.ContactTemplatesDO;
import com.bootdo.contact.service.ContactTemplatesService;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 合同模板
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-30 09:38:20
 */
 
@Controller
@RequestMapping("/contact/contactTemplates")
public class ContactTemplatesController extends BaseController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ContactTemplatesController.class);
	@Autowired
	private ContactTemplatesService contactTemplatesService;
	
	@GetMapping()
	@RequiresPermissions("contact:contactTemplates:contactTemplates")
	String ContactTemplates(){
	    return "contact/contactTemplates/contactTemplates";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("contact:contactTemplates:contactTemplates")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ContactTemplatesDO> contactTemplatesList = contactTemplatesService.list(query);
		int total = contactTemplatesService.count(query);
		PageUtils pageUtils = new PageUtils(contactTemplatesList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("contact:contactTemplates:add")
	String add(){
	    return "contact/contactTemplates/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("contact:contactTemplates:edit")
	String edit(@PathVariable("id") String id,Model model){
		ContactTemplatesDO contactTemplates = contactTemplatesService.get(id);
		model.addAttribute("contactTemplates", contactTemplates);
	    return "contact/contactTemplates/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("contact:contactTemplates:add")
	public R save( ContactTemplatesDO contactTemplates){
		if(contactTemplatesService.save(contactTemplates)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("contact:contactTemplates:edit")
	public R update( ContactTemplatesDO contactTemplates){
		contactTemplatesService.update(contactTemplates);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("contact:contactTemplates:remove")
	public R remove(String id) {
		ContactTemplatesDO tbContactFiles = contactTemplatesService.get(id);
		String fileName = tbContactFiles.getPath()+tbContactFiles.getReserve1();
		if (contactTemplatesService.remove(id)>0) {
			boolean b = FileUtil.deleteFile(fileName);
			if (!b) {
				return R.error("数据库记录删除成功，文件删除失败");
			}
			return R.ok();
		} else {
			return R.error();
		}
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("contact:contactTemplates:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		contactTemplatesService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 上传
	 */
	@ResponseBody
	@PostMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,  @RequestParam String params) {
		String fileName = file.getOriginalFilename();
		
		JSONObject json = JSONObject.parseObject(params);
		ContactTemplatesDO sysFile = new ContactTemplatesDO();
		sysFile.setName(fileName);
		fileName = FileUtil.renameToUUID(fileName);
        String os = System.getProperty("os.name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String ctxPath = null;
        if (os != null && os.startsWith("Windows")){
    		ctxPath = "ContactTemplates\\";
    		ctxPath += ymd + "\\";
        }else{
    		ctxPath = "ContactTemplates//";
    		ctxPath += ymd + "//";
        }
		sysFile.setReserve1(fileName);
		sysFile.setType(json.get("type").toString());
		sysFile.setId(UUID.randomUUID().toString());
		sysFile.setPath(ctxPath);
		sysFile.setUploader(getUser().getName());
		
		try {
			FileUtil.uploadFile(file.getBytes(), ctxPath, fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (contactTemplatesService.save(sysFile) > 0) {
			return R.ok().put("fileName","");
		}
		return R.error();
	}
	/**
	 * 下载
	 */
	@ResponseBody
	@RequestMapping("/download")
	public void download(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request)
    {
		String id = params.get("id").toString();
		ContactTemplatesDO contactTemplates = contactTemplatesService.get(id);
		
        String name = contactTemplates.getReserve1();
        String path = contactTemplates.getPath();
        String realName = contactTemplates.getName();
        InputStream inputStream = null;
        OutputStream os = null;
        try
        {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + FileUtil.setFileDownloadHeader(request, realName));
            File file = new File(path+name);
            inputStream = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = inputStream.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
        }
        catch (Exception e)
        {
        	LOGGER.error("下载文件失败", e);
        }
        finally
        {
            try
            {
                os.close();
                inputStream.close();
            }
            catch (IOException e)
            {
            	LOGGER.error("close close fail ", e);
            }
        }
    }

}

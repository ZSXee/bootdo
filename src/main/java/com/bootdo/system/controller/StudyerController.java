package com.bootdo.system.controller;

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

import com.bootdo.system.domain.StudyerDO;
import com.bootdo.system.service.StudyerService;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 学习培训
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2020-05-20 09:38:20
 */
 
@Controller
@RequestMapping("/system/studyer")
public class StudyerController extends BaseController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(StudyerController.class);
	@Autowired
	private StudyerService StudyerService;
	
	@GetMapping()
	@RequiresPermissions("system:studyer:studyer")
	String Studyer(){
	    return "system/studyer/studyer";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:studyer:studyer")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudyerDO> studyerList = StudyerService.list(query);
		int total = StudyerService.count(query);
		PageUtils pageUtils = new PageUtils(studyerList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:studyer:add")
	String add(){
	    return "system/studyer/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:studyer:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudyerDO studyer = StudyerService.get(id);
		model.addAttribute("studyer", studyer);
	    return "system/studyer/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:studyer:add")
	public R save( StudyerDO studyer){
		if(StudyerService.save(studyer)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:studyer:edit")
	public R update( StudyerDO studyer){
		StudyerService.update(studyer);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:studyer:remove")
	public R remove(String id) {
		StudyerDO tbContactFiles = StudyerService.get(id);
		String fileName = tbContactFiles.getPath()+tbContactFiles.getReserve1();
		if (StudyerService.remove(id)>0) {
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
	@RequiresPermissions("system:studyer:batchRemove")
	public R batchRemove(@RequestParam("ids[]") String[] ids){
		StudyerService.batchRemove(ids);
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
		StudyerDO sysFile = new StudyerDO();
		sysFile.setName(fileName);
		fileName = FileUtil.renameToUUID(fileName);
        String os = System.getProperty("os.name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String ctxPath = null;
        if (os != null && os.startsWith("Windows")){
    		ctxPath = "Studyer\\";
    		ctxPath += ymd + "\\";
        }else{
    		ctxPath = "Studyer//";
    		ctxPath += ymd + "//";
        }
		sysFile.setReserve1(fileName);
		sysFile.setType(json.get("type").toString());
		sysFile.setId(UUID.randomUUID().toString());
		sysFile.setPath(ctxPath);
		sysFile.setUploader(getUser().getName());
		sysFile.setReserve2(ymd);
		
		try {
			FileUtil.uploadFile(file.getBytes(), ctxPath, fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (StudyerService.save(sysFile) > 0) {
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
		StudyerDO studyer = StudyerService.get(id);
		
        String name = studyer.getReserve1();
        String path = studyer.getPath();
        String realName = studyer.getName();
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

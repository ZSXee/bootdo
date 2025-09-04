package com.bootdo.htdzk.controller;

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

import com.bootdo.htdzk.domain.GshtdzkDO;
import com.bootdo.htdzk.service.GshtdzkService;
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
@RequestMapping("/htdzk/gshtdzk")
public class GshtdzkController extends BaseController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(GshtdzkController.class);
	@Autowired
	private GshtdzkService gshtdzkService;
	
	@GetMapping()
	@RequiresPermissions("htdzk:gshtdzk:gshtdzk")
	String Gshtdzk(){
	    return "htdzk/gshtdzk/gshtdzk";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("htdzk:gshtdzk:gshtdzk")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GshtdzkDO> gshtdzkList = gshtdzkService.list(query);
		int total = gshtdzkService.count(query);
		PageUtils pageUtils = new PageUtils(gshtdzkList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("htdzk:gshtdzk:add")
	String add(){
	    return "htdzk/gshtdzk/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("htdzk:gshtdzk:edit")
	String edit(@PathVariable("id") String id,Model model){
		GshtdzkDO gshtdzk = gshtdzkService.get(id);
		model.addAttribute("gshtdzk", gshtdzk);
	    return "htdzk/gshtdzk/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("htdzk:gshtdzk:add")
	public R save( GshtdzkDO gshtdzk){
		if(gshtdzkService.save(gshtdzk)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("htdzk:gshtdzk:edit")
	public R update( GshtdzkDO gshtdzk){
		gshtdzkService.update(gshtdzk);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("htdzk:gshtdzk:remove")
	public R remove(String id) {
		GshtdzkDO tbContactFiles = gshtdzkService.get(id);
		String fileName = tbContactFiles.getPath()+tbContactFiles.getReserve1();
		if (gshtdzkService.remove(id)>0) {
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
	@RequiresPermissions("htdzk:gshtdzk:batchRemove")
	public R batchRemove(@RequestParam("ids[]") String[] ids){
		gshtdzkService.batchRemove(ids);
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
		GshtdzkDO sysFile = new GshtdzkDO();
		sysFile.setName(fileName);
		fileName = FileUtil.renameToUUID(fileName);
        String os = System.getProperty("os.name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String ctxPath = null;
        if (os != null && os.startsWith("Windows")){
    		ctxPath = "Gshtdzk\\";
    		ctxPath += ymd + "\\";
        }else{
    		ctxPath = "Gshtdzk//";
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

		if (gshtdzkService.save(sysFile) > 0) {
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
		GshtdzkDO gshtdzk = gshtdzkService.get(id);
		
        String name = gshtdzk.getReserve1();
        String path = gshtdzk.getPath();
        String realName = gshtdzk.getName();
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

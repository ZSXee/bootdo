package com.bootdo.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.file.domain.TbOfficeInfoFilesDO;
import com.bootdo.file.service.TbOfficeInfoFilesService;
import com.bootdo.system.domain.StudyerDO;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 合同附件
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-27 08:55:20
 */
 
@Controller
@RequestMapping("/file/tbOfficeInfoFiles")
public class TbOfficeInfoFilesController extends BaseController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(TbOfficeInfoFilesController.class);
	@Autowired
	private TbOfficeInfoFilesService TbOfficeInfoFilesService;
	
	@GetMapping()
	@RequiresPermissions("file:tbOfficeInfoFiles:tbOfficeInfoFiles")
	String tbOfficeInfoFiles(){
	    return "file/tbOfficeInfoFiles/tbOfficeInfoFiles";
	}
	
	@ResponseBody
	@GetMapping("/listHT")
	public PageUtils listHT(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("type", "1");
        Query query = new Query(params);
		List<TbOfficeInfoFilesDO> tbOfficeInfoFilesList = TbOfficeInfoFilesService.list(query);
		int total = TbOfficeInfoFilesService.count(query);
		PageUtils pageUtils = new PageUtils(tbOfficeInfoFilesList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/listPZ")
	public PageUtils listPZ(@RequestParam Map<String, Object> params){
		//查询列表数据
		params.put("type", "2");
        Query query = new Query(params);
		List<TbOfficeInfoFilesDO> tbOfficeInfoFilesList = TbOfficeInfoFilesService.list(query);
		int total = TbOfficeInfoFilesService.count(query);
		PageUtils pageUtils = new PageUtils(tbOfficeInfoFilesList, total);
		return pageUtils;
	}
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	public R remove(String id) {
		TbOfficeInfoFilesDO tbOfficeInfoFiles = TbOfficeInfoFilesService.get(id);
		String fileName = tbOfficeInfoFiles.getPath()+tbOfficeInfoFiles.getReserve1();
		if (TbOfficeInfoFilesService.remove(id) > 0) {
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
	@RequiresPermissions("file:tbOfficeInfoFiles:batchRemove")
	public R remove(@RequestParam("ids[]") String[] ids){
		TbOfficeInfoFilesService.batchRemove(ids);
		return R.ok();
	}
	/**
	 * 上传
	 */
	@ResponseBody
	@PostMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,  @RequestParam String params) {
		String fileName = file.getOriginalFilename();
		// 将非数字、英文字母、汉字的部分替换为空后，就剩数字、英文字母、汉字了
		fileName = fileName.replaceAll("[^0-9a-zA-Z\\u4e00-\\u9fa5$.]", "");
		System.out.println("****"+fileName);
		JSONObject json = JSONObject.parseObject(params);
		TbOfficeInfoFilesDO sysFile = new TbOfficeInfoFilesDO();
		sysFile.setName(fileName);
		fileName = FileUtil.renameToUUID(fileName);
//		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "uploads\\";
		
        String os = System.getProperty("os.name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String ctxPath = null;
        if (os != null && os.startsWith("Windows")){
    		ctxPath = "uploads\\";
    		ctxPath += ymd + "\\";
        }else{
    		ctxPath = "uploads//";
    		ctxPath += ymd + "//";
        }
		
		sysFile.setReserve1(fileName);
		sysFile.setType(json.get("type").toString());
		sysFile.setCntId(json.get("cntId").toString());
		sysFile.setId(UUID.randomUUID().toString());
		sysFile.setPath(ctxPath);
		sysFile.setReserve2(getUser().getName());
		
		try {
			FileUtil.uploadFile(file.getBytes(), ctxPath, fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (TbOfficeInfoFilesService.save(sysFile) > 0) {
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
		String cntId = params.get("cntId").toString();
		TbOfficeInfoFilesDO studyerr = TbOfficeInfoFilesService.get(cntId);
        String name = studyerr.getReserve1();
        String path = studyerr.getPath();
        String realName = studyerr.getName();
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
	/**
	 * 下载ChromeFrame.zip
	 * @throws FileNotFoundException 
	 */
	@ResponseBody
	@RequestMapping("/download1")
	public void download1(@RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) throws FileNotFoundException
    {
        String name = params.get("name").toString();
        String path = getJarRootPath()+"/browser/";
        InputStream inputStream = null;
        OutputStream os = null;
        LOGGER.info(path);
        try
        {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + FileUtil.setFileDownloadHeader(request, name));
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
    private String getJarRootPath() throws FileNotFoundException {
        String path = ResourceUtils.getURL("classpath:").getPath();
        //创建File时会自动处理前缀和jar包路径问题  => /root/tmp
        File rootFile = new File(path);
        if(!rootFile.exists()) {
            rootFile = new File("");
        }
        return rootFile.getAbsolutePath();
    }


}

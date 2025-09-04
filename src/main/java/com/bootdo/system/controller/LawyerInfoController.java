package com.bootdo.system.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.LawyerInfoDO;
import com.bootdo.system.domain.OfficeInfoDO;
import com.bootdo.system.service.LawyerInfoService;
import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.excel.AjaxResult;
import com.bootdo.common.utils.excel.ExcelUtil;
import com.bootdo.contact.controller.ContactTemplatesController;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-05-26 14:45:29
 */
 
@Controller
@RequestMapping("/system/lawyerInfo")
public class LawyerInfoController extends BaseController{
	protected static final Logger LOGGER = LoggerFactory.getLogger(ContactTemplatesController.class);
	@Autowired
	private LawyerInfoService lawyerInfoService;
	
	@GetMapping()
	@RequiresPermissions("system:lawyerInfo:lawyerInfo")
	String LawyerInfo(){
	    return "system/lawyerInfo/lawyerInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:lawyerInfo:lawyerInfo")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LawyerInfoDO> lawyerInfoList = lawyerInfoService.list(query);
		int total = lawyerInfoService.count(query);
		PageUtils pageUtils = new PageUtils(lawyerInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:lawyerInfo:add")
	String add(){
	    return "system/lawyerInfo/add";
	}

	@GetMapping("/edit/{number}")
	@RequiresPermissions("system:lawyerInfo:edit")
	String edit(@PathVariable("number") Integer number,Model model){
		LawyerInfoDO lawyerInfo = lawyerInfoService.get(number);
		model.addAttribute("lawyerInfo", lawyerInfo);
	    return "system/lawyerInfo/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:lawyerInfo:add")
	public R save( LawyerInfoDO lawyerInfo){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		lawyerInfo.setDate(date);
		if(lawyerInfoService.save(lawyerInfo)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:lawyerInfo:edit")
	public R update( LawyerInfoDO lawyerInfo){
		lawyerInfoService.update(lawyerInfo);
		return R.ok();
	}
	@ResponseBody
	@PostMapping("/export")
	@RequiresPermissions("system:lawyerInfo:export")
	@Log("导出")
	public AjaxResult export(@RequestParam Map<String, Object> params){
		// 查询列表数据
		Query query = new Query(params);
		List<LawyerInfoDO> lawyerInfoList = lawyerInfoService.list(query);

		//机构类型0：分行 1：总行部室2：分行部室3：支行4：总行合规风险部5：分行合规风险部6：全行
		
		
		ExcelUtil<LawyerInfoDO> util = new ExcelUtil<LawyerInfoDO>(LawyerInfoDO.class);
		return util.exportExcel(lawyerInfoList, "导出");
	}
	@RequestMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
        InputStream inputStream = null;
        OutputStream os = null;
        try
        {
        	String filePath = null;
        	String osys = System.getProperty("os.name");
            if (osys != null && osys.startsWith("Windows")){
            	filePath = "file\\"+ fileName;
            }else{
            	filePath = "file//"+ fileName;
            }

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + setFileDownloadHeader(request, realFileName));
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = inputStream.read(b)) > 0)
            {
                os.write(b, 0, length);
            }
            if (delete && file.exists())
            {
                file.delete();
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
	
	public String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        else if (agent.contains("Firefox"))
        {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;

    }

	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:lawyerInfo:remove")
	public R remove( Integer number){
		if(lawyerInfoService.remove(number)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:lawyerInfo:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] numbers){
		lawyerInfoService.batchRemove(numbers);
		return R.ok();
	}
	
}

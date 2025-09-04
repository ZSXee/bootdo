package com.bootdo.system.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

import com.bootdo.system.domain.DailyDO;
import com.bootdo.system.service.DailyService;
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
 * @date 2020-06-12 08:54:14
 */
 
@Controller
@RequestMapping("/system/daily")
public class DailyController extends BaseController{
	protected static final Logger LOGGER = LoggerFactory.getLogger(ContactTemplatesController.class);

	@Autowired
	private DailyService dailyService;
	
	@GetMapping()
	@RequiresPermissions("system:daily:daily")
	String Daily(){
	    return "system/daily/daily";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:daily:daily")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DailyDO> dailyList = dailyService.list(query);
		int total = dailyService.count(query);
		PageUtils pageUtils = new PageUtils(dailyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:daily:add")
	String add(Model model){
		model.addAttribute("cntId", UUID.randomUUID().toString());
	    return "system/daily/add";
	}

	@GetMapping("/edit/{cntId}")
	@RequiresPermissions("system:daily:edit")
	String edit(@PathVariable("cntId") String cntId,Model model){
		DailyDO daily = dailyService.get(cntId);
		model.addAttribute("daily", daily);
		model.addAttribute("cntId", cntId);
	    return "system/daily/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:daily:add")
	public R save( DailyDO daily){
		if(dailyService.save(daily)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:daily:edit")
	public R update( DailyDO daily){
		dailyService.update(daily);
		return R.ok();
	}
	@ResponseBody
	@PostMapping("/export")
	@RequiresPermissions("system:daily:export")
	@Log("导出")
	public AjaxResult export(@RequestParam Map<String, Object> params){
		// 查询列表数据
		Query query = new Query(params);
		List<DailyDO> dailyList = dailyService.list(query);

		//机构类型0：分行 1：总行部室2：分行部室3：支行4：总行合规风险部5：分行合规风险部6：全行
		
		
		ExcelUtil<DailyDO> util = new ExcelUtil<DailyDO>(DailyDO.class);
		return util.exportExcel(dailyList, "导出");
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
	@RequiresPermissions("system:daily:remove")
	public R remove( String cntId){
		if(dailyService.remove(cntId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:daily:batchRemove")
	public R remove(@RequestParam("cntIds[]") String[] cntIds){
		dailyService.batchRemove(cntIds);
		return R.ok();
	}
	
}

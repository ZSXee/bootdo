package com.bootdo.jobguide.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.bootdo.jobguide.domain.BusiGuideDO;
import com.bootdo.jobguide.service.BusiGuideService;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
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
@RequestMapping("/jobguide/busiguide")
public class BusiGuideController extends BaseController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(BusiGuideController.class);
	@Autowired
	private BusiGuideService busiguideService;
	@Autowired
	DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("jobguide:busiguide:busiguide")
	String BusiGuide(Model model){
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("fileType");
		model.addAttribute("fileTypes",dictDOS);
		
	    return "jobguide/busiguide/busiguide";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jobguide:busiguide:busiguide")
	public PageUtils list(@RequestParam Map<String, Object> params){

		//查询列表数据
        Query query = new Query(params);
		List<BusiGuideDO> busiguideList = busiguideService.list(query);
		
		//翻译文件类型
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "fileType");
		List<DictDO> dictList = dictService.list(map);
		Map<String, Object> map_tmp = new HashMap<>(16);
		for(DictDO dictDO : dictList){
			map_tmp.put(dictDO.getValue(), dictDO.getName());
		}
		for(BusiGuideDO busiGuideDO : busiguideList){
			busiGuideDO.setType((String) map_tmp.get(busiGuideDO.getType()));
		}
		
		int total = busiguideService.count(query);
		PageUtils pageUtils = new PageUtils(busiguideList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jobguide:busiguide:add")
	String add(){
	    return "jobguide/busiguide/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("jobguide:busiguide:edit")
	String edit(@PathVariable("id") String id,Model model){
		BusiGuideDO busiguide = busiguideService.get(id);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("fileType");
		String type = busiguide.getType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("fileTypes",dictDOS);
		model.addAttribute("busiguide", busiguide);
	    return "jobguide/busiguide/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jobguide:busiguide:add")
	public R save( BusiGuideDO busiguide){
		if(busiguideService.save(busiguide)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jobguide:busiguide:edit")
	public R update( BusiGuideDO busiguide){
		busiguideService.update(busiguide);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("jobguide:busiguide:remove")
	public R remove(String id) {
		BusiGuideDO tbContactFiles = busiguideService.get(id);
		String fileName = tbContactFiles.getPath()+tbContactFiles.getReserve1();
		if (busiguideService.remove(id)>0) {
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
	@RequiresPermissions("jobguide:busiguide:batchRemove")
	public R batchRemove(@RequestParam("ids[]") String[] ids){
		busiguideService.batchRemove(ids);
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
		BusiGuideDO sysFile = new BusiGuideDO();
		sysFile.setName(fileName);
		fileName = FileUtil.renameToUUID(fileName);
        String os = System.getProperty("os.name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String ctxPath = null;
        if (os != null && os.startsWith("Windows")){
    		ctxPath = "BusiGuide\\";
    		ctxPath += ymd + "\\";
        }else{
    		ctxPath = "BusiGuide//";
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

		if (busiguideService.save(sysFile) > 0) {
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
		BusiGuideDO busiguide = busiguideService.get(id);
		
        String name = busiguide.getReserve1();
        String path = busiguide.getPath();
        String realName = busiguide.getName();
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

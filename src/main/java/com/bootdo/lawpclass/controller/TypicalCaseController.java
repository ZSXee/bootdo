package com.bootdo.lawpclass.controller;

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

import com.bootdo.lawpclass.domain.TypicalCaseDO;
import com.bootdo.lawpclass.service.TypicalCaseService;
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
@RequestMapping("/lawpclass/typicalcase")
public class TypicalCaseController extends BaseController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(TypicalCaseController.class);
	@Autowired
	private TypicalCaseService typicalcaseService;
	@Autowired
	DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("lawpclass:typicalcase:typicalcase")
	String TypicalCase(Model model){
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("typicalType");
		model.addAttribute("typicalTypes",dictDOS);
	    return "lawpclass/typicalcase/typicalcase";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("lawpclass:typicalcase:typicalcase")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TypicalCaseDO> typicalcaseList = typicalcaseService.list(query);
		
		//翻译典型类型
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "typicalType");
		List<DictDO> dictList = dictService.list(map);
		Map<String, Object> map_tmp = new HashMap<>(16);
		for(DictDO dictDO : dictList){
			map_tmp.put(dictDO.getValue(), dictDO.getName());
		}
		for(TypicalCaseDO typicalCaseDO : typicalcaseList){
			typicalCaseDO.setType((String) map_tmp.get(typicalCaseDO.getType()));
		}
		
		int total = typicalcaseService.count(query);
		PageUtils pageUtils = new PageUtils(typicalcaseList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("lawpclass:typicalcase:add")
	String add(){
	    return "lawpclass/typicalcase/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("lawpclass:typicalcase:edit")
	String edit(@PathVariable("id") String id,Model model){
		TypicalCaseDO typicalcase = typicalcaseService.get(id);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("typicalType");
		String type = typicalcase.getType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("typicalTypes",dictDOS);
		model.addAttribute("typicalcase", typicalcase);
	    return "lawpclass/typicalcase/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("lawpclass:typicalcase:add")
	public R save( TypicalCaseDO typicalcase){
		if(typicalcaseService.save(typicalcase)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("lawpclass:typicalcase:edit")
	public R update( TypicalCaseDO typicalcase){
		typicalcaseService.update(typicalcase);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("lawpclass:typicalcase:remove")
	public R remove(String id) {
		TypicalCaseDO tbContactFiles = typicalcaseService.get(id);
		String fileName = tbContactFiles.getPath()+tbContactFiles.getReserve1();
		if (typicalcaseService.remove(id)>0) {
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
	@RequiresPermissions("lawpclass:typicalcase:batchRemove")
	public R batchRemove(@RequestParam("ids[]") String[] ids){
		typicalcaseService.batchRemove(ids);
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
		TypicalCaseDO sysFile = new TypicalCaseDO();
		sysFile.setName(fileName);
		fileName = FileUtil.renameToUUID(fileName);
        String os = System.getProperty("os.name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String ctxPath = null;
        if (os != null && os.startsWith("Windows")){
    		ctxPath = "TypicalCase\\";
    		ctxPath += ymd + "\\";
        }else{
    		ctxPath = "TypicalCase//";
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

		if (typicalcaseService.save(sysFile) > 0) {
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
		TypicalCaseDO typicalcase = typicalcaseService.get(id);
		
        String name = typicalcase.getReserve1();
        String path = typicalcase.getPath();
        String realName = typicalcase.getName();
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

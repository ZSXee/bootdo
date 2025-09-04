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

import com.bootdo.lawpclass.domain.StudyDO;
import com.bootdo.lawpclass.service.StudyService;
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
@RequestMapping("/lawpclass/study")
public class StudyController extends BaseController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(StudyController.class);
	@Autowired
	private StudyService studyService;
	@Autowired
	DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("lawpclass:study:study")
	String Study(Model model){
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("studyType");
		model.addAttribute("studyTypes",dictDOS);
	    return "lawpclass/study/study";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("lawpclass:study:study")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<StudyDO> studyList = studyService.list(query);
		
		//翻译培训类型
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "studyType");
		List<DictDO> dictList = dictService.list(map);
		Map<String, Object> map_tmp = new HashMap<>(16);
		for(DictDO dictDO : dictList){
			map_tmp.put(dictDO.getValue(), dictDO.getName());
		}
		for(StudyDO studyDO : studyList){
			studyDO.setType((String) map_tmp.get(studyDO.getType()));
		}
		
		int total = studyService.count(query);
		PageUtils pageUtils = new PageUtils(studyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("lawpclass:study:add")
	String add(){
	    return "lawpclass/study/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("lawpclass:study:edit")
	String edit(@PathVariable("id") String id,Model model){
		StudyDO study = studyService.get(id);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("studyType");
		String type = study.getType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("studyTypes",dictDOS);
		model.addAttribute("study", study);
	    return "lawpclass/study/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("lawpclass:study:add")
	public R save( StudyDO study){
		if(studyService.save(study)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("lawpclass:study:edit")
	public R update( StudyDO study){
		studyService.update(study);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("lawpclass:study:remove")
	public R remove(String id) {
		StudyDO tbContactFiles = studyService.get(id);
		String fileName = tbContactFiles.getPath()+tbContactFiles.getReserve1();
		if (studyService.remove(id)>0) {
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
	@RequiresPermissions("lawpclass:study:batchRemove")
	public R batchRemove(@RequestParam("ids[]") String[] ids){
		studyService.batchRemove(ids);
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
		StudyDO sysFile = new StudyDO();
		sysFile.setName(fileName);
		fileName = FileUtil.renameToUUID(fileName);
        String os = System.getProperty("os.name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String ctxPath = null;
        if (os != null && os.startsWith("Windows")){
    		ctxPath = "Study\\";
    		ctxPath += ymd + "\\";
        }else{
    		ctxPath = "Study//";
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

		if (studyService.save(sysFile) > 0) {
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
		StudyDO study = studyService.get(id);
		
        String name = study.getReserve1();
        String path = study.getPath();
        String realName = study.getName();
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

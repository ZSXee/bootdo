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

import com.bootdo.lawpclass.domain.DailyStudyDO;
import com.bootdo.lawpclass.service.DailyStudyService;
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
@RequestMapping("/lawpclass/dailystudy")
public class DailyStudyController extends BaseController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(DailyStudyController.class);
	@Autowired
	private DailyStudyService dailystudyService;
	@Autowired
	DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("lawpclass:dailystudy:dailystudy")
	String DailyStudy(Model model){
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("dailyType");
		model.addAttribute("dailyTypes",dictDOS);
	    return "lawpclass/dailystudy/dailystudy";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("lawpclass:dailystudy:dailystudy")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<DailyStudyDO> dailystudyList = dailystudyService.list(query);
		
		//翻译每日学习类型
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "dailyType");
		List<DictDO> dictList = dictService.list(map);
		Map<String, Object> map_tmp = new HashMap<>(16);
		for(DictDO dictDO : dictList){
			map_tmp.put(dictDO.getValue(), dictDO.getName());
		}
		for(DailyStudyDO dailyStudyDO : dailystudyList){
			dailyStudyDO.setType((String) map_tmp.get(dailyStudyDO.getType()));
		}
		
		int total = dailystudyService.count(query);
		PageUtils pageUtils = new PageUtils(dailystudyList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("lawpclass:dailystudy:add")
	String add(){
	    return "lawpclass/dailystudy/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("lawpclass:dailystudy:edit")
	String edit(@PathVariable("id") String id,Model model){
		DailyStudyDO dailystudy = dailystudyService.get(id);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("dailyType");
		String type = dailystudy.getType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("dailyTypes",dictDOS);
		model.addAttribute("dailystudy", dailystudy);
	    return "lawpclass/dailystudy/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("lawpclass:dailystudy:add")
	public R save( DailyStudyDO dailystudy){
		if(dailystudyService.save(dailystudy)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("lawpclass:dailystudy:edit")
	public R update( DailyStudyDO dailystudy){
		dailystudyService.update(dailystudy);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("lawpclass:dailystudy:remove")
	public R remove(String id) {
		DailyStudyDO tbContactFiles = dailystudyService.get(id);
		String fileName = tbContactFiles.getPath()+tbContactFiles.getReserve1();
		if (dailystudyService.remove(id)>0) {
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
	@RequiresPermissions("lawpclass:dailystudy:batchRemove")
	public R batchRemove(@RequestParam("ids[]") String[] ids){
		dailystudyService.batchRemove(ids);
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
		DailyStudyDO sysFile = new DailyStudyDO();
		sysFile.setName(fileName);
		fileName = FileUtil.renameToUUID(fileName);
        String os = System.getProperty("os.name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String ctxPath = null;
        if (os != null && os.startsWith("Windows")){
    		ctxPath = "DailyStudy\\";
    		ctxPath += ymd + "\\";
        }else{
    		ctxPath = "DailyStudy//";
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

		if (dailystudyService.save(sysFile) > 0) {
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
		DailyStudyDO dailystudy = dailystudyService.get(id);
		
        String name = dailystudy.getReserve1();
        String path = dailystudy.getPath();
        String realName = dailystudy.getName();
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

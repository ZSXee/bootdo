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

import com.bootdo.jobguide.domain.RiskTipDO;
import com.bootdo.jobguide.service.RiskTipService;
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
@RequestMapping("/jobguide/risktip")
public class RiskTipController extends BaseController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(RiskTipController.class);
	@Autowired
	private RiskTipService risktipService;
	@Autowired
	DictService dictService;
	
	@GetMapping()
	@RequiresPermissions("jobguide:risktip:risktip")
	String RiskTip(Model model){
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("riskType");
		model.addAttribute("riskTypes",dictDOS);
	    return "jobguide/risktip/risktip";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("jobguide:risktip:risktip")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RiskTipDO> risktipList = risktipService.list(query);
		
		
		//翻译文件类型
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", "riskType");
		List<DictDO> dictList = dictService.list(map);
		Map<String, Object> map_tmp = new HashMap<>(16);
		for(DictDO dictDO : dictList){
			map_tmp.put(dictDO.getValue(), dictDO.getName());
		}
		for(RiskTipDO riskTipDO : risktipList){
			riskTipDO.setType((String) map_tmp.get(riskTipDO.getType()));
		}
		
		int total = risktipService.count(query);
		PageUtils pageUtils = new PageUtils(risktipList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("jobguide:risktip:add")
	String add(){
	    return "jobguide/risktip/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("jobguide:risktip:edit")
	String edit(@PathVariable("id") String id,Model model){
		RiskTipDO risktip = risktipService.get(id);
		//添加字典
		List<DictDO> dictDOS = dictService.listByType("riskType");
		String type = risktip.getType();
		for (DictDO dictDO:dictDOS){
			if(type.equals(dictDO.getValue())){
				dictDO.setRemarks("checked");
			}
		}
		model.addAttribute("riskTypes",dictDOS);
		model.addAttribute("risktip", risktip);
	    return "jobguide/risktip/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("jobguide:risktip:add")
	public R save( RiskTipDO risktip){
		if(risktipService.save(risktip)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("jobguide:risktip:edit")
	public R update( RiskTipDO risktip){
		risktipService.update(risktip);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("jobguide:risktip:remove")
	public R remove(String id) {
		RiskTipDO tbContactFiles = risktipService.get(id);
		String fileName = tbContactFiles.getPath()+tbContactFiles.getReserve1();
		if (risktipService.remove(id)>0) {
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
	@RequiresPermissions("jobguide:risktip:batchRemove")
	public R batchRemove(@RequestParam("ids[]") String[] ids){
		risktipService.batchRemove(ids);
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
		RiskTipDO sysFile = new RiskTipDO();
		sysFile.setName(fileName);
		fileName = FileUtil.renameToUUID(fileName);
        String os = System.getProperty("os.name");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String ctxPath = null;
        if (os != null && os.startsWith("Windows")){
    		ctxPath = "RiskTip\\";
    		ctxPath += ymd + "\\";
        }else{
    		ctxPath = "RiskTip//";
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

		if (risktipService.save(sysFile) > 0) {
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
		RiskTipDO risktip = risktipService.get(id);
		
        String name = risktip.getReserve1();
        String path = risktip.getPath();
        String realName = risktip.getName();
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

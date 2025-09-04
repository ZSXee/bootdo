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

import com.bootdo.htdzk.domain.FgshtdzkDO;
import com.bootdo.htdzk.service.FgshtdzkService;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.service.DeptService;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.controller.BaseController;
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
@RequestMapping("/htdzk/fgshtdzk")
public class FgshtdzkController extends BaseController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(FgshtdzkController.class);
	@Autowired
	private FgshtdzkService fgshtdzkService;
	@Autowired
	private DeptService sysDeptService;
	@Autowired
	DictService dictService;

	private String deptIds[];

	@GetMapping()
	@RequiresPermissions("htdzk:fgshtdzk:fgshtdzk")
	String Fgshtdzk() {
		return "htdzk/fgshtdzk/fgshtdzk";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("htdzk:fgshtdzk:fgshtdzk")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = null;
		List<DeptDO> sysDeptList = null;
		int total = 0;
		DeptDO dept = sysDeptService.get(this.getUser().getDeptId());
		Long parentId = dept.getParentId();
		List<FgshtdzkDO> fgshtdzkList = null;
		// 机构类型0：分行 1：总行部室2：分行部室3：支行4：总行合规风险部5：分行合规风险部
		if (dept.getDeptType() == 4) {
			// 获取选择机构id
			deptIds = null;
			params.put("limit", "999999");
			query = new Query(params);
			if(params.get("reserve4").equals("")) {
				query = new Query(params);
				params.put("limit", "999999");
				fgshtdzkList = fgshtdzkService.list(query);
				total = fgshtdzkService.count(query);
			} else {
				DeptDO dept_tmp = sysDeptService.get(Long.parseLong(params.get("reserve4").toString()));
				if (dept_tmp.getDeptType() == 0) {
					params.put("parentId", dept_tmp.getDeptId());
					params.put("limit", "999999");
					query = new Query(params);
					sysDeptList = sysDeptService.list(query);
					deptIds = new String[sysDeptList.size()];
					for (int i = 0; i < sysDeptList.size(); i++) {
						// 将当前遍历机构的deptId存储至deptIds[i]中
						deptIds[i] = sysDeptList.get(i).getDeptId().toString();
					}
					params.clear();
					params.put("deptIds", deptIds);
					params.put("limit", "999999");
					params.put("offset", "0");
					query = new Query(params);
					fgshtdzkList = fgshtdzkService.list(query);
					total = fgshtdzkService.count(query);
				}else{
					params.put("uploader", this.getUser().getName());
					query = new Query(params);
					fgshtdzkList = fgshtdzkService.list(query);
					total = fgshtdzkService.count(query);
				}
			}
		} else if (dept.getDeptType() == 5) {
			deptIds = null;
			params.put("parentId", parentId);
			params.put("limit", "999999");
			query = new Query(params);
			sysDeptList = sysDeptService.list(query);
			deptIds = new String[sysDeptList.size()];

			for (int i = 0; i < sysDeptList.size(); i++) {
				deptIds[i] = sysDeptList.get(i).getDeptId().toString();
			}
			params.put("deptIds", deptIds);
			query = new Query(params);
			fgshtdzkList = fgshtdzkService.list(query);
			total = fgshtdzkService.count(query);
		} else {
			params.put("uploader", this.getUser().getName());
			query = new Query(params);
			fgshtdzkList = fgshtdzkService.list(query);
			total = fgshtdzkService.count(query);
		}

		PageUtils pageUtils = new PageUtils(fgshtdzkList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("htdzk:fgshtdzk:add")
	String add() {
		return "htdzk/fgshtdzk/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("htdzk:fgshtdzk:edit")
	String edit(@PathVariable("id") String id, Model model) {
		FgshtdzkDO fgshtdzk = fgshtdzkService.get(id);
		model.addAttribute("fgshtdzk", fgshtdzk);
		return "htdzk/fgshtdzk/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("htdzk:fgshtdzk:add")
	public R save(FgshtdzkDO fgshtdzk) {
		if (fgshtdzkService.save(fgshtdzk) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("htdzk:fgshtdzk:edit")
	public R update(FgshtdzkDO fgshtdzk) {
		fgshtdzkService.update(fgshtdzk);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("htdzk:fgshtdzk:remove")
	public R remove(String id) {
		FgshtdzkDO tbContactFiles = fgshtdzkService.get(id);
		String fileName = tbContactFiles.getPath() + tbContactFiles.getReserve1();
		if (fgshtdzkService.remove(id) > 0) {
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
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("htdzk:fgshtdzk:batchRemove")
	public R batchRemove(@RequestParam("ids[]") String[] ids) {
		fgshtdzkService.batchRemove(ids);
		return R.ok();
	}

	/**
	 * 上传
	 */
	@ResponseBody
	@PostMapping("/upload")
	public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, @RequestParam String params) {
		String fileName = file.getOriginalFilename();
		JSONObject json = JSONObject.parseObject(params);
		FgshtdzkDO sysFile = new FgshtdzkDO();
		DeptDO dept = sysDeptService.get(this.getUser().getDeptId());
		sysFile.setReserve4(dept.getDeptId().toString());
		sysFile.setName(fileName);
		fileName = FileUtil.renameToUUID(fileName);
		String os = System.getProperty("os.name");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String ctxPath = null;
		if (os != null && os.startsWith("Windows")) {
			ctxPath = "Fgshtdzk\\";
			ctxPath += ymd + "\\";
		} else {
			ctxPath = "Fgshtdzk//";
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

		if (fgshtdzkService.save(sysFile) > 0) {
			return R.ok().put("fileName", "");
		}
		return R.error();
	}

	/**
	 * 下载
	 */
	@ResponseBody
	@RequestMapping("/download")
	public void download(@RequestParam Map<String, Object> params, HttpServletResponse response,
			HttpServletRequest request) {
		String id = params.get("id").toString();
		FgshtdzkDO fgshtdzk = fgshtdzkService.get(id);

		String name = fgshtdzk.getReserve1();
		String path = fgshtdzk.getPath();
		String realName = fgshtdzk.getName();
		InputStream inputStream = null;
		OutputStream os = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition",
					"attachment;fileName=" + FileUtil.setFileDownloadHeader(request, realName));
			File file = new File(path + name);
			inputStream = new FileInputStream(file);
			os = response.getOutputStream();
			byte[] b = new byte[1024];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (Exception e) {
			LOGGER.error("下载文件失败", e);
		} finally {
			try {
				os.close();
				inputStream.close();
			} catch (IOException e) {
				LOGGER.error("close close fail ", e);
			}
		}
	}

}

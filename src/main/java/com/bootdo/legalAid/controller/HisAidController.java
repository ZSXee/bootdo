package com.bootdo.legalAid.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootdo.legalAid.domain.HisAidDO;
import com.bootdo.legalAid.service.HisAidService;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.excel.AjaxResult;
import com.bootdo.common.utils.excel.ExcelUtil;

/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-08-17 15:04:37
 */
 
@Controller
@RequestMapping("/legalAid/hisAid")
public class HisAidController extends BaseController{
	protected static final Logger LOGGER = LoggerFactory.getLogger(HisAidController.class);
	@Autowired
	private HisAidService hisAidService;
	@Autowired
	private DeptService sysDeptService;
	@Autowired
	private DeptDao sysDeptMapper;
	
	@GetMapping()
	@RequiresPermissions("legalAid:hisAid:hisAid")
	String HiProcinst(Model model){
		
	    return "legalAid/hisAid/hisAid";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> params){
		UserDO userDO  = getUser();
		DeptDO sysDept = sysDeptService.get(userDO.getDeptId());
		//查询权限控制(1：总行部室2：分行部室3：支行4：总行合规风险部5：分行合规风险部)
		String deptIds[] = null;
		Map<String, Object> query = new HashMap<>(16);
		List<DeptDO> sysDeptList = null;
		//风险部特殊处理（查询总行部室发起的流程）
		if(userDO.getUsername().equals("80399999")){
			if(params.get("dept_id")!=null&&params.get("dept_id")!=""){
				params.remove("dept_name");
				Long l = new Long(params.get("dept_id").toString());
				sysDept = sysDeptService.get(l);
				
	    		query.put("parentId", sysDept.getDeptId());
	    		sysDeptList = sysDeptService.list(query);
	    		deptIds = new String[sysDeptList.size()];
	    		for(int i=0;i<sysDeptList.size();i++){
	    			deptIds[i] = sysDeptList.get(i).getDeptId().toString();
	    		}
	    		
				if(sysDept.getDeptType()==1||sysDept.getDeptType()==2){
		            deptIds = new String[] {sysDept.getDeptId().toString()};  
		        	params.put("deptIds", deptIds);
				}
				sysDept.setDeptType(new Long(6));
			}
			params.put("deptIds", deptIds);
		}
        switch(sysDept.getDeptType().intValue()){
	        case 1:
	        case 2:
	        case 3:
	            deptIds = new String[] {sysDept.getDeptId().toString()};  
	        	params.put("deptIds", deptIds);
	            break;
	        case 4:
	    		sysDeptList = sysDeptMapper.listzh();
	    		deptIds = new String[sysDeptList.size()];
	    		for(int i=0;i<sysDeptList.size();i++){
	    			deptIds[i] = sysDeptList.get(i).getDeptId().toString();
	    		}
	        	params.put("deptIds", deptIds);
	            break;
	        case 5:
	    		query.put("parentId", sysDept.getParentId());
	    		sysDeptList = sysDeptService.list(query);
	    		deptIds = new String[sysDeptList.size()];
	    		for(int i=0;i<sysDeptList.size();i++){
	    			deptIds[i] = sysDeptList.get(i).getDeptId().toString();
	    		}
	        	params.put("deptIds", deptIds);
	            break;
	        case 6:
	            break;
	        default:
	            System.out.println("机构设置有问题");
	            break;
        }
		//查询列表数据
        query = new Query(params);
		List<HisAidDO> hisAidList = hisAidService.list(query);
		int total = hisAidService.count(query);
		PageUtils pageUtils = new PageUtils(hisAidList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@PostMapping("/export")
	public AjaxResult export(@RequestParam Map<String, Object> params){
		UserDO userDO  = getUser();
		DeptDO sysDept = sysDeptService.get(userDO.getDeptId());
		//查询权限控制(1：总行部室2：分行部室3：支行4：总行合规风险部5：分行合规风险部)
		String deptIds[] = null;
		Map<String, Object> query = new HashMap<>(16);
		List<DeptDO> sysDeptList = null;
		//风险部特殊处理（查询总行部室发起的流程）
		if(userDO.getUsername().equals("80399999")){
			if(params.get("dept_id")!=null&&params.get("dept_id")!=""){
				params.remove("dept_name");
				Long l = new Long(params.get("dept_id").toString());
				sysDept = sysDeptService.get(l);
				sysDept.setDeptType(new Long(6));
	    		query.put("parentId", sysDept.getDeptId());
	    		sysDeptList = sysDeptService.list(query);
	    		deptIds = new String[sysDeptList.size()];
	    		for(int i=0;i<sysDeptList.size();i++){
	    			deptIds[i] = sysDeptList.get(i).getDeptId().toString();
	    		}
			}
			params.put("deptIds", deptIds);
		}
        switch(sysDept.getDeptType().intValue()){
	        case 1:
	        case 2:
	        case 3:
	            deptIds = new String[] {sysDept.getDeptId().toString()};  
	        	params.put("deptIds", deptIds);
	            break;
	        case 4:
	    		sysDeptList = sysDeptMapper.listzh();
	    		deptIds = new String[sysDeptList.size()];
	    		for(int i=0;i<sysDeptList.size();i++){
	    			deptIds[i] = sysDeptList.get(i).getDeptId().toString();
	    		}
	        	params.put("deptIds", deptIds);
	            break;
	        case 5:
	    		query.put("parentId", sysDept.getParentId());
	    		sysDeptList = sysDeptService.list(query);
	    		deptIds = new String[sysDeptList.size()];
	    		for(int i=0;i<sysDeptList.size();i++){
	    			deptIds[i] = sysDeptList.get(i).getDeptId().toString();
	    		}
	        	params.put("deptIds", deptIds);
	            break;
	        case 6:
	            break;
	        default:
	            System.out.println("机构设置有问题");
	            break;
        }
		//查询列表数据
        query = new Query(params);
		List<HisAidDO> hiAidList = hisAidService.list(query);
		ExcelUtil<HisAidDO> util = new ExcelUtil<HisAidDO>(HisAidDO.class);
		
		return util.exportExcel(hiAidList, "法律援助查询统计");
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
}

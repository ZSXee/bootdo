package com.bootdo.activiti.controller;

import com.bootdo.activiti.dao.HiTaskinstDao;
import com.bootdo.activiti.dao.MyHiTaskinstDao;
import com.bootdo.activiti.domain.HiTaskinstDO;
import com.bootdo.activiti.domain.MyHiTaskinstDO;
import com.bootdo.activiti.domain.ReProcdefDO;
import com.bootdo.activiti.service.ActTaskService;
import com.bootdo.activiti.service.ReProcdefService;
import com.bootdo.activiti.vo.CommentVO;
import com.bootdo.activiti.vo.TaskVO;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.contact.service.TbContactInfoService;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.system.service.UserService;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.CommentEntity;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

 */
@RequestMapping("activiti/task")
@RestController
public class TaskController extends BaseController{
	@Autowired
	UserService userService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    FormService formService;
    @Autowired
    TaskService taskService;
    @Autowired
    HistoryService historyService;
    @Autowired
    ActTaskService actTaskService;
	@Autowired
	TbContactInfoService tbContactInfoService;
    @Autowired
    UserRoleDao userRoleMapper;
	@Autowired
	DeptService sysDeptService;
	@Autowired
	ReProcdefService reProcdefService;
	@Autowired
	HiTaskinstDao hiTaskinstDao;
	@Autowired
	MyHiTaskinstDao myHiTaskinstDao;

    @GetMapping("goto")
    public ModelAndView gotoTask(){
        return new ModelAndView("act/task/gotoTask");
    }

    @GetMapping("/gotoList")
    PageUtils list(@RequestParam Map<String, Object> params) {

        List<Object> list = new ArrayList<>();
    	UserDO userDO  = userService.get(getUserId());
		//查询列表数据
        Query query = new Query(params);
    	List<ReProcdefDO> reProcdefList = reProcdefService.listNew(query);

        for(ReProcdefDO processDefinition: reProcdefList){
    		DeptDO dept = sysDeptService.get(userDO.getDeptId());
    		//1：总行部室  2：分行部室   3：支行  4：总行合规风险部    5：分行合规风险部
    		switch(dept.getDeptType().intValue()){
 	        case 1:
 	        	if(processDefinition.getKey().equals("zonghang"))
 	        		list.add(processDefinition);
 	        	if(processDefinition.getKey().substring(0,4).equals("gesh"))
 	        		list.add(processDefinition);
 	        	if(processDefinition.getKey().substring(0,4).equals("zhfl"))
 	        		list.add(processDefinition);
	        	if(processDefinition.getKey().equals("flxz"))
	        		list.add(processDefinition);
	        	if(processDefinition.getKey().equals("zhbmwplsrksq"))
	        		list.add(processDefinition);
	        	if(processDefinition.getKey().equals("zyzgzh"))
	        		list.add(processDefinition);
 	            break;
 	        case 2:
 	        	if(processDefinition.getKey().equals("fenhang"))
 	        		list.add(processDefinition);
 	        	if(processDefinition.getKey().substring(0,4).equals("zyzg"))
 	        		list.add(processDefinition);
// 	        	if(processDefinition.getKey().substring(0,4).equals("fhfl"))
// 	        		list.add(processDefinition);
	        	if(processDefinition.getKey().equals("flxz"))
	        		list.add(processDefinition);
	        	if(processDefinition.getKey().substring(0,4).equals("wpls"))
	        		list.add(processDefinition);
 	        	break;
 	        case 3:
 	        	if(processDefinition.getKey().equals("zhihang"))
 	        		list.add(processDefinition);
 	        	if(processDefinition.getKey().substring(0,4).equals("zyzg"))
 	        		list.add(processDefinition);
 	        	if(processDefinition.getKey().substring(0,4).equals("shfl"))
 	        		list.add(processDefinition);
	        	if(processDefinition.getKey().equals("flxz"))
	        		list.add(processDefinition);
 	            break;
 	        case 4:
 	        	if(processDefinition.getKey().equals("fengxian"))
 	        		list.add(processDefinition);
 	        	if(processDefinition.getKey().substring(0,4).equals("gesh"))
 	        		list.add(processDefinition);
	        	if(processDefinition.getKey().equals("flxz"))
	        		list.add(processDefinition);
 	            break;
 	        case 5:
 	        	if(processDefinition.getKey().substring(0,4).equals("wpls"))
	        		list.add(processDefinition);
 	        	if(processDefinition.getKey().substring(0,4).equals("flhg"))
 	        		list.add(processDefinition);
	        	if(processDefinition.getKey().equals("flxz"))
	        		list.add(processDefinition);
 	        	if(processDefinition.getKey().equals("fengxian"))
 	        		list.add(processDefinition);
// 	        	if(processDefinition.getKey().equals("zyzgsq"))
// 	        		list.add(processDefinition);
 	        	if(processDefinition.getKey().equals("fhflhglssq"))
	        		list.add(processDefinition);
 	            break;
 	        default:
 	            break;
            }

        }

    	PageUtils pageUtils = new PageUtils(list, list.size());

        return pageUtils;
    }

    @GetMapping("/form/{procDefId}")
    public void startForm(@PathVariable("procDefId") String procDefId  ,HttpServletResponse response) throws IOException {
        String formKey = actTaskService.getFormKey(procDefId, null);
        response.sendRedirect(formKey);
    }

    @GetMapping("/form/{procDefId}/{taskId}")
    public void form(@PathVariable("procDefId") String procDefId,@PathVariable("taskId") String taskId ,HttpServletResponse response) throws IOException {
        // 获取流程XML上的表单KEY
        String formKey = actTaskService.getFormKey(null, taskId);
        response.sendRedirect(formKey+"/"+taskId);
    }

    @GetMapping("/todo")
    ModelAndView todo(Model model){
    	UserDO userDO  = userService.get(getUserId());
    	List<Long> roles = userDO.getRoleIds();
    	for(Long role : roles){
    		if(role==3)
    			model.addAttribute("leader", "1");
    	}
    	model.addAttribute("userDO", userDO);
        return new ModelAndView("act/task/todoTask");
    }

    /**
     * 查询我的待办任务
     */
    @GetMapping("/todoList")
    List<TaskVO> todoList(){
    	UserDO userDO  = userService.get(getUserId());
        List<Task> tasks = taskService.createTaskQuery()
        		.taskAssignee(userDO.getUsername())
        		.orderByTaskCreateTime().desc()
        		.list();

        List<TaskVO> taskVOS =  new ArrayList<>();
        //角色为信息查询岗（5）无法办理任务
        for (Long s : userDO.getRoleIds()){
        	if(s==5)
        		return taskVOS;
        }
        for(Task task : tasks){
        	HistoricProcessInstance  historicProcessInstance= historyService.createHistoricProcessInstanceQuery().
        			processInstanceId(task.getProcessInstanceId()).singleResult();
            TaskVO taskVO = new TaskVO(task);
            if(historicProcessInstance != null){
                UserDO userDO_tmp  = userService.get(historicProcessInstance.getStartUserId());
                taskVO.setStartUser(userDO_tmp==null?"":userDO_tmp.getName());
                taskVO.setProcessDefinitionName(historicProcessInstance.getProcessDefinitionName());
                taskVO.setStartTime(historicProcessInstance.getStartTime());
                taskVO.setCntName(historicProcessInstance.getName());
            }
            taskVO.setUser(userDO.getUsername());
            taskVOS.add(taskVO);
        }

        return taskVOS;
    }
    /**
     * 查询我的已办任务
     */
    @ResponseBody
    @GetMapping("/toFinnishList")
    public PageUtils toFinnishList(@RequestParam Map<String, Object> params){
    	UserDO userDO  = userService.get(getUserId());
    	DeptDO dept = sysDeptService.get(userDO.getDeptId());
		//风险部特殊处理（查询全部发起的流程）
		if(userDO.getUsername().equals("80399999")){
			params.put("cntAdminDep", "总行");
		}
		else{
			params.put("assignee", userDO.getUsername());
		}
        Query query = new Query(params);
        List<HiTaskinstDO> list= hiTaskinstDao.list(query);
        int size = hiTaskinstDao.count(query);
		PageUtils pageUtils = new PageUtils(list, size);
		return pageUtils;
    }
    /**
     * 查询所有任务
     */
    @GetMapping("toListAll")
    public ModelAndView toFinnishListAll(){
        return new ModelAndView("act/task/listAll");
    }
    /**
     * 查询所有任务
     */
    @ResponseBody
    @GetMapping("/toFinnishListAll")
    public PageUtils toFinnishListAll(@RequestParam Map<String, Object> params){
    	//UserDO userDO  = userService.get(getUserId());
    	//DeptDO dept = sysDeptService.get(userDO.getDeptId());

        Query query = new Query(params);
        List<HiTaskinstDO> list= hiTaskinstDao.list(query);
        int size = hiTaskinstDao.count(query);
		PageUtils pageUtils = new PageUtils(list, size);
		return pageUtils;
    }
    /**
     * 查询我发起的任务
     */
    @ResponseBody
    @GetMapping("/toMyTakeList")
    public PageUtils toMyTakeList(@RequestParam Map<String, Object> params){
    	UserDO userDO  = userService.get(getUserId());
    	params.put("startUser", userDO.getUsername());
        Query query = new Query(params);
        List<MyHiTaskinstDO> list= myHiTaskinstDao.list(query);
        int size = myHiTaskinstDao.count(query);
		PageUtils pageUtils = new PageUtils(list, size);
		return pageUtils;
    }

    /**
     * 查询部门待办任务
     */
    @GetMapping("/todoBMList")
    List<TaskVO> todoBMList(){
    	UserDO userDO  = userService.get(getUserId());
        List<Task> tasks = taskService.createTaskQuery()
        		.taskCandidateGroup(userDO.getDeptId().toString())
        		.orderByTaskCreateTime().asc()
        		.list();
        List<TaskVO> taskVOS =  new ArrayList<>();
        //角色为信息查询岗（5）无法办理任务
        for (Long s : userDO.getRoleIds()){
        	if(s==5)
        		return taskVOS;
        }
        for(Task task : tasks){
        	HistoricProcessInstance  historicProcessInstance= historyService.createHistoricProcessInstanceQuery().
        			processInstanceId(task.getProcessInstanceId()).singleResult();
            TaskVO taskVO = new TaskVO(task);
            taskVO.setProcessDefinitionName(historicProcessInstance.getProcessDefinitionName());
            userDO  = userService.get(historicProcessInstance.getStartUserId());
            taskVO.setStartUser(userDO.getName());
            taskVO.setCntName(historicProcessInstance.getName());
            taskVOS.add(taskVO);

        }
        return taskVOS;
    }
    /**
     * 查询批注
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/listComment")
    List<CommentVO> listComment(String procInsId) throws UnsupportedEncodingException{
    	List<Comment> list = taskService.getProcessInstanceComments(procInsId);
        List<CommentVO> commentVOS =  new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++) {
        	CommentVO commentVO = new CommentVO((CommentEntity) list.get(i));
        	commentVOS.add(commentVO);
        }
        return commentVOS;
    }
    /**
     * 领取任务
     */
    @ResponseBody
    @GetMapping("/claim/{taskId}")
    public R claim(@PathVariable("taskId") String taskId ,HttpServletResponse response) throws IOException {
        UserDO userDO  = userService.get(getUserId());
        try {
            taskService.claim(taskId, userDO.getUsername());
            return R.ok();
        } catch (Exception e) {
        	return R.error();
        }
    }
    /**
     * 读取带跟踪的图片
     */
    @RequestMapping(value = "/trace/photo/{procDefId}")
    public void tracePhoto(@PathVariable("procDefId") String procDefId, HttpServletResponse response) throws Exception {
        InputStream imageStream = actTaskService.tracePhoto(procDefId);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }
    /**
     * 终止任务
     */
    @ResponseBody
    @GetMapping("/deleteProcessInstance/{procInsId}")
    public R shutdown(@PathVariable("procInsId") String procInsId ,HttpServletResponse response) throws IOException {
        try {
        	actTaskService.deleteProcessInstance(procInsId, "管理员手工终止");
            return R.ok();
        } catch (Exception e) {
        	return R.error();
        }
    }

}

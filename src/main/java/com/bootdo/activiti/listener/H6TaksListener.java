package com.bootdo.activiti.listener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.EngineServices;
import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricProcessInstance;
import com.bootdo.activiti.utils.SpringUtil;
import com.bootdo.common.utils.SendRTXMessage;
import com.bootdo.system.service.UserService;

public class H6TaksListener implements ExecutionListener  {

    /**
	 * 设置风险部门负责人
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		EngineServices engineServices = arg0.getEngineServices();
		
		HistoryService historyService = engineServices.getHistoryService();
    	HistoricProcessInstance  historicProcessInstance= historyService.createHistoricProcessInstanceQuery().processInstanceId(arg0.getProcessInstanceId()).singleResult();
		
		UserService userService = (UserService)SpringUtil.getBean(UserService.class);
		// TODO Auto-generated method stub
		//设置部门负责人
		String username = historicProcessInstance.getStartUserId();
		Long deptId = userService.get(username).getDeptId();
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("roleId", "3");//3角色为负责人
		query.put("deptId", deptId);
		if(userService != null){
			List<String> sysUserList = userService.listByRoleDept(query);
			arg0.setVariable("applyUserId",sysUserList.get(0));
			
//			SendRTXMessage rtxSend = new SendRTXMessage();
//			rtxSend.SendRTXNotify(sysUserList.get(0), "非格式合同管理系统", "您收到一个非格式合同任务，需要审批。", "0", "3");

		}
	}
}


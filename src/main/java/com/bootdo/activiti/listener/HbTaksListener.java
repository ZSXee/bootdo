package com.bootdo.activiti.listener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bootdo.activiti.utils.SpringUtil;
//import com.bootdo.common.utils.SendRTXMessage;
//import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.service.UserService;

public class HbTaksListener implements ExecutionListener  {

    /**
	 * 设置总行风险部门负责人
	 */
	private static final long serialVersionUID = 1L;
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(HbTaksListener.class);
	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		
		UserService userService = (UserService)SpringUtil.getBean(UserService.class);
		// TODO Auto-generated method stub
		//设置部门负责人
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("roleId", "3");//3角色为负责人
		query.put("deptId", new Long(8801));
		if(userService != null){
			List<String> sysUserList = userService.listByRoleDept(query);
			arg0.setVariable("applyUserId",sysUserList.get(0));
			
			//SendRTXMessage rtxSend = new SendRTXMessage();
			//rtxSend.SendRTXNotify(sysUserList.get(0), "非格式合同管理系统", "您收到一个非格式合同任务，需要审批。", "0", "3");
			
		}
	}
}
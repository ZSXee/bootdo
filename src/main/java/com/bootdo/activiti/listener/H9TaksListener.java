package com.bootdo.activiti.listener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bootdo.activiti.utils.SpringUtil;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.service.DeptService;
import com.bootdo.system.service.UserService;

public class H9TaksListener implements ExecutionListener  {

    /**
	 * 1.总行非风险部门发起设置为总行风险负责人
	 * 2.分行非风险部门发起设置为分行风险负责人
	 * 3.分行风险部门发起设置为总行风险负责人
	 */
	private static final long serialVersionUID = 1L;
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(H1TaksListener.class);
	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		
		UserService userService = (UserService)SpringUtil.getBean(UserService.class);
		// TODO Auto-generated method stub
		Long deptId = ShiroUtils.getUser().getDeptId();
		DeptService deptService = (DeptService)SpringUtil.getBean(DeptService.class);
		DeptDO dept = deptService.get(deptId);
		Map<String, Object> query1 = new HashMap<String, Object>();
		Map<String, Object> query2 = new HashMap<String, Object>();
		Map<String, Object> query3 = new HashMap<String, Object>();
		List<String> sysUserList1 = null;//总行风险部负责人
		List<String> sysUserList2 = null;//分行风险部负责人
		
		if(dept.getDeptType()==1){
			query1.put("roleId", "3");//3角色为负责人
			query1.put("deptId", "8801");//4总行合规风险部
			if(userService != null){
				sysUserList1 = userService.listByRoleDept(query1);	
			}
		}else{
			query1.put("roleId", "3");//3角色为负责人
			query1.put("deptId", "8801");//4总行合规风险部
			if(userService != null){
				sysUserList1 = userService.listByRoleDept(query1);	
			}
			query2.put("parentDeptId", dept.getParentId()); 
			query2.put("deptType", new Long(5)); //5分行合规风险部
			DeptDO deptHG = deptService.getHG(query2);
			
			query3.put("roleId", "3");//3角色为负责人
			query3.put("deptId", deptHG.getDeptId());//5分行合规风险部
			if(userService != null){
				sysUserList2 = userService.listByRoleDept(query3);	
			}
		}
		
		//1：总行部室   2：分行部室   3：支行   4：总行合规风险部   5：分行合规风险部
        switch(dept.getDeptType().intValue()){
	        case 1:
	        	arg0.setVariable("applyUserId",sysUserList1.get(0));	
	            break;
	        case 2:
	        	arg0.setVariable("applyUserId",sysUserList2.get(0));	
	        	break;
	        case 3:
	        	arg0.setVariable("applyUserId",sysUserList2.get(0));	
	            break;
	        case 4:
	            break;
	        case 5:
	        	arg0.setVariable("applyUserId",sysUserList1.get(0));	
	            break;
	        default:
	            break;
        }
	}
}
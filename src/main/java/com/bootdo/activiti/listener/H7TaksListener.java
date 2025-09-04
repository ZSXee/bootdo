package com.bootdo.activiti.listener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import com.bootdo.activiti.utils.SpringUtil;
import com.bootdo.common.utils.SendRTXMessage;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.DeptService;


public class H7TaksListener implements ExecutionListener  {

    /**
	 * 设置分行风险部门
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		
		DeptService deptService = (DeptService)SpringUtil.getBean(DeptService.class);
		Long deptId = ShiroUtils.getUser().getDeptId();
		DeptDO dept = deptService.get(deptId);
		Long parentDeptId = dept.getParentId();
		
		Map<String, Object> query = new HashMap<>();
		query.put("parentDeptId", parentDeptId); 
		query.put("deptType", new Long(5)); 
		DeptDO deptHG = deptService.getHG(query);
		
		arg0.setVariable("candidateGroups",deptHG.getDeptId().toString());
		//会签结束，将会签变量置为0（非会签）
		arg0.setVariable("countersign","0");

		
//		UserDao userMapper = (UserDao)SpringUtil.getBean(UserDao.class);
//		Map<String, Object> map = new HashMap<>();
//		map.put("deptId", deptHG.getDeptId().toString());
//		map.put("status", "1");
//		List<UserDO> list = userMapper.list(map);
//		String users = null;
//		for(UserDO user : list) {
//			users = users + ',' + user.getUsername();
//		}
//		SendRTXMessage rtxSend = new SendRTXMessage();
//		rtxSend.SendRTXNotify(users.substring(5), "非格式合同管理系统", "部门收到一个非格式合同任务，需要审批。", "0", "3");
		
	}
}
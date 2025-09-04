package com.bootdo.activiti.listener;
import org.activiti.engine.EngineServices;
import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricProcessInstance;
import com.bootdo.common.utils.SendRTXMessage;

public class H8TaksListener implements ExecutionListener  {

    /**
	 * 退回，设置为流程发起人
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		EngineServices engineServices = arg0.getEngineServices();
		HistoryService historyService = engineServices.getHistoryService();
    	HistoricProcessInstance  historicProcessInstance= historyService.createHistoricProcessInstanceQuery().processInstanceId(arg0.getProcessInstanceId()).singleResult();
		// TODO Auto-generated method stub
		//设置为流程发起人
		String username = historicProcessInstance.getStartUserId();
		arg0.setVariable("applyUserId",username);
		
//		SendRTXMessage rtxSend = new SendRTXMessage();
//		rtxSend.SendRTXNotify(username, "非格式合同管理系统", "您收到一个非格式合同任务，需要审批。", "0", "3");

		
	}
}


package com.bootdo.activiti.listener;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import com.bootdo.common.utils.SendRTXMessage;

public class H4TaksListener implements ExecutionListener  {

    /**
	 * 子流程中回退后设置处理人处理
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		
		arg0.setVariable("applyUserId",arg0.getVariable("applyUserId_tmp"));

//		SendRTXMessage rtxSend = new SendRTXMessage();
//		try {
//			rtxSend.SendRTXNotify(arg0.getVariable("applyUserId_tmp").toString(), "非格式合同管理系统", "您收到一个非格式合同任务，需要审批。", "0", "3");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
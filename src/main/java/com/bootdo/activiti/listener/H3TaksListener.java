package com.bootdo.activiti.listener;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bootdo.common.utils.SendRTXMessage;


public class H3TaksListener implements TaskListener  {

    /**
	 * 子流程退回，设置处理人
	 */
	private static final long serialVersionUID = 1L;
	public void notify(DelegateTask arg0) {
		// TODO Auto-generated method stub
		arg0.setVariable("applyUserId_tmp",arg0.getAssignee());
		
//		SendRTXMessage rtxSend = new SendRTXMessage();
//		try {
//			rtxSend.SendRTXNotify(arg0.getAssignee(), "非格式合同管理系统", "您收到一个非格式合同任务，需要审批。", "0", "3");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
	}
}


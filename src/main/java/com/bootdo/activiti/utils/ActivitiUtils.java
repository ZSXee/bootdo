package com.bootdo.activiti.utils;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**

 */
@Component
public class ActivitiUtils {
    /**
     * 根据taskId查找businessKey
     */
    @Autowired
    TaskService taskService;
    @Autowired
    HistoryService historyService;
    @Autowired
    RuntimeService runtimeService;
    public String getBusinessKeyByTaskId(String taskId){
        Task task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        ProcessInstance pi = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        return pi.getBusinessKey();
    }
    public String getInstanceIdByTaskId(String taskId){
        Task task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        return task.getProcessInstanceId();
    }
    public Task getTaskByTaskId(String taskId){
        Task task = taskService
                .createTaskQuery()
                .taskId(taskId)
                .singleResult();
        return task;
    }
    public String getBusinessKeyByInstanceId(String instanceId){
    	
    	HistoricProcessInstance pi = historyService
        		.createHistoricProcessInstanceQuery()
                .processInstanceId(instanceId)
                .singleResult();
        return pi.getBusinessKey();
    }
}

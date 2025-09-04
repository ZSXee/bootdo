package com.bootdo.activiti.vo;

import java.text.SimpleDateFormat;

import org.activiti.engine.history.HistoricTaskInstance;

/**

 */
public class HistoricTaskVO  {

    public HistoricTaskVO(HistoricTaskInstance task){

    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setId(task.getId());
        this.setKey(task.getTaskDefinitionKey());
        this.setName(task.getName());
        this.setDescription(task.getDescription());
        this.setAssignee(task.getAssignee());
        this.setFormKey(task.getFormKey());
        this.setProcessId(task.getProcessInstanceId());
        this.setProcessDefinitionId(task.getProcessDefinitionId());
        this.setExecutionId(task.getExecutionId());
        this.setStartTime(df.format(task.getStartTime()));
        this.setEndTime(df.format(task.getEndTime()));
    }
    private String id;
    private String name;
    private String key;
    private String description;
    private String formKey;
    private String assignee;
    private String processId;
    private String processDefinitionId;
    private String processDefinitionName;
    private String executionId;
    private String startTime;
    private String endTime;
    private String cntName;
    private String cntAdmin;
    private String cntAdminDep;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCntName() {
		return cntName;
	}

	public void setCntName(String cntName) {
		this.cntName = cntName;
	}

	public String getCntAdmin() {
		return cntAdmin;
	}

	public void setCntAdmin(String cntAdmin) {
		this.cntAdmin = cntAdmin;
	}

	public String getCntAdminDep() {
		return cntAdminDep;
	}

	public void setCntAdminDep(String cntAdminDep) {
		this.cntAdminDep = cntAdminDep;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}
	
}

package com.bootdo.activiti.vo;

import java.util.Date;

import org.activiti.engine.history.HistoricProcessInstance;

/**

 */
public class HistoricProcessInstanceVO  {

    public HistoricProcessInstanceVO(HistoricProcessInstance task){

        this.setId(task.getId());
        this.setKey(task.getProcessDefinitionKey());
        this.setName(task.getName());
        this.setDescription(task.getDescription());
        this.setStartUserId(task.getStartUserId());
        this.setBusinessKey(task.getBusinessKey());
        this.setProcessDefinitionId(task.getProcessDefinitionId());
        this.setProcessDefinitionName(task.getProcessDefinitionName());
        this.setStartTime(task.getStartTime());
        if(task.getEndTime()!=null)
        	this.setEndTime(task.getEndTime());
    }
    private String id;
    private String name;
    private String key;
    private String description;
    private String businessKey;
    private String startUserId;
    private String execUser;
    private String processDefinitionId;
    private String processDefinitionName;
    private String executionId;
    private Date startTime;
    private Date endTime;

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

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date date) {
		this.startTime = date;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date date) {
		this.endTime = date;
	}

	public String getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getExecUser() {
		return execUser;
	}

	public void setExecUser(String execUser) {
		this.execUser = execUser;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}
}

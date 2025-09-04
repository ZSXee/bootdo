package com.bootdo.activiti.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-11-02 18:55:51
 */
public class HiTaskinstDO implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	private String id;
	//
	private String procInstId;
	//
	private String name;
	//
	private String contactName;
	//
	private String assignee;
	//
	private String startUser;
	//
	private Date startTime;
	//
	private Date endTime;
	//
    private String processDefinitionId;
    //
    private String processDefinitionName;
    //
    private String cntAdminDep;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProcInstId() {
		return procInstId;
	}
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}
	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}
	public String getCntAdminDep() {
		return cntAdminDep;
	}
	public void setCntAdminDep(String cntAdminDep) {
		this.cntAdminDep = cntAdminDep;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getStartUser() {
		return startUser;
	}
	public void setStartUser(String startUser) {
		this.startUser = startUser;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

}

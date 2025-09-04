package com.bootdo.activiti.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2023-07-12 11:40:21
 */
public class AddhiCommentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String type;
	//
	private Date time;
	//
	private String userId;
	//
	private String taskId;
	//
	private String procInstId;
	//
	private String action;
	//
	private String message;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	/**
	 * 获取：
	 */
	public Date getTime() {
		return time;
	}
	/**
	 * 设置：
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * 设置：
	 */
	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}
	/**
	 * 获取：
	 */
	public String getProcInstId() {
		return procInstId;
	}
	/**
	 * 设置：
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * 获取：
	 */
	public String getAction() {
		return action;
	}
	/**
	 * 设置：
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 获取：
	 */
	public String getMessage() {
		return message;
	}
}

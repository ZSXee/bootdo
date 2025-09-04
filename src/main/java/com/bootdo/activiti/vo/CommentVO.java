package com.bootdo.activiti.vo;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.activiti.engine.impl.persistence.entity.CommentEntity;

/**

 */
public class CommentVO  {

    public CommentVO(CommentEntity comment) throws UnsupportedEncodingException{
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.setId(comment.getId());
        this.setType(comment.getType());
        this.setUserId(comment.getUserId());
        this.setTime(df.format(comment.getTime()));
        this.setProcessInstanceId(comment.getProcessInstanceId());
        this.setTaskId(comment.getTaskId());
        this.setAction(comment.getAction());
        this.setMessage(comment.getMessage());
        this.setFullMessage(new String(comment.getFullMessage().getBytes("GBK"),"UTF-8"));
    }
    
    protected String id;
    protected String type;
    protected String userId;
    protected String time;
    protected String taskId;
    protected String processInstanceId;
    protected String action;
    protected String message;
    protected String fullMessage;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFullMessage() {
		return fullMessage;
	}
	public void setFullMessage(String fullMessage) {
		this.fullMessage = fullMessage;
	}


}

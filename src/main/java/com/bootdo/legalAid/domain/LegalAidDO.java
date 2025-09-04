package com.bootdo.legalAid.domain;

import java.io.Serializable;


/**
 * 法律援助信息表
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-08-13 09:26:47
 */
public class LegalAidDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//援助ID
	private String aidId;
	//流程ID
	private String procInsId;
	//援助类型
	private String aidType;
	//援助理由
	private String aidContent;
	//预留字段1
	private String aidReserve1;
	//预留字段2
	private String aidReserve2;
	//预留字段3
	private String aidReserve3;
	//预留字段4
	private String aidReserve4;
	//预留字段5
	private String aidReserve5;
	//流程主键
	private String key;
	//taskPass
	private String taskPass;
	//Task_ID
	private String taskId;
	//批注
	private String taskComment;	

	/**
	 * 设置：援助ID
	 */
	public void setAidId(String aidId) {
		this.aidId = aidId;
	}
	/**
	 * 获取：援助ID
	 */
	public String getAidId() {
		return aidId;
	}
	/**
	 * 设置：流程ID
	 */
	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	/**
	 * 获取：流程ID
	 */
	public String getProcInsId() {
		return procInsId;
	}
	/**
	 * 设置：援助类型
	 */
	public void setAidType(String aidType) {
		this.aidType = aidType;
	}
	/**
	 * 获取：援助类型
	 */
	public String getAidType() {
		return aidType;
	}
	/**
	 * 设置：援助理由
	 */
	public void setAidContent(String aidContent) {
		this.aidContent = aidContent;
	}
	/**
	 * 获取：援助理由
	 */
	public String getAidContent() {
		return aidContent;
	}
	/**
	 * 设置：预留字段1
	 */
	public void setaidReserve1(String aidReserve1) {
		this.aidReserve1 = aidReserve1;
	}
	/**
	 * 获取：预留字段1
	 */
	public String getaidReserve1() {
		return aidReserve1;
	}
	/**
	 * 设置：预留字段2
	 */
	public void setaidReserve2(String aidReserve2) {
		this.aidReserve2 = aidReserve2;
	}
	/**
	 * 获取：预留字段2
	 */
	public String getaidReserve2() {
		return aidReserve2;
	}
	/**
	 * 设置：预留字段3
	 */
	public void setaidReserve3(String aidReserve3) {
		this.aidReserve3 = aidReserve3;
	}
	/**
	 * 获取：预留字段3
	 */
	public String getaidReserve3() {
		return aidReserve3;
	}
	/**
	 * 设置：预留字段4
	 */
	public void setaidReserve4(String aidReserve4) {
		this.aidReserve4 = aidReserve4;
	}
	/**
	 * 获取：预留字段4
	 */
	public String getaidReserve4() {
		return aidReserve4;
	}
	/**
	 * 设置：预留字段5
	 */
	public void setaidReserve5(String aidReserve5) {
		this.aidReserve5 = aidReserve5;
	}
	/**
	 * 获取：预留字段5
	 */
	public String getaidReserve5() {
		return aidReserve5;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * 获取：taskPass
	 */
	public String getTaskPass() {
		return taskPass;
	}
	/**
	 * 设置：taskPass
	 */
	public void setTaskPass(String taskPass) {
		this.taskPass = taskPass;
	}
	/**
	 * 获取：TaskId
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * 设置：TaskId
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskComment() {
		return taskComment;
	}
	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}
}

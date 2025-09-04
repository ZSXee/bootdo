package com.bootdo.legalAid.domain;

import java.io.Serializable;
import java.util.Date;

import com.bootdo.common.utils.excel.Excel;



/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-30 15:04:37
 */
public class HisAidDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	@Excel(name = "ID")
	private String id;
	//
	private String procInstId;
	//
	private String businessKey;
	//
	private String procDefId;
	@Excel(name = "开始时间")
	private Date startTime;
	@Excel(name = "结束时间")
	private Date endTime;
	//
	private Long duration;
	@Excel(name = "发起人")
	private String startUserId;
	//
	private String startActId;
	//
	private String endActId;
	//
	private String superProcessInstanceId;
	//
	private String deleteReason;
	//
	private String tenantId;
	//
	private String name;
	//
	private String userName;
	@Excel(name = "发起机构")
	private String deptId;
	//援助类型
	@Excel(name = "援助类型")
	private String aidType;
	//援助理由
	@Excel(name = "援助理由")
	private String aidContent;
	//机构名称
	//@Excel(name = "机构名称")
	private String deptName;
	//预留字段1
	//@Excel(name = "预留字段1")
	private String cntReserve1;
	//预留字段2
	//@Excel(name = "预留字段2")
	private String cntReserve2;
	//预留字段3
	//@Excel(name = "预留字段3")
	private String cntReserve3;
	//预留字段4
	//@Excel(name = "预留字段4")
	private String cntReserve4;
	//预留字段5
	//@Excel(name = "预留字段5")
	private String cntReserve5;
	//任务节点
	//@Excel(name = "任务节点")
	private String taskName;
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
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	/**
	 * 获取：
	 */
	public String getBusinessKey() {
		return businessKey;
	}
	/**
	 * 设置：
	 */
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	/**
	 * 获取：
	 */
	public String getProcDefId() {
		return procDefId;
	}
	/**
	 * 设置：
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：
	 */
	public Date getStartTime() {
//		return DateUtils.format(startTime,DateUtils.DATE_TIME_PATTERN);
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public Date getEndTime() {
//		return DateUtils.format(endTime,DateUtils.DATE_TIME_PATTERN);
		return endTime;
	}
	/**
	 * 设置：
	 */
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	/**
	 * 获取：
	 */
	public Long getDuration() {
		return duration;
	}
	/**
	 * 设置：
	 */
	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}
	/**
	 * 获取：
	 */
	public String getStartUserId() {
		return startUserId;
	}
	/**
	 * 设置：
	 */
	public void setStartActId(String startActId) {
		this.startActId = startActId;
	}
	/**
	 * 获取：
	 */
	public String getStartActId() {
		return startActId;
	}
	/**
	 * 设置：
	 */
	public void setEndActId(String endActId) {
		this.endActId = endActId;
	}
	/**
	 * 获取：
	 */
	public String getEndActId() {
		return endActId;
	}
	/**
	 * 设置：
	 */
	public void setSuperProcessInstanceId(String superProcessInstanceId) {
		this.superProcessInstanceId = superProcessInstanceId;
	}
	/**
	 * 获取：
	 */
	public String getSuperProcessInstanceId() {
		return superProcessInstanceId;
	}
	/**
	 * 设置：
	 */
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	/**
	 * 获取：
	 */
	public String getDeleteReason() {
		return deleteReason;
	}
	/**
	 * 设置：
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	/**
	 * 获取：
	 */
	public String getTenantId() {
		return tenantId;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getAidType() {
		return aidType;
	}
	public void setAidType(String aidType) {
		this.aidType = aidType;
	}
	public String getAidContent() {
		return aidContent;
	}
	public void setAidContent(String aidContent) {
		this.aidContent = aidContent;
	}
	public String getCntReserve1() {
		return cntReserve1;
	}
	public void setCntReserve1(String cntReserve1) {
		this.cntReserve1 = cntReserve1;
	}
	public String getCntReserve2() {
		return cntReserve2;
	}
	public void setCntReserve2(String cntReserve2) {
		this.cntReserve2 = cntReserve2;
	}
	public String getCntReserve3() {
		return cntReserve3;
	}
	public void setCntReserve3(String cntReserve3) {
		this.cntReserve3 = cntReserve3;
	}
	public String getCntReserve4() {
		return cntReserve4;
	}
	public void setCntReserve4(String cntReserve4) {
		this.cntReserve4 = cntReserve4;
	}
	public String getCntReserve5() {
		return cntReserve5;
	}
	public void setCntReserve5(String cntReserve5) {
		this.cntReserve5 = cntReserve5;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
}

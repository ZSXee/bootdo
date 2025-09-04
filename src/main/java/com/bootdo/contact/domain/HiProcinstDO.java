package com.bootdo.contact.domain;

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
public class HiProcinstDO implements Serializable {
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
	//合同名称
	@Excel(name = "合同名称")
	private String name;
	@Excel(name = "发起机构")
	private String deptId;
	//
	private String userName;
	//合同类型
	@Excel(name = "合同类型")
	private String cntType;
	//机构号
	@Excel(name = "机构号")
	private String reserve1;
	//机构名称
	@Excel(name = "机构名称")
	private String deptName;
	//合同份数
	@Excel(name = "合同份数")
	private String cntReserve3;
	//任务节点
	@Excel(name = "任务节点")
	private String taskName;
	//标的
	@Excel(name = "合同标的")
	private String cntStandard;

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
	
	public String getCntStandard() {
		return cntStandard;
	}
	public void setCntStandard(String cntStandard) {
		this.cntStandard = cntStandard;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCntType() {
		return cntType;
	}
	public void setCntType(String cntType) {
		this.cntType = cntType;
	}
	public String getReserve1() {
		return reserve1;
	}
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
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
	public String getCntReserve3() {
		return cntReserve3;
	}
	public void setCntReserve3(String cntReserve3) {
		this.cntReserve3 = cntReserve3;
	}
	
}

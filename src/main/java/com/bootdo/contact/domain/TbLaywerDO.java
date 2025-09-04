package com.bootdo.contact.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 外聘律师信息表
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-07-31 14:15:24
 */
public class TbLaywerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//合同ID
	private String cntId;
	//Task_ID
	private String taskId;
	//taskPass
	private String taskPass;
	//流程ID
	private String procInsId;
	//合同编号
	private String cntCode;
	//合同名称
	private String cntName;
	//合同类型
	private String cntType;
	//律师姓名
	private String laywerName;
	//事务所名称
	private String laywerOffice;
	//住所地
	private String address;
	//律师人数
	private String amount;
	//金融业务律师人数
	private String moneyAmount;
	//有无处罚
	private String memo;
	//预留字段1
	private String cntReserve1;
	//预留字段2
	private String cntReserve2;
	//预留字段3
	private String cntReserve3;
	//预留字段4
	private String cntReserve4;
	//预留字段5
	private String cntReserve5;

	/**
	 * 设置：合同ID
	 */
	public void setCntId(String cntId) {
		this.cntId = cntId;
	}
	/**
	 * 获取：合同ID
	 */
	public String getCntId() {
		return cntId;
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
	 * 设置：合同编号
	 */
	public void setCntCode(String cntCode) {
		this.cntCode = cntCode;
	}
	/**
	 * 获取：合同编号
	 */
	public String getCntCode() {
		return cntCode;
	}
	/**
	 * 设置：合同名称
	 */
	public void setCntName(String cntName) {
		this.cntName = cntName;
	}
	/**
	 * 获取：合同名称
	 */
	public String getCntName() {
		return cntName;
	}
	/**
	 * 设置：合同类型
	 */
	public void setCntType(String cntType) {
		this.cntType = cntType;
	}
	/**
	 * 获取：合同类型
	 */
	public String getCntType() {
		return cntType;
	}
	/**
	 * 设置：律师姓名
	 */
	public void setLaywerName(String laywerName) {
		this.laywerName = laywerName;
	}
	/**
	 * 获取：律师姓名
	 */
	public String getLaywerName() {
		return laywerName;
	}
	/**
	 * 设置：事务所名称
	 */
	public void setLaywerOffice(String laywerOffice) {
		this.laywerOffice = laywerOffice;
	}
	/**
	 * 获取：事务所名称
	 */
	public String getLaywerOffice() {
		return laywerOffice;
	}
	/**
	 * 设置：住所地
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：住所地
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：律师人数
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	/**
	 * 获取：律师人数
	 */
	public String getAmount() {
		return amount;
	}
	/**
	 * 设置：金融业务律师人数
	 */
	public void setMoneyAmount(String moneyAmount) {
		this.moneyAmount = moneyAmount;
	}
	/**
	 * 获取：金融业务律师人数
	 */
	public String getMoneyAmount() {
		return moneyAmount;
	}
	/**
	 * 设置：有无处罚
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取：有无处罚
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * 设置：预留字段1
	 */
	public void setCntReserve1(String cntReserve1) {
		this.cntReserve1 = cntReserve1;
	}
	/**
	 * 获取：预留字段1
	 */
	public String getCntReserve1() {
		return cntReserve1;
	}
	/**
	 * 设置：预留字段2
	 */
	public void setCntReserve2(String cntReserve2) {
		this.cntReserve2 = cntReserve2;
	}
	/**
	 * 获取：预留字段2
	 */
	public String getCntReserve2() {
		return cntReserve2;
	}
	/**
	 * 设置：预留字段3
	 */
	public void setCntReserve3(String cntReserve3) {
		this.cntReserve3 = cntReserve3;
	}
	/**
	 * 获取：预留字段3
	 */
	public String getCntReserve3() {
		return cntReserve3;
	}
	/**
	 * 设置：预留字段4
	 */
	public void setCntReserve4(String cntReserve4) {
		this.cntReserve4 = cntReserve4;
	}
	/**
	 * 获取：预留字段4
	 */
	public String getCntReserve4() {
		return cntReserve4;
	}
	/**
	 * 设置：预留字段5
	 */
	public void setCntReserve5(String cntReserve5) {
		this.cntReserve5 = cntReserve5;
	}
	/**
	 * 获取：预留字段5
	 */
	public String getCntReserve5() {
		return cntReserve5;
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
}
	

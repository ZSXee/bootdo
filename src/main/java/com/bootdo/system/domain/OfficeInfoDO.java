package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;

import com.bootdo.common.utils.excel.Excel;



/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-06-10 14:23:57
 */
public class OfficeInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	@Excel(name = "聘用律所")
	private String officename;
	//
	@Excel(name = "律师信息")
	private String lawyer;
	//
	@Excel(name = "费用")
	private String money;
	//
	@Excel(name = "服务期限")
	private String date;
	//
	@Excel(name = "服务事项")
	private String service;
	//
	private String contract;
	//
	private int id;
	//
	private String cntId;
	//
	private String cntName;
	//
	private String cntType;
	//
	@Excel(name = "聘用机构")
	private String scr;
	//
	private String path;
	//
	private Date reserve2;
	//
	private String reserve1;

	/**
	 * 设置：
	 */
	public void setOfficename(String officename) {
		this.officename = officename;
	}
	/**
	 * 获取：
	 */
	public String getOfficename() {
		return officename;
	}
	/**
	 * 设置：
	 */
	public void setLawyer(String lawyer) {
		this.lawyer = lawyer;
	}
	/**
	 * 获取：
	 */
	public String getLawyer() {
		return lawyer;
	}
	/**
	 * 设置：
	 */
	public void setMoney(String money) {
		this.money = money;
	}
	/**
	 * 获取：
	 */
	public String getMoney() {
		return money;
	}
	/**
	 * 设置：
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 获取：
	 */
	public String getDate() {
		return date;
	}
	/**
	 * 设置：
	 */
	public void setService(String service) {
		this.service = service;
	}
	/**
	 * 获取：
	 */
	public String getService() {
		return service;
	}
	/**
	 * 设置：
	 */
	public void setContract(String contract) {
		this.contract = contract;
	}
	/**
	 * 获取：
	 */
	public String getContract() {
		return contract;
	}
	/**
	 * 设置：
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public int getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCntId(String cntId) {
		this.cntId = cntId;
	}
	/**
	 * 获取：
	 */
	public String getCntId() {
		return cntId;
	}
	/**
	 * 设置：
	 */
	public void setCntName(String cntName) {
		this.cntName = cntName;
	}
	/**
	 * 获取：
	 */
	public String getCntName() {
		return cntName;
	}
	/**
	 * 设置：
	 */
	public void setCntType(String cntType) {
		this.cntType = cntType;
	}
	/**
	 * 获取：
	 */
	public String getCntType() {
		return cntType;
	}
	/**
	 * 设置：
	 */
	public void setScr(String scr) {
		this.scr = scr;
	}
	/**
	 * 获取：
	 */
	public String getScr() {
		return scr;
	}
	/**
	 * 设置：
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取：
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 设置：
	 */
	public void setReserve2(Date reserve2) {
		this.reserve2 = reserve2;
	}
	/**
	 * 获取：
	 */
	public Date getReserve2() {
		return reserve2;
	}
	/**
	 * 设置：
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	/**
	 * 获取：
	 */
	public String getReserve1() {
		return reserve1;
	}
}

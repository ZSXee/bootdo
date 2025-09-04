package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;

import com.bootdo.common.utils.excel.Excel;



/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-06-24 09:58:53
 */
public class GreylistInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	@Excel(name = "编号")
	private Integer number;
	//
	@Excel(name = "地区")
	private String area;
	//
	@Excel(name = "律所名称")
	private String officename;
	//
	@Excel(name = "地址")
	private String address;
	
	@Excel(name = "电话")
	private String phone;

	/**
	 * 设置：
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * 获取：
	 */
	public Integer getNumber() {
		return number;
	}
	/**
	 * 设置：
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取：
	 */
	public String getArea() {
		return area;
	}
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
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

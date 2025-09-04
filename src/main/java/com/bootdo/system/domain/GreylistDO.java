package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-06-23 10:24:34
 */
public class GreylistDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer number;
	//
	private String area;
	//
	private String officename;
	//
	private String address;

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
}

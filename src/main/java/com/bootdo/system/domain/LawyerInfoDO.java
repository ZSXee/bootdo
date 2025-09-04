package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;

import com.bootdo.common.utils.excel.Excel;



/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-05-26 14:45:29
 */
public class LawyerInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	@Excel(name = "编号")
	private Integer number;
	//
	@Excel(name = "地区")
	private String name;
	//
	@Excel(name = "律所名称")
	private String sex;
	//
	private Integer age;
	//
	@Excel(name = "地址")
	private String phone;
	
	@Excel(name="电话")
	private String dianhua;
	
	@Excel(name="入库单位")
	private String dept;
	
	@Excel(name="入库时间")
	private String date;
	
	@Excel(name="备注1")
	private String reserve1;
	
	@Excel(name="备注2")
	private String reserve2;
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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * 获取：
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：
	 */
	public String getPhone() {
		return phone;
	}
	
	public String getDianhua() {
		return dianhua;
	}
	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}
	
}

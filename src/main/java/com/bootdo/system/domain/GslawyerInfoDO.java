package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;

import com.bootdo.common.utils.excel.Excel;



/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-06-17 15:01:04
 */
public class GslawyerInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	@Excel(name = "工作证编号")
	private String number;
	//
	@Excel(name = "姓名")
	private String name;
	//
	@Excel(name = "机构")
	private String sex;
	//
	@Excel(name = "工作岗位")
	private String age;
	//
	@Excel(name = "考核结果")
	private String phone;
	
	
	@Excel(name="电话")
	private String dianhua;

	/**
	 * 设置：
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * 获取：
	 */
	public String getNumber() {
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
	/**
	 * 获取：
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * 获取：
	 */
	public String getAge() {
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

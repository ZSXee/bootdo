package com.bootdo.system.domain;

import java.io.Serializable;



/**
 * 机构信息扩展表
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-29 15:17:22
 */
public class DeptExtendDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//机构ID
	private Long deptIdE;
	//机构类型 1：总行部室2：分行部室3：支行4：总行合规风险部5：分行合规风险部
	private Long deptType;
	//机构路径，数据查询权限
	private String deptPath;
	//预留字段1
	private String reserve1;
	//预留字段2
	private String reserve2;
	//预留字段3
	private String reserve3;
	//预留字段4
	private String reserve4;
	//预留字段5
	private String reserve5;

	/**
	 * 设置：机构ID
	 */
	public void setDeptIdE(Long deptIdE) {
		this.deptIdE = deptIdE;
	}
	/**
	 * 获取：机构ID
	 */
	public Long getDeptIdE() {
		return deptIdE;
	}
	/**
	 * 设置：机构类型
            1：总行部室2：分行部室3：支行4：总行合规风险部5：分行合规风险部
	 */
	public void setDeptType(Long deptType) {
		this.deptType = deptType;
	}
	/**
	 * 获取：机构类型
            1：总行部室2：分行部室3：支行4：总行合规风险部5：分行合规风险部
	 */
	public Long getDeptType() {
		return deptType;
	}
	/**
	 * 设置：机构路径，数据查询权限
	 */
	public void setDeptPath(String deptPath) {
		this.deptPath = deptPath;
	}
	/**
	 * 获取：机构路径，数据查询权限
	 */
	public String getDeptPath() {
		return deptPath;
	}
	/**
	 * 设置：预留字段1
	 */
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	/**
	 * 获取：预留字段1
	 */
	public String getReserve1() {
		return reserve1;
	}
	/**
	 * 设置：预留字段2
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	/**
	 * 获取：预留字段2
	 */
	public String getReserve2() {
		return reserve2;
	}
	/**
	 * 设置：预留字段3
	 */
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	/**
	 * 获取：预留字段3
	 */
	public String getReserve3() {
		return reserve3;
	}
	/**
	 * 设置：预留字段4
	 */
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}
	/**
	 * 获取：预留字段4
	 */
	public String getReserve4() {
		return reserve4;
	}
	/**
	 * 设置：预留字段5
	 */
	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}
	/**
	 * 获取：预留字段5
	 */
	public String getReserve5() {
		return reserve5;
	}
}

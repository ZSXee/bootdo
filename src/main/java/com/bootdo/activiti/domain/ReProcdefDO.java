package com.bootdo.activiti.domain;

import java.io.Serializable;



/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-10-09 08:04:54
 */
public class ReProcdefDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private Integer rev;
	//
	private String category;
	//
	private String name;
	//
	private String key;
	//
	private Integer version;
	//
	private String deploymentId;
	//
	private String resourceName;
	//
	private String dgrmResourceName;
	//
	private String description;
	//
	private Integer hasStartFormKey;
	//
	private Integer hasGraphicalNotation;
	//
	private Integer suspensionState;
	//
	private String tenantId;

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
	public void setRev(Integer rev) {
		this.rev = rev;
	}
	/**
	 * 获取：
	 */
	public Integer getRev() {
		return rev;
	}
	/**
	 * 设置：
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * 获取：
	 */
	public String getCategory() {
		return category;
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
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 获取：
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置：
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	/**
	 * 获取：
	 */
	public Integer getVersion() {
		return version;
	}
	/**
	 * 设置：
	 */
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	/**
	 * 获取：
	 */
	public String getDeploymentId() {
		return deploymentId;
	}
	/**
	 * 设置：
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	/**
	 * 获取：
	 */
	public String getResourceName() {
		return resourceName;
	}
	/**
	 * 设置：
	 */
	public void setDgrmResourceName(String dgrmResourceName) {
		this.dgrmResourceName = dgrmResourceName;
	}
	/**
	 * 获取：
	 */
	public String getDgrmResourceName() {
		return dgrmResourceName;
	}
	/**
	 * 设置：
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：
	 */
	public void setHasStartFormKey(Integer hasStartFormKey) {
		this.hasStartFormKey = hasStartFormKey;
	}
	/**
	 * 获取：
	 */
	public Integer getHasStartFormKey() {
		return hasStartFormKey;
	}
	/**
	 * 设置：
	 */
	public void setHasGraphicalNotation(Integer hasGraphicalNotation) {
		this.hasGraphicalNotation = hasGraphicalNotation;
	}
	/**
	 * 获取：
	 */
	public Integer getHasGraphicalNotation() {
		return hasGraphicalNotation;
	}
	/**
	 * 设置：
	 */
	public void setSuspensionState(Integer suspensionState) {
		this.suspensionState = suspensionState;
	}
	/**
	 * 获取：
	 */
	public Integer getSuspensionState() {
		return suspensionState;
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
}

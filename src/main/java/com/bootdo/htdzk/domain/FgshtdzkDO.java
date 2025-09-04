package com.bootdo.htdzk.domain;

import java.io.Serializable;



/**
 * 典型案例
 * 
 * @author zcf
 * @email zcf@163.com
 * @date 2018-08-30 09:38:20
 */
public class FgshtdzkDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private String id;
	//路径
	private String path;
	//文件名称
	private String name;
	//文件类型
	private String type;
	//上传人员
	private String uploader;
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
	//合同编号
	private String htbh;
	//合同名称
	private String htmc;
	//当事人名称
	private String dsrmc;
	//合同标的额
	private Double htbde;
	//履行期限
	private String lxqx;
	//违约情况
	private String wyqk;
	//争议情况
	private String zyqk;

	/**
	 * 设置：ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：路径
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取：路径
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 设置：文件名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：文件名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：文件类型
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：文件类型
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：上传人员
	 */
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	/**
	 * 获取：上传人员
	 */
	public String getUploader() {
		return uploader;
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
	/**
	 * 设置：合同编号
	 */
	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}
	/**
	 * 获取：合同编号
	 */
	public String getHtbh() {
		return htbh;
	}
 /**
	 * 设置：合同名称
	 */
	public void setHtmc(String htmc) {
		this.htmc = htmc;
	}
	/**
	 * 获取：合同名称
	 */
	public String getHtmc() {
		return htmc;
	}
/**
	 * 设置：当事人名称
	 */
	public void setDsrmc(String dsrmc) {
		this.dsrmc = dsrmc;
	}
	/**
	 * 获取：当事人名称
	 */
	public String getDsrmc() {
		return dsrmc;
	}
	/**
	 * 设置：合同标的额
	 */
	public void setHtbde(Double htbde) {
		this.htbde = htbde;
	}
	/**
	 * 获取：合同标的额
	 */
	public Double getHtbde() {
		return htbde;
	}
	/**
	 * 设置：履行期限
	 */
	public void setLxqx(String lxqx) {
		this.lxqx = lxqx;
	}
	/**
	 * 获取：履行期限
	 */
	public String getLxqx() {
		return lxqx;
	}
	/**
	 * 设置：违约情况
	 */
	public void setWyqk(String wyqk) {
		this.wyqk = wyqk;
	}
	/**
	 * 获取：违约情况
	 */
	public String getWyqk() {
		return wyqk;
	}
	/**
	 * 设置：争议情况
	 */
	public void setZyqk(String zyqk) {
		this.zyqk = zyqk;
	}
	/**
	 * 获取：争议情况
	 */
	public String getZyqk() {
		return zyqk;
	}
}

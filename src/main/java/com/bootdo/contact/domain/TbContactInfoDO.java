package com.bootdo.contact.domain;

import java.io.Serializable;



/**
 * 合同信息表
 *
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-12 11:03:48
 */
public class TbContactInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//合同ID
	private String cntId;
	//Task_ID
	private String taskId;
	//taskPass
	private String taskPass;
	//流程ID
	private String procInsId;
	//批注
	private String taskComment;
	//合同编号
	private String cntCode;
	//合同名称
	private String cntName;
	//合同类型
	private String cntType;
	//合同价款/报酬
	private String cntPrice;
	//合同数量
	private String cntAmount;
	//合同质量
	private String cntQuality;
	//合同管理员
	private String cntAdmin;
	//签订时间
	private String cntSignTime;
	//期限
	private String cntTerm;
	//履行地点
	private String cntPerfomPlace;
	//履行方式
	private String cntPerfomMode;
	//合同标的
	private String cntStandard;
	//违约责任
	private String cntBreach;
	//解决争议办法
	private String cntMethod;
	//预留字段1-会签部门被占用
	private String cntReserve1;
	//预留字段2-是否会签标志
	private String cntReserve2;
	//预留字段3-合同份数
	private String cntReserve3;
	//预留字段4
	private String cntReserve4;
	//预留字段5
	private String cntReserve5;
	//申请人
	private String sqr;
	//性别
	private String sex;
	//身份证
	private String sfz;
	//单位
	private String dw;
	//职务
	private String zw;
	//岗位
	private String gw;
	//现岗位起始时间
	private String sdate;
	//取得法律资格证时间
	private String qdate;
	//资格证编号
	private String bh;
	//承诺
	private String cn;
	//申请机构
	private String site;
	//地址
	private String add;
	//电话
	private String phone;
	//人数
	private String sl;
	//金融人数
	private String jrsl;
	//流程主键
	private String key;

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
	 * 设置：合同价款/报酬
	 */
	public void setCntPrice(String cntPrice) {
		this.cntPrice = cntPrice;
	}
	/**
	 * 获取：合同价款/报酬
	 */
	public String getCntPrice() {
		return cntPrice;
	}
	/**
	 * 设置：合同数量
	 */
	public void setCntAmount(String cntAmount) {
		this.cntAmount = cntAmount;
	}
	/**
	 * 获取：合同数量
	 */
	public String getCntAmount() {
		return cntAmount;
	}
	/**
	 * 设置：合同质量
	 */
	public void setCntQuality(String cntQuality) {
		this.cntQuality = cntQuality;
	}
	/**
	 * 获取：合同质量
	 */
	public String getCntQuality() {
		return cntQuality;
	}
	/**
	 * 设置：合同管理员
	 */
	public void setCntAdmin(String cntAdmin) {
		this.cntAdmin = cntAdmin;
	}
	/**
	 * 获取：合同管理员
	 */
	public String getCntAdmin() {
		return cntAdmin;
	}
	/**
	 * 设置：签订时间
	 */
	public void setCntSignTime(String cntSignTime) {
		this.cntSignTime = cntSignTime;
	}
	/**
	 * 获取：签订时间
	 */
	public String getCntSignTime() {
		return cntSignTime;
	}
	/**
	 * 设置：期限
	 */
	public void setCntTerm(String cntTerm) {
		this.cntTerm = cntTerm;
	}
	/**
	 * 获取：期限
	 */
	public String getCntTerm() {
		return cntTerm;
	}
	/**
	 * 设置：履行地点
	 */
	public void setCntPerfomPlace(String cntPerfomPlace) {
		this.cntPerfomPlace = cntPerfomPlace;
	}
	/**
	 * 获取：履行地点
	 */
	public String getCntPerfomPlace() {
		return cntPerfomPlace;
	}
	/**
	 * 设置：履行方式
	 */
	public void setCntPerfomMode(String cntPerfomMode) {
		this.cntPerfomMode = cntPerfomMode;
	}
	/**
	 * 获取：履行方式
	 */
	public String getCntPerfomMode() {
		return cntPerfomMode;
	}
	/**
	 * 设置：合同标的
	 */
	public void setCntStandard(String cntStandard) {
		this.cntStandard = cntStandard;
	}
	/**
	 * 获取：合同标的
	 */
	public String getCntStandard() {
		return cntStandard;
	}
	/**
	 * 设置：违约责任
	 */
	public void setCntBreach(String cntBreach) {
		this.cntBreach = cntBreach;
	}
	/**
	 * 获取：违约责任
	 */
	public String getCntBreach() {
		return cntBreach;
	}
	/**
	 * 设置：解决争议办法
	 */
	public void setCntMethod(String cntMethod) {
		this.cntMethod = cntMethod;
	}
	/**
	 * 获取：解决争议办法
	 */
	public String getCntMethod() {
		return cntMethod;
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
	 * 获取：预留字段5
	 */

	/**
	 * 设置：申请人
	 */
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	/**
	 * 获取：申请人
	 */
	public String getSqr() {
		return sqr;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：身份证
	 */
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	/**
	 * 获取：身份证
	 */
	public String getSfz() {
		return sfz;
	}
	/**
	 * 设置：单位
	 */
	public void setDw(String dw) {
		this.dw = dw;
	}
	/**
	 * 获取：单位
	 */
	public String getDw() {
		return dw;
	}
	/**
	 * 设置：职务
	 */
	public void setZw(String zw) {
		this.zw = zw;
	}
	/**
	 * 获取：职务
	 */
	public String getZw() {
		return zw;
	}
	/**
	 * 设置：岗位
	 */
	public void setGw(String gw) {
		this.gw = gw;
	}
	/**
	 * 获取：岗位
	 */
	public String getGw() {
		return gw;
	}
	/**
	 * 设置：现岗位起始时间
	 */
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	/**
	 * 获取：现岗位起始时间
	 */
	public String getSdate() {
		return sdate;
	}
	/**
	 * 设置：取得法律资格证时间
	 */
	public void setQdate(String qdate) {
		this.qdate = qdate;
	}
	/**
	 * 获取：取得法律资格证时间
	 */
	public String getQdate() {
		return qdate;
	}
	/**
	 * 设置：资格证编号
	 */
	public void setBh(String bh) {
		this.bh = bh;
	}
	/**
	 * 获取：资格证编号
	 */
	public String getBh() {
		return bh;
	}
	/**
	 * 设置：承诺
	 */
	public void setCn(String cn) {
		this.cn = cn;
	}
	/**
	 * 获取：承诺
	 */
	public String getCn() {
		return cn;
	}
	/**
	 * 设置：申请机构
	 */
	public void setSite(String site) {
		this.site = site;
	}
	/**
	 * 获取：申请机构
	 */
	public String getSite() {
		return site;
	}
	/**
	 * 设置：地址
	 */
	public void setAdd(String add) {
		this.add = add;
	}
	/**
	 * 获取：地址
	 */
	public String getAdd() {
		return add;
	}

	/**
	 * 设置电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置：人数
	 */
	public void setSl(String sl) {
		this.sl = sl;
	}
	/**
	 * 获取：人数
	 */
	public String getSl() {
		return sl;
	}
	/**
	 * 设置：金融人数
	 */
	public void setJrsl(String jrsl) {
		this.jrsl = jrsl;
	}
	/**
	 * 获取：金融人数
	 */
	public String getJrsl() {
		return jrsl;
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
	/**
	 * 获取：批注
	 */
	public String getTaskComment() {
		return taskComment;
	}
	/**
	 * 设置：批注
	 */
	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}

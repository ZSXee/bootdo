package com.bootdo.contact.domain;

import java.io.Serializable;



/**
 * 批注信息
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2018-08-12 11:03:48
 */
public class CommentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//本合同是否已阅知？
	private String yuedu;
	//本合同所涉及业务是否合规?
	private String hegui;
	//对本合同涉及业务条款是否有异议？
	private String yiyi;
	//是否同意提交法律审核？
	private String shenhe;
	//本合同所涉及业务的履行情况？
	private String lvxing;	
	//本合同所涉及业务是否正在履行过程中或已履行完毕（如是，应附书面履行情况说明）
	private String yewu;
	//批注意见
	private String taskComment;
	
	public String getYuedu() {
		return yuedu;
	}
	public void setYuedu(String yuedu) {
		this.yuedu = yuedu;
	}
	public String getHegui() {
		return hegui;
	}
	public void setHegui(String hegui) {
		this.hegui = hegui;
	}
	public String getYiyi() {
		return yiyi;
	}
	public void setYiyi(String yiyi) {
		this.yiyi = yiyi;
	}
	public String getShenhe() {
		return shenhe;
	}
	public void setShenhe(String shenhe) {
		this.shenhe = shenhe;
	}
	public String getLvxing() {
		return lvxing;
	}
	public void setLvxing(String lvxing) {
		this.lvxing = lvxing;
	}
	public String getYewu() {
		return yewu;
	}
	public void setYewu(String yewu) {
		this.yewu = yewu;
	}
	public String getTaskComment() {
		return taskComment;
	}
	public void setTaskComment(String taskComment) {
		this.taskComment = taskComment;
	}
}

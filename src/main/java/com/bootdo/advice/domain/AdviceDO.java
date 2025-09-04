package com.bootdo.advice.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author dongst
 * @email lsyhdst@163.com
 * @date 2020-11-16 13:58:58
 */
public class AdviceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String userid;
	//
	private String username;
	//
	private String content;
	//
	private String response;
	//
	private String responder;
	//
	private String reserve1;
	//
	private String reserve2;
	//
	private String reserve3;
	//
	private Date starttime;
	//
	private Date endtime;

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
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * 获取：
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * 设置：
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	/**
	 * 获取：
	 */
	public String getResponse() {
		return response;
	}
	/**
	 * 设置：
	 */
	public void setResponder(String responder) {
		this.responder = responder;
	}
	/**
	 * 获取：
	 */
	public String getResponder() {
		return responder;
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
	/**
	 * 设置：
	 */
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
	/**
	 * 获取：
	 */
	public String getReserve2() {
		return reserve2;
	}
	/**
	 * 设置：
	 */
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	/**
	 * 获取：
	 */
	public String getReserve3() {
		return reserve3;
	}
	/**
	 * 设置：
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	/**
	 * 获取：
	 */
	public Date getStarttime() {
		return starttime;
	}
	/**
	 * 设置：
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	/**
	 * 获取：
	 */
	public Date getEndtime() {
		return endtime;
	}
}

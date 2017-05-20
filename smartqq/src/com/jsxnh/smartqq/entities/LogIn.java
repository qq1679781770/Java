package com.jsxnh.smartqq.entities;

import java.util.Date;

public class LogIn {

	private Integer id;
	private Integer user_id;
	private String ip;
	private Date login_time;
	private Date logout_time;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public Date getLogout_time() {
		return logout_time;
	}
	public void setLogout_time(Date logout_time) {
		this.logout_time = logout_time;
	}
}

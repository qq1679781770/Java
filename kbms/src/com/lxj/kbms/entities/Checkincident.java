package com.lxj.kbms.entities;

import java.util.Date;

public class Checkincident {

	private Integer id;
	private String incidentname;
	private String province;
	private String city;
	private java.sql.Date begin_time;
	private java.sql.Date end_time;
	private String influence;
	private String content;
	private Integer commituser;
	private Date committime;
	private Integer ischecked;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIncidentname() {
		return incidentname;
	}
	public void setIncidentname(String incidentname) {
		this.incidentname = incidentname;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public java.sql.Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(java.sql.Date begin_time) {
		this.begin_time = begin_time;
	}
	public java.sql.Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(java.sql.Date end_time) {
		this.end_time = end_time;
	}
	public String getInfluence() {
		return influence;
	}
	public void setInfluence(String influence) {
		this.influence = influence;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCommituser() {
		return commituser;
	}
	public void setCommituser(Integer commituser) {
		this.commituser = commituser;
	}
	public Date getCommittime() {
		return committime;
	}
	public void setCommittime(Date committime) {
		this.committime = committime;
	}
	public Integer getIschecked() {
		return ischecked;
	}
	public void setIschecked(Integer ischecked) {
		this.ischecked = ischecked;
	}
	
}

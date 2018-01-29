package com.lxj.kbms.entities;

import java.sql.Date;

public class Incident {

	private Integer id;
	private String province;
	private String city;
	private Date begin_time;
	private Date end_time;
	private String influence;
	private String  grade;
	private String ketword;
	private String content;
	private String incidentname;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getInfluence() {
		return influence;
	}
	public void setInfluence(String influence) {
		this.influence = influence;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getKetword() {
		return ketword;
	}
	public void setKetword(String ketword) {
		this.ketword = ketword;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIncidentname() {
		return incidentname;
	}
	public void setIncidentname(String incidentname) {
		this.incidentname = incidentname;
	}

	
}

package com.lxj.kbms.entities;

import java.util.Date;

public class Checkresult {

	private Integer incidentid;
	private String result;
	private Date time;
	private Integer operator;
	public Integer getIncidentid() {
		return incidentid;
	}
	public void setIncidentid(Integer incidentid) {
		this.incidentid = incidentid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	
	
}

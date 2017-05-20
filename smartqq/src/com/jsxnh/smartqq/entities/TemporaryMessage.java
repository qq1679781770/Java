package com.jsxnh.smartqq.entities;

import java.util.Date;

public class TemporaryMessage {

	private Integer id;
	private Integer send_id;
	private Date send_time;
	private Integer receive;
	private String content;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSend_id() {
		return send_id;
	}
	public void setSend_id(Integer send_id) {
		this.send_id = send_id;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	public Integer getReceive() {
		return receive;
	}
	public void setReceive(Integer receive) {
		this.receive = receive;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}

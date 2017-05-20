package com.jsxnh.smartqq.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class User {

	private Integer id;
	private Integer user_id;
	private String password;
	private String nick_name;
	private Date register_time;
	private String name;
	private Integer age;
	private String message;
	private String signature;
	private Integer status;
	private Set<Packet> packets=new HashSet<Packet>();
	public Set<Packet> getPackets() {
		return packets;
	}
	public void setPackets(Set<Packet> packets) {
		this.packets = packets;
	}
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
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public Date getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", user_id=" + user_id + ", password=" + password + ", nick_name=" + nick_name
				+ ", register_time=" + register_time + ", name=" + name + ", age=" + age + ", message=" + message
				+ ", signature=" + signature + ", status=" + status + ", packets=" + packets + "]";
	}
}

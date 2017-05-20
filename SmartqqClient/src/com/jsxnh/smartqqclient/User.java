package com.jsxnh.smartqqclient;

import java.util.LinkedList;

public class User {

	private Integer user_id;
	private String signature;
	private String nickname;
	private String name;
	private Integer age;
	private String message;
	private Integer status;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	private LinkedList<String> packets=new LinkedList<>();
	
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public LinkedList<String> getPackets() {
		return packets;
	}
	public void setPackets(LinkedList<String> packets) {
		this.packets = packets;
	}
	
	
}

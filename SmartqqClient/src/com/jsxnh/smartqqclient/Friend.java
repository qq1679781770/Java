package com.jsxnh.smartqqclient;

public class Friend {

	private Integer user_id;
	private String nickname;
	private String remarkname;
	private String signature;
	private Integer status;
	private String packet;
	private boolean isnickname=true;
	
	public boolean isIsnickname() {
		return isnickname;
	}
	public void setIsnickname(boolean isnickname) {
		this.isnickname = isnickname;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRemarkname() {
		return remarkname;
	}
	public void setRemarkname(String remarkname) {
		this.remarkname = remarkname;
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
	public String getPacket() {
		return packet;
	}
	public void setPacket(String packet) {
		this.packet = packet;
	}
	@Override
	public String toString() {
		if(!isnickname){
		   return remarkname;
		}
		return nickname;
	}
	
}

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
		if(!isnickname&&remarkname!=null&&!remarkname.equals("")){
		   return remarkname;
		}
		return nickname;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Friend other = (Friend) obj;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	
}

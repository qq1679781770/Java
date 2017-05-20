package com.jsxnh.smartqq.entities;

public class Friend {

	private Integer id;
	private Integer user1_id;
	private Integer user2_id;
	private Packet packet1;
	private Packet packet2;
	private String remark1_name;
	private String remark2_name;
	
	@Override
	public String toString() {
		return "Friend [id=" + id + ", user1_id=" + user1_id + ", user2_id=" + user2_id + ", packet1=" + packet1
				+ ", packet2=" + packet2 + ", remark1_name=" + remark1_name + ", remark2_name=" + remark2_name + "]";
	}
	public String getRemark1_name() {
		return remark1_name;
	}
	public void setRemark1_name(String remark1_name) {
		this.remark1_name = remark1_name;
	}
	public String getRemark2_name() {
		return remark2_name;
	}
	public void setRemark2_name(String remark2_name) {
		this.remark2_name = remark2_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser1_id() {
		return user1_id;
	}
	public void setUser1_id(Integer user1_id) {
		this.user1_id = user1_id;
	}
	public Integer getUser2_id() {
		return user2_id;
	}
	public void setUser2_id(Integer user2_id) {
		this.user2_id = user2_id;
	}
	public Packet getPacket1() {
		return packet1;
	}
	public void setPacket1(Packet packet1) {
		this.packet1 = packet1;
	}
	public Packet getPacket2() {
		return packet2;
	}
	public void setPacket2(Packet packet2) {
		this.packet2 = packet2;
	}
	public Friend(){}
}

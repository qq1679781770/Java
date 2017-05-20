package com.jsxnh.smartqq.entities;

public class TemporaryFriend {

	private Integer id;
	private Integer user1_id;
	private Integer user2_id;
	private String packet_name;
	
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
	public String getPacket_name() {
		return packet_name;
	}
	public void setPacket_name(String packet_name) {
		this.packet_name = packet_name;
	}
}

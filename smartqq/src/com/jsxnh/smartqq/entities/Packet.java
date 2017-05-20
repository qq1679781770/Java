package com.jsxnh.smartqq.entities;

public class Packet {

	private Integer id;
	private User user;
	private String packet_name;
	@Override
	public String toString() {
		return "Packet [id=" + id + ", user=" + user + ", packet_name=" + packet_name + "]";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPacket_name() {
		return packet_name;
	}
	public void setPacket_name(String packet_name) {
		this.packet_name = packet_name;
	}
}

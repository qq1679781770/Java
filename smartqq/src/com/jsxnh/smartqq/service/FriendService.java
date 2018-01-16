package com.jsxnh.smartqq.service;

import java.util.List;

import com.jsxnh.smartqq.entities.Friend;
import com.jsxnh.smartqq.entities.TemporaryFriend;


public interface FriendService {

	//添加好友
	public void addFriend(Integer user1_id,Integer user2_id,String packet1_name,String packet2_name);
	//删除好友
	public void delete(Integer user1_id,Integer user2_id);
	//移动好友分组
	public void movePacket(Integer user1_id,Integer user2_id,String packet1_name,String packet2_name);
	//查找用户好友
	public List<Friend> findFriends(Integer user_id);
	//查找某个分组的好友
	public List<Friend> findPacketFriends(Integer user_id,String packet_name);
	//修改备注名
	public void modifyRemarkName(Integer user1_id,Integer user2_id,String remarkname);
	//等待添加好友
	public void addTemporaryFriend(Integer user1_id,Integer user2_id,String packet_name);
	//拒绝添加好友
	public void deleteTemporaryFriend(Integer user1_id,Integer user2_id);
	//查找好友添加
	public List<TemporaryFriend> findTemporaryFriends(Integer user_id); 
	//查找用户待添加好友
	public List<TemporaryFriend>  findTemporaryFriend(Integer user1_id,Integer user2_id);
	//查找用户是否已经成为朋友
	public Friend findFriend(Integer user1_id,Integer user2_id);
}

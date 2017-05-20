package com.jsxnh.smartqq.dao;

import java.util.List;
import com.jsxnh.smartqq.entities.Friend;
import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.User;

public interface FriendDao {

	//寻找用户某个分组的朋友
	public List<Friend> findFriends(User user,Packet packet);
	//添加朋友
	public void addFriend(User user1,User user2,Packet packet1,Packet packet2);
	//删除朋友
	public void deleteFriend(User user1,User user2);
	//移动分组
	public void movePacket(User user,Packet old_packet, Packet packet);
	//寻找好友
	public Friend findFriend(User user1,User user2);
	//寻找用户好友
	public List<Friend> findAllFriends(User user);
	//修改备注
	public void modifyRemarkName(User user1,User user2,String remarkname);
}

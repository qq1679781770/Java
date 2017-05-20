package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.TemporaryFriend;

public interface TemporaryFriendDao {

	public void addTemporaryFriend(TemporaryFriend temporaryFriend);
	
	public void deleteTemporaryFriend(Integer user1_id,Integer user2_id);
	
	public List<TemporaryFriend> findTemporaryFriends(Integer user_id);
	
	public List<TemporaryFriend> findTemporaryFriend(Integer user1_id,Integer user2_id);
}

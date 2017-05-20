package com.jsxnh.smartqq.service;

import java.util.List;

import com.jsxnh.smartqq.entities.Friend;
import com.jsxnh.smartqq.entities.TemporaryFriend;


public interface FriendService {

	//��Ӻ���
	public void addFriend(Integer user1_id,Integer user2_id,String packet1_name,String packet2_name);
	//ɾ������
	public void delete(Integer user1_id,Integer user2_id);
	//�ƶ����ѷ���
	public void movePacket(Integer user1_id,Integer user2_id,String packet1_name,String packet2_name);
	//�����û�����
	public List<Friend> findFriends(Integer user_id);
	//����ĳ������ĺ���
	public List<Friend> findPacketFriends(Integer user_id,String packet_name);
	//�޸ı�ע��
	public void modifyRemarkName(Integer user1_id,Integer user2_id,String remarkname);
	//�ȴ���Ӻ���
	public void addTemporaryFriend(Integer user1_id,Integer user2_id,String packet_name);
	//�ܾ���Ӻ���
	public void deleteTemporaryFriend(Integer user1_id,Integer user2_id);
	//���Һ������
	public List<TemporaryFriend> findTemporaryFriends(Integer user_id); 
	//
	public List<TemporaryFriend>  findTemporaryFriend(Integer user1_id,Integer user2_id);
}

package com.jsxnh.smartqq.service;

import java.util.HashMap;
import java.util.List;

import com.jsxnh.smartqq.entities.User;

public interface UserService {

	//�û�ע��
	public void userRegister(User user,String problem,String answer);
	//��ȷ�����û�
	public User findUserById(Integer user_id);
	//ģ�������û�
	public List<User> findUsersByNickName(String nickname);
	//�����û�״̬
	public int updateUserStatus(Integer user_id);
	//�����û���Ϣ
	public int updateData(Integer user_id,HashMap<String,String> datas);
	//���¸���ǩ��
	public int updateSignature(Integer user_id,String signature);
	//�޸�����
	public int modifyPassword(Integer user_id,String password);
	//�޸��ǳ�
	public int updateNickName(Integer user_id,String nickname);
	//���ӷ���
	public int addPacket(Integer user_id,String packet_name);
	//�޸ķ�������
	public int modifyPacket(Integer user_id,String old_packetname,String new_packetname);
	//ɾ������
	public int deletePacket(Integer user_id,String packet_name);
	//ȷ������
	public boolean checkPassword(Integer user_id,String password);
	//ȷ���ܱ�����
	public boolean checkAnswer(Integer user_id,String answer);
	//�����ܱ�����
	public String  findProblem(Integer user_id);
	//��¼
	public boolean LogIn(Integer user_id,String password,String ip);
	//�ǳ�
	public void LogOut(Integer user_id);
}

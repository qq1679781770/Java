package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.User;

public interface UserDao {

	// �����û��������û�
	public User findbyid(Integer user_id);
	// �����ǳ�ģ����ѯ�û�
	public List<User> findbyname(String nickname);
	// �û�ע��
	public void register(User user);
	// �����û�״̬
	public int updatestatus(User user);
	//�����û���Ϣ
	public int updateNickName(User user);
	//���¸���ǩ��
	public int updateSignature(User user);
	//�޸�����
	public int updatePassword(User user);
	//��֤����
	public boolean checkPassword(User user);
	//�޸���Ϣ
	public int updateData(User user);
	//��ӷ���
	public int addPacket(User user,Packet packet);
	//ɾ������
	public int deletePacket(User user,Packet packet);
	
}

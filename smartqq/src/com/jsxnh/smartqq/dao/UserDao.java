package com.jsxnh.smartqq.dao;

import java.util.List;

import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.User;

public interface UserDao {

	// 根据用户名查找用户
	public User findbyid(Integer user_id);
	// 根据昵称模糊查询用户
	public List<User> findbyname(String nickname);
	// 用户注册
	public void register(User user);
	// 更新用户状态
	public int updatestatus(User user);
	//更新用户信息
	public int updateNickName(User user);
	//更新个性签名
	public int updateSignature(User user);
	//修改密码
	public int updatePassword(User user);
	//验证密码
	public boolean checkPassword(User user);
	//修改信息
	public int updateData(User user);
	//添加分组
	public int addPacket(User user,Packet packet);
	//删除分组
	public int deletePacket(User user,Packet packet);
	
}

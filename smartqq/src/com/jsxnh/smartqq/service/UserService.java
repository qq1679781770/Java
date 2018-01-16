package com.jsxnh.smartqq.service;

import java.util.HashMap;
import java.util.List;

import com.jsxnh.smartqq.entities.User;

public interface UserService {

	//用户注册
	public void userRegister(User user,String problem,String answer);
	//精确查找用户
	public User findUserById(Integer user_id);
	//模糊查找用户
	public List<User> findUsersByNickName(String nickname);
	//更新用户状态
	public int updateUserStatus(Integer user_id);
	//更新用户信息
	public int updateData(Integer user_id,HashMap<String,String> datas);
	//更新个性签名
	public int updateSignature(Integer user_id,String signature);
	//修改密码
	public int modifyPassword(Integer user_id,String password);
	//修改昵称
	public int updateNickName(Integer user_id,String nickname);
	//增加分组
	public int addPacket(Integer user_id,String packet_name);
	//修改分组名字
	public int modifyPacket(Integer user_id,String old_packetname,String new_packetname);
	//删除分组
	public int deletePacket(Integer user_id,String packet_name);
	//确认密码
	public boolean checkPassword(Integer user_id,String password);
	//确认密保问题
	public boolean checkAnswer(Integer user_id,String answer);
	//查找密保问题
	public String  findProblem(Integer user_id);
	//登录
	public boolean LogIn(Integer user_id,String password,String ip);
	//登出
	public void LogOut(Integer user_id);
}

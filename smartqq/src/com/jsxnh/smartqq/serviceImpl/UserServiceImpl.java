package com.jsxnh.smartqq.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsxnh.smartqq.dao.LogInDao;
import com.jsxnh.smartqq.dao.PacketDao;
import com.jsxnh.smartqq.dao.PasswordAnswerDao;
import com.jsxnh.smartqq.dao.SignatureDao;
import com.jsxnh.smartqq.dao.TemporaryLogInDao;
import com.jsxnh.smartqq.dao.UserDao;
import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.PasswordAnswer;
import com.jsxnh.smartqq.entities.Signature;
import com.jsxnh.smartqq.entities.TemporaryLogIn;
import com.jsxnh.smartqq.entities.User;
import com.jsxnh.smartqq.entities.LogIn;
import com.jsxnh.smartqq.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	SignatureDao signatureDao;
	
	@Autowired
	PacketDao packetDao;
	
	@Autowired
	PasswordAnswerDao passwordAnswerDao;
	
	@Autowired 
	TemporaryLogInDao temporaryLogInDao;
	
	@Autowired
	LogInDao logInDao;
	
	@Override
	public void userRegister(User user,String problem,String answer) {
		PasswordAnswer passwordAnswer=new PasswordAnswer();
		passwordAnswer.setUser_id(user.getUser_id());
		passwordAnswer.setProblem(problem);
		passwordAnswer.setAnswer(answer);
		Packet packet=new Packet();
		packet.setPacket_name("默认分组");
		passwordAnswerDao.addPasswordAnswer(passwordAnswer);
		userDao.register(user);
		userDao.addPacket(user, packet);
	}

	@Override
	public User findUserById(Integer user_id) {
		
		return userDao.findbyid(user_id);
	}

	@Override
	public int updateUserStatus(Integer user_id) {
		User user=new User();
		user.setUser_id(user_id);
		userDao.updatestatus(user);
		return 1;
	}


	@Override
	public int updateData(Integer user_id,HashMap<String,String> datas) {
		User user=new User();
		user.setUser_id(user_id);
		if(datas.containsKey("name")){
			user.setName(datas.get("name"));
		}
		if(datas.containsKey("age")){
			user.setAge(Integer.parseInt(datas.get("age")));
		}
		if(datas.containsKey("message")){
			user.setMessage(datas.get("message"));
		}
		userDao.updateData(user);
		return 1;
	}

	@Override
	public int updateSignature(Integer user_id, String signature_content) {
		Signature signature=new Signature();
		signature.setContent(signature_content);
		signature.setUser_id(user_id);
		signature.setTime(new Date());
		User user=userDao.findbyid(user_id);
		user.setSignature(signature_content);
		userDao.updateSignature(user);
		signatureDao.addSigatureDao(signature);
		return 1;
	}

	@Override
	public int modifyPassword(Integer user_id, String password) {
		User user=new User();
		user.setUser_id(user_id);
		user.setPassword(password);
		userDao.updatePassword(user);
		return 1;
	}

	@Override
	public int updateNickName(Integer user_id, String nickname) {
		User user=new User();
		user.setUser_id(user_id);
		user.setNick_name(nickname);
		userDao.updateNickName(user);
		return 1;
	}

	@Override
	public List<User> findUsersByNickName(String nickname) {
		return userDao.findbyname(nickname);
	}

	@Override
	public int addPacket(Integer user_id, String packet_name) {
		User user=userDao.findbyid(user_id);
		Packet packet=new Packet();
		packet.setPacket_name(packet_name);
		return userDao.addPacket(user, packet);
	}

	@Override
	public int deletePacket(Integer user_id, String packet_name) {
		User user=userDao.findbyid(user_id);
		Packet packet=null;
		for(Packet packet_:user.getPackets()){
			if(packet_.getPacket_name().equals(packet_name))
				packet=packet_;
		}
		if(packet!=null){
			 if(packetDao.findFriendsbyPacket(packet).size()==0)
			     userDao.deletePacket(user, packet);
		}
		return 0;
	}

	@Override
	public boolean checkPassword(Integer user_id, String password) {
		User user=new User();
		user.setUser_id(user_id);
		user.setPassword(password);
		return userDao.checkPassword(user);
	}

	@Override
	public boolean checkAnswer(Integer user_id, String answer) {
		PasswordAnswer passwordanswer=new PasswordAnswer();
		passwordanswer.setUser_id(user_id);
		passwordanswer.setAnswer(answer);
		return passwordAnswerDao.checkProblem(passwordanswer);
	}

	@Override
	public String findProblem(Integer user_id) {
		return passwordAnswerDao.findPasswordAnswer(user_id).getProblem();
	}

	@Override
	public boolean LogIn(Integer user_id, String password,String ip) {
		if(checkPassword(user_id, password)==false)
			return false;
		TemporaryLogIn temporaryLogIn=new TemporaryLogIn();
		temporaryLogIn.setUser_id(user_id);
		temporaryLogIn.setIp(ip);
		LogIn logIn=new LogIn();
		logIn.setUser_id(user_id);
		logIn.setIp(ip);
		logIn.setLogin_time(new Date());
		logInDao.addLogIn(logIn);
		temporaryLogInDao.addTemporaryLogIn(temporaryLogIn);
		User user=new User();
		user.setUser_id(user_id);
		userDao.updatestatus(user);
		return true;
	}

	@Override
	public void LogOut(Integer user_id) {
		temporaryLogInDao.deleteTemporaryLogIn(user_id);
		logInDao.addLogout(user_id);
		updateUserStatus(user_id);
	}

	@Override
	public int modifyPacket(Integer user_id, String old_packetname, String new_packetname) {
		User user=userDao.findbyid(user_id);
		List<Packet> packets=packetDao.findpacketById(user);
		Packet packet_=null;
		for(Packet packet:packets){
			if(packet.getPacket_name().equals(old_packetname))
				packet_=packet;
		}
		packetDao.modifyPacket(packet_, new_packetname);
		return 1;
	}
}

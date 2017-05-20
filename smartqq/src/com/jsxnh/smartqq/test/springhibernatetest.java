package com.jsxnh.smartqq.test;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jsxnh.smartqq.entities.Friend;
import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.User;
import com.jsxnh.smartqq.service.ChatService;
import com.jsxnh.smartqq.service.FriendService;
import com.jsxnh.smartqq.service.UserService;

import javax.sql.DataSource;


public class springhibernatetest {

//	
	UserService userService=null;
	FriendService friendService=null;
	ChatService chatService=null;
	ApplicationContext ctx=null;
	{
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	{
		userService=ctx.getBean(UserService.class);
		friendService=ctx.getBean(FriendService.class);
		chatService=ctx.getBean(ChatService.class);
	}
//	SessionFactory sessionFactory=null;
//	Session session=null;
//	Transaction transaction=null;
//	
//
//    @org.junit.Before
//	public void before(){
//		
//		sessionFactory=(SessionFactory) ctx.getBean("sessionFactory");
//		session=sessionFactory.openSession();
//		transaction=session.beginTransaction();
//	}
//	
//	@org.junit.After
//	public void after(){
//	
//		transaction.commit();
//		session.close();
//		sessionFactory.close();
//	}
//	
	
	
	@Test
	public void testdatasource(){
//		javax.sql.DataSource datasource=ctx.getBean(DataSource.class);
//		System.out.println(datasource.getConnection());
	}
	
//	@Test
//	public void test() {
//		
//		Session session=getsession();
//        User user=new User();
//        Packet packet1=new Packet();
//        Packet packet2=new Packet();
//        packet1.setPacket_name("AA");
//        packet2.setPacket_name("BB");
//        
//        user.setUser_id(1679781770);
//        packet1.setUser(user);
//        packet2.setUser(user);
//       
//        user.getPackets().add(packet1);
//        user.getPackets().add(packet2);
//       
//        session.save(user);
//        session.save(packet1);
//        session.save(packet2);
//		
//	}
	
//	@Test
//	public void testquery(){
//		Session session=getsession();
//		User user=session.get(User.class, 1);
//		System.out.println(user.getPackets().size());
//	}
//	
//	@Test
//	public void testupdate(){
//		Session session=getsession();
//		User user=(User) session.get(User.class, 1);
//		Integer user_id=user.getId();
//		Set<Packet> packets=user.getPackets();
//		for(Packet packet:packets){
//			if(packet.getPacket_name().equals("CC"))
//				packet.setPacket_name("cc");
//		}
//	}

	@Test
    public void testregister(){
    	User user=new User();
    	user.setUser_id(4444);
    	user.setNick_name("AAAA");
    	user.setPassword("123456");
    	user.setAge(10);
    	user.setRegister_time(new Date(new java.util.Date().getTime()));
    	user.setName("bbb");
    	userService.userRegister(user,"你喜欢什么","c++");
    }
//	
//	@Test
//	public void testFinduser(){
//		User user=userService.findUserById(1679781770);
//		System.out.println(user.getNick_name());
//		
////		List<User> users=userService.findUsersByNickName("jsxnh");
////		for(User item:users){
////			System.out.println(item);
////		}
//	}
//	
//	@Test
//	public void testStatus(){
//		userService.updateUserStatus(1679781770);
//	}
//	
//	@Test
//	public void testupdatemessage(){
//		HashMap<String,String> datas=new HashMap<String,String>();
//		datas.put("name", "aaa");
//		datas.put("age", "10");
//		datas.put("message","简史小男孩");
//		userService.updateData(1679781770, datas);
//	}
//	
//	@Test
//	public void testSignature(){
//		userService.updateSignature(1679781770, "岂因祸福避趋之");
//	}
//	
//	@Test
//	public void modifypassword(){
//		userService.modifyPassword(1679781770, "123456");
//		userService.updateNickName(1679781770, "简史小男孩");
//	}
//	
	@Test
	public void testPacket(){
//	    userService.addPacket(1679781770, "朋友");
//	    userService.addPacket(1679781770, "家人");
//	    userService.addPacket(1679781770, "海豚有害");
//		User user=session.get(User.class,1);
//		Packet packet=new Packet();
//		packet.setPacket_name("风筝有风");
//		user.getPackets().add(packet);
//		session.save(user);
//		session.save(packet);
//		userService.addPacket(1111, "家人");
//		userService.addPacket(2222, "家人");
//		userService.addPacket(3333, "家人");
//		userService.addPacket(4444, "家人");
		userService.modifyPacket(1111, "家人", "朋友");
		
	}
//	
//	@Test
//	public void testaddFriend(){
//		friendService.addFriend(1679781770, 1111, "风筝有风", "家人");
//		friendService.addFriend(1679781770, 2222, "风筝有风", "家人");
//		friendService.addFriend(1679781770, 3333, "海豚有害", "家人");
//		friendService.addFriend(1679781770, 4444, "家人", "家人");
//		
//	}
//	
//	@Test
//	public void deleteFriend(){
//		friendService.delete(1679781770, 1436529607);
//	}
//	@Test
//	public void testmovePacket(){
//		//friendService.movePacket(1679781770, 1436529607, "风筝有风", "海豚有害");
//		friendService.movePacket(1679781770, 1436529607, "海豚有害", "风筝有风");
//	}
//	
//	@Test
//	public void testfindfriend(){
//		List<Friend> friends=friendService.findFriends(1679781770);
//		if(friends==null){
//			System.out.println("null");
//		}
//		System.out.println(friends.size());
//		for(Friend friend:friends){
//			System.out.println(friend.getId());
//		}
////		String hql="FROM Friend a WHERE a.user1_id=?";
////		String hql2="FROM Friend a WHERE a.user2_id=?";
////		List<Friend> friends=new ArrayList<Friend>();
////		friends.addAll(session.createQuery(hql).setParameter(0, 1679781770).list());
////		friends.addAll(session.createQuery(hql2).setParameter(0, 1679781770).list());
////		
//		for(Friend item:friends){
//			System.out.println(item.getUser1_id()+"----"+item.getUser2_id());
//		}
////		Friend friend=session.get(Friend.class, 6);
////		System.out.println(friend.getUser2_id());
//	}
//	
////	@Test
////	public void testpacket(){
////		Packet packet=session.get(Packet.class, 6);
////		System.out.println(packet.getUser().getNick_name());
////	}
//	
//	@Test
//	public void testPacketFriend(){
//		List<Friend> friends=friendService.findPacketFriends(1679781770, "风筝有风");
//		for(Friend friend:friends){
//			System.out.println(friend.getUser1_id()+"---"+friend.getUser2_id());
//		}
//	}
//	
//	@Test
//	public void modifyReamrkname(){
//		friendService.modifyRemarkName(1679781770, 1111, "拉机");
//		friendService.modifyRemarkName(2222, 1679781770, "垃圾");
//	}
//	
//	@Test
//	public void checkPassword(){
//		System.out.println(userService.checkPassword(1679781770,"123456"));
//	}
//	
//	@Test
//	public void checkPasswordAnswer(){
//		//System.out.println(userService.findProblem(6666));
//		System.out.println(userService.checkAnswer(6666, "c+"));
//	}
//	
	@Test
	public void testLogin() throws UnknownHostException{
		userService.LogOut(1111);
		//System.out.println(userService.LogIn(1111, "123456", InetAddress.getLocalHost().getHostAddress()));
	}
	
	@Test
	public void Testchat(){
		//chatService.sendMessage(1111, 2222, "你好");
		chatService.receiveMessage(2222);
	}
}

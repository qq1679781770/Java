package com.jsxnh.smartqqclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

public class SendtoServer{

	public static Socket socket;
	public SendtoServer(Socket s){
		socket=s;
	}
	public static void login(Integer id,String password){	    	
	    PrintWriter write = null;
		try {
			write = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", id);
		json.put("password", password);
		JSONObject loginjson=new JSONObject();
		loginjson.put("command2", json);
		write.println(loginjson.toString());
		write.flush();
		
	}
    public static void logout(Integer user_id){
    	PrintWriter write = null;
		try {
			write = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		JSONObject logoutjson=new JSONObject();
		logoutjson.put("command15", json);
		write.println(logoutjson.toString());
		write.flush();
    }
	public static void register(Integer user_id,String password,String nickname,String problem,String answer,Integer age,String name,String message ){
		PrintWriter write=null;
		try {
			write = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("password", password);
		json.put("nickname", nickname);
		json.put("name", name);
		json.put("age", age);
		json.put("message", message);
		json.put("problem", problem);
		json.put("answer", answer);
		JSONObject registerjson=new JSONObject();
		registerjson.put("command1", json);
		write.println(registerjson.toString());
		write.flush();
	}
	
	public static void updateNickname(Integer user_id,String nickname){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("nickname",nickname);
		JSONObject updatejson=new JSONObject();
		updatejson.put("command7",json);
		write.println(updatejson.toString());
		write.flush();
	}
	
	public static void updateSignature(Integer user_id,String signature){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("signature",signature);
		JSONObject updatejson=new JSONObject();
		updatejson.put("command3", json);
		write.println(updatejson.toString());
		write.flush();
	}
	
	public static void updateDatas(Integer user_id,String name,Integer age,String message){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("name",name);
		json.put("age", age);
		json.put("message", message);
		JSONObject updatejson=new JSONObject();
		updatejson.put("command4", json);
		write.println(updatejson.toString());
		write.flush();
	}
	
	public static void findProblem(Integer user_id){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		JSONObject findjson=new JSONObject();
		findjson.put("command5", json);
		write.println(findjson.toString());
		write.flush();
	}

	public static void modifyPassword(Integer user_id,String answer,String password){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("answer", answer);
		json.put("password", password);
		JSONObject modifyjson=new JSONObject();
		modifyjson.put("command6", json);
		write.println(modifyjson.toString());
		write.flush();
	}
	
	public static void modifyRemark(Integer user1_id,Integer user2_id,String remarkname){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		json.put("remarkname", remarkname);
		JSONObject modifyjson=new JSONObject();
		modifyjson.put("command8", json);
		write.println(modifyjson.toString());
		write.flush();
	}
	
	public static void addPacket(Integer user_id,String packetname){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("packetname", packetname);
		JSONObject addjson=new JSONObject();
		addjson.put("command9", json);
		write.println(addjson.toString());
		write.flush();
	}
	
	public static void deletePacket(Integer user_id,String packetname){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("packetname", packetname);
		JSONObject addjson=new JSONObject();
		addjson.put("command11", json);
		write.println(addjson.toString());
		write.flush();
	}
	
	public static void modifyPacket(Integer user_id,String oldpacketname,String newpacketname){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("oldpacketname", oldpacketname);
		json.put("newpacketname", newpacketname);
		JSONObject modifyjson=new JSONObject();
		modifyjson.put("command10",json);
		write.println(modifyjson.toString());
		write.flush();
	}
	
	public static void movePacket(Integer user1_id,Integer user2_id,String oldpacketname,String newpacketname){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		json.put("oldpacketname", oldpacketname);
		json.put("newpacketname", newpacketname);
		JSONObject movejson=new JSONObject();
		movejson.put("command12",json);
		write.println(movejson.toString());
		write.flush();
	}
	
	public static void finduserById(Integer user_id){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		JSONObject findjson=new JSONObject();
		findjson.put("command13", json);
		write.println(findjson.toString());
		write.flush();
	}
	
	public static void finduserByName(String nickname){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("nickname", nickname);
		JSONObject findjson=new JSONObject();
		findjson.put("command14", json);
		write.println(findjson.toString());
		write.flush();
	}
	
	public static void addFriend(Integer user1_id,Integer user2_id,String packetname){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		json.put("packetname", packetname);
		JSONObject addjson=new JSONObject();
		addjson.put("command16", json);
		write.println(addjson.toString());
		write.flush();
	}
	
	public static void agreeAddFriend(Integer user1_id,Integer user2_id,String packet1_name,String packet2_name){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		json.put("packet1_name", packet1_name);
		json.put("packet2_name", packet2_name);
		JSONObject agreefriend=new JSONObject();
		agreefriend.put("command17", json);
		write.println(agreefriend);
		write.flush();
	}
	
	public static void disagreeAddFriend(Integer user1_id,Integer user2_id){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		JSONObject disagree=new JSONObject();
		disagree.put("command18", json);
		write.println(disagree.toString());
		write.flush();
	}
	
	public static void sendChat(Integer user1_id,Integer user2_id,String content,String send_time){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		json.put("content",content);
		json.put("send_time", send_time);
		JSONObject sendjson=new JSONObject();
		sendjson.put("command19", json);
		write.println(sendjson.toString());
		write.flush();
	}
	
	public static void receiveChat(Integer user1_id,Integer user2_id,String content,String send_time,String receive_time){
		PrintWriter write=null;
		try{
			write=new PrintWriter(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		json.put("content",content);
		json.put("send_time", send_time);
		json.put("receive_time", receive_time);
		JSONObject receivejson=new JSONObject();
		receivejson.put("command20", json);
		write.println(receivejson.toString());
		write.flush();
	}	
}

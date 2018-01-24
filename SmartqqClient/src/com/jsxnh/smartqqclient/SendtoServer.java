package com.jsxnh.smartqqclient;

import java.io.*;
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

		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", id);
		json.put("password", password);
		JSONObject loginjson=new JSONObject();
		loginjson.put("command2", json);

		try {
			dout.writeUTF(loginjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
    public static void logout(Integer user_id){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		JSONObject logoutjson=new JSONObject();
		logoutjson.put("command15", json);
		try {
			dout.writeUTF(logoutjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	public static void register(Integer user_id,String password,String nickname,String problem,String answer,Integer age,String name,String message ){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
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
		try {
			dout.writeUTF(registerjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateNickname(Integer user_id,String nickname){

		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("nickname",nickname);
		JSONObject updatejson=new JSONObject();
		updatejson.put("command7",json);

		try {
			dout.writeUTF(updatejson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateSignature(Integer user_id,String signature){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("signature",signature);
		JSONObject updatejson=new JSONObject();
		updatejson.put("command3", json);
		try {
			dout.writeUTF(updatejson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDatas(Integer user_id,String name,Integer age,String message){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("name",name);
		json.put("age", age);
		json.put("message", message);
		JSONObject updatejson=new JSONObject();
		updatejson.put("command4", json);
		try {
			dout.writeUTF(updatejson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void findProblem(Integer user_id){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		JSONObject findjson=new JSONObject();
		findjson.put("command5", json);
		try {
			dout.writeUTF(findjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void modifyPassword(Integer user_id,String answer,String password){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("answer", answer);
		json.put("password", password);
		JSONObject modifyjson=new JSONObject();
		modifyjson.put("command6", json);
		try {
			dout.writeUTF(modifyjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void modifyRemark(Integer user1_id,Integer user2_id,String remarkname){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		json.put("remarkname", remarkname);
		JSONObject modifyjson=new JSONObject();
		modifyjson.put("command8", json);
		try {
			dout.writeUTF(modifyjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addPacket(Integer user_id,String packetname){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("packetname", packetname);
		JSONObject addjson=new JSONObject();
		addjson.put("command9", json);
		try {
			dout.writeUTF(addjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void deletePacket(Integer user_id,String packetname){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("packetname", packetname);
		JSONObject addjson=new JSONObject();
		addjson.put("command11", json);
		try {
			dout.writeUTF(addjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void modifyPacket(Integer user_id,String oldpacketname,String newpacketname){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		json.put("oldpacketname", oldpacketname);
		json.put("newpacketname", newpacketname);
		JSONObject modifyjson=new JSONObject();
		modifyjson.put("command10",json);
		try {
			dout.writeUTF(modifyjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void movePacket(Integer user1_id,Integer user2_id,String oldpacketname,String newpacketname){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		json.put("oldpacketname", oldpacketname);
		json.put("newpacketname", newpacketname);
		JSONObject movejson=new JSONObject();
		movejson.put("command12",json);
		try {
			dout.writeUTF(movejson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void finduserById(Integer user_id){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user_id", user_id);
		JSONObject findjson=new JSONObject();
		findjson.put("command13", json);
		try {
			dout.writeUTF(findjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void finduserByName(String nickname){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("nickname", nickname);
		JSONObject findjson=new JSONObject();
		findjson.put("command14", json);
		try {
			dout.writeUTF(findjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addFriend(Integer user1_id,Integer user2_id,String packetname){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		json.put("packetname", packetname);
		JSONObject addjson=new JSONObject();
		addjson.put("command16", json);
		try {
			dout.writeUTF(addjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void agreeAddFriend(Integer user1_id,Integer user2_id,String packet1_name,String packet2_name){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		json.put("packet1_name", packet1_name);
		json.put("packet2_name", packet2_name);
		JSONObject agreefriend=new JSONObject();
		agreefriend.put("command17", json);
		try {
			dout.writeUTF(agreefriend.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void disagreeAddFriend(Integer user1_id,Integer user2_id){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		JSONObject disagree=new JSONObject();
		disagree.put("command18", json);
		try {
			dout.writeUTF(disagree.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void sendChat(Integer user1_id,Integer user2_id,String content,String send_time){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONObject json=new JSONObject();
		json.put("user1_id", user1_id);
		json.put("user2_id", user2_id);
		json.put("content",content);
		json.put("send_time", send_time);
		JSONObject sendjson=new JSONObject();
		sendjson.put("command19", json);
		try {
			dout.writeUTF(sendjson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void receiveChat(Integer user1_id,Integer user2_id,String content,String send_time,String receive_time){
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
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
		try {
			dout.writeUTF(receivejson.toString());
			dout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public  static void sendFile(Integer send, Integer receive, String filename, File file){
		int length = 0;
		byte[] sendByte = null;
		DataOutputStream dout = null;
		FileInputStream fin = null;
		try{
			dout = new DataOutputStream(socket.getOutputStream());
			fin = new FileInputStream(file);
			sendByte = new byte[1024];
			JSONObject json = new JSONObject();
			json.put("send",send);
			json.put("receive",receive);
			json.put("filename",filename);
			JSONObject sendjson = new JSONObject();
			sendjson.put("command21",json);
			dout.writeUTF(sendjson.toString());
			dout.flush();
			while((length = fin.read(sendByte, 0, sendByte.length))!=-1){
				dout.write(sendByte,0,length);
				System.out.println(length);
				//dout.flush();
			}
			dout.flush();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

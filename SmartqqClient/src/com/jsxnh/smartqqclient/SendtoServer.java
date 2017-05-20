package com.jsxnh.smartqqclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
		loginjson.put("��¼", json);
		write.println(loginjson.toString());
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
		registerjson.put("ע��", json);
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
		updatejson.put("�޸��ǳ�",json);
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
		updatejson.put("����ǩ��", json);
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
		updatejson.put("������Ϣ", json);
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
		findjson.put("�����ܱ�����", json);
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
		modifyjson.put("�޸�����", json);
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
		addjson.put("��ӷ���", json);
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
		addjson.put("ɾ������", json);
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
		modifyjson.put("�޸ķ���",json);
		write.println(modifyjson.toString());
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
		findjson.put("��ȷ����", json);
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
		findjson.put("ģ������", json);
		write.println(findjson.toString());
		write.flush();
	}
}

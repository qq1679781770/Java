package com.jsxnh.smartqq.tcpserver;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TCPClient {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=null;
		
			socket=new Socket("127.0.0.1",9001);
			System.out.println("lianjiechenggong");
			PrintWriter out=new PrintWriter(socket.getOutputStream());
			

			JSONObject json=new JSONObject();
            json.put("user_id",3333);
            json.put("password", "123456");
//            json.put("content", "���");
//           json.put("packet1_name", "Ĭ�Ϸ���");
 //           json.put("packet2_name", "����");
//            json.put("user2_id", 22222);
  //          json.put("packetname", "Ĭ�Ϸ���");
            JSONObject jsonlogin=new JSONObject();
            jsonlogin.put("��¼", json);
			String strjson=jsonlogin.toString();
			//byte[] jsonByte=strjson.getBytes();
			//out.write(jsonByte);
			out.println(strjson);
			out.flush();
//			out.close();
			System.out.println("chuanshuwanbi");
//			socket.shutdownOutput();
			
			try{
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));	
			String strinputstream=new String(in.readLine());
			JSONObject jsonres=JSONObject.fromObject(strinputstream);
			System.out.println(jsonres);
//			out.close();
//			socket.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	  }
}


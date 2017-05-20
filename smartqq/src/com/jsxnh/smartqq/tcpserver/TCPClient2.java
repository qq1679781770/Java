package com.jsxnh.smartqq.tcpserver;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import net.sf.json.JSONObject;

public class TCPClient2 {

	public static void main(String[] args) {
		Socket socket=null;
		try{
			
			socket=new Socket("123.207.244.41",9001);
			System.out.println("lianjiechenggong");
			DataOutputStream out=new DataOutputStream(socket.getOutputStream());
			DataInputStream in=new DataInputStream(socket.getInputStream());
			
			JSONObject json=new JSONObject();
			json.put("user_id", 22222);
			json.put("password", "12345678");
			JSONObject jsonlogin=new JSONObject();
			jsonlogin.put("µÇÂ¼", json);
			String strjson=jsonlogin.toString();
			byte[] jsonByte=strjson.getBytes();
			out.write(jsonByte);
			out.flush();
			System.out.println("chuanshuwanbi");
			socket.shutdownOutput();
			
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			byte[] by=new byte[2048];
			int n;
			while((n=in.read(by))!=-1){
				baos.write(by, 0, n);
			}
			String strinputstream=new String(baos.toByteArray());
			JSONObject jsonres=JSONObject.fromObject(strinputstream);
			System.out.println(jsonres);
			//socket.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}

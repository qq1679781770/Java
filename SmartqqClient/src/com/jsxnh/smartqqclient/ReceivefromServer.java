package com.jsxnh.smartqqclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

public class ReceivefromServer extends Thread{
	private Socket socket;
	private Login login;
	private UserPanel user;
	
	public ReceivefromServer(Socket s,Login l){
		socket=s;
		login=l;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public UserPanel getUser() {
		return user;
	}
	public void setUser(UserPanel user) {
		this.user = user;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		while(true){
			try{
				BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String str=new String(in.readLine());
				JSONObject json=JSONObject.fromObject(str);
				if(json.containsKey("registerresult")){
					String result=json.getString("registerresult");
					if(result.equals("注册成功")){
						login.notifyall(Login.RegisterSuccessMessage, result);
					}else{
						login.notifyall(Login.RegisterFailureMessage, result);
					}
				}
				else if(json.containsKey("loginresult")){
					String result=json.getString("loginresult");
					if(result.equals("登录成功")){
						login.notifyall(Login.LoginSuccessMessage,json.toString());
					}else{
						login.notifyall(Login.LoginFailureMessage, result);
					}
				}
				else if(json.containsKey("modifypasswordresult")){
					String result=json.getString("modifypasswordresult");
					if(result.equals("修改成功")){
						login.notifyall(Login.ModifyPasswordSuccessMessage, result);
					}else{
						login.notifyall(Login.ModifyPasswordFailureMessage, result);
					}
				}
				else if(json.containsKey("findproblemresult")){
					if(json.getString("findproblemresult").equals("failure")){
						login.notifyall(Login.FindProblemFailureMessage, "failure");
					}
					else{
						login.notifyall(Login.FindProblemSuccessMessage, json.getString("findproblemresult"));
					}
				}
				else if(json.containsKey("updatenicknameresult")){
					user.notifyall(UserPanel.UpdateNicknameMessage, json.getString("updatenicknameresult"));
				}
				else if(json.containsKey("updatesignatureresult")){
					user.notifyall(UserPanel.UpdateSignatureMessage, json.getString("updatesignatureresult"));
				}
				else if(json.containsKey("updatedatasresult")){
					user.notifyall(UserPanel.UpdateDatasMessage, json.getString("updatedatasresult"));
				}
				else if(json.containsKey("modifypacketresult")){
					user.notifyall(UserPanel.ModifyPacketMessage, json.getString("modifypacketresult"));
				}
				else if(json.containsKey("addpacketresult")){
					user.notifyall(UserPanel.AddPacketMessage, json.getString("addpacketresult"));
				}
				else if(json.containsKey("deletepacketresult")){
					user.notifyall(UserPanel.DeletePacketMessage, json.getString("deletepacketresult"));
				}
				else if(json.containsKey("movepacketresult")){
					user.notifyall(UserPanel.MovePacketMessage, json.getString("movepacketresult"));
				}
				else if(json.containsKey("modifyremarkresult")){
					user.notifyall(UserPanel.ModifyRemarkMessage, json.getString("modifyremarkresult"));
				}
				else if(json.containsKey("addfriend")){
					user.getMsgLb().setText("有消息");
					user.injectMessage(UserPanel.AddFriendMessage, json.getJSONObject("addfriend").toString());
				}
				else if(json.containsKey("addfriendresult")){
					user.notifyall(UserPanel.AddFriendResultMessage, json.getString("addfriendresult"));
				}
				else if(json.containsKey("agreeaddfriendresult")){
					if(json.containsKey("agreeaddfriend")){
						user.getMsgLb().setText("有消息");
						user.injectMessage(UserPanel.AgreeAddFriendMessage, json.toString());
					}else{
						user.notifyall(UserPanel.AgreeAddFriendMessage, json.getString("agreeaddfriendresult"));
					}
				}
				else if(json.containsKey("disagreeaddfriendresult")){
					if(json.containsKey("disagreeaddfriend")){
						user.getMsgLb().setText("有消息");
						user.injectMessage(UserPanel.DisagreeFriendMessage, json.toString());
					}else{
						user.notifyall(UserPanel.DisagreeFriendMessage, json.getString("disagreeaddfriendresult"));
					}
				}
				else if(json.containsKey("finduserbyidresult")){
					if(json.containsKey("user")){
						user.notifyall(UserPanel.FindUserByIdSuccessMessage, json.getJSONObject("user").toString());
					}else{
						user.notifyall(UserPanel.FindUserByIdFailureMessage, json.getString("finduserbyidresult"));
					}
				}
				else if(json.containsKey("finduserbynicknameresult")){
					if(json.containsKey("users")){
						user.notifyall(UserPanel.FindUserByNicknameSuccessMessage, json.getJSONArray("users").toString());
					}else{
						user.notifyall(UserPanel.FindUserByNicknameFailureMessage, json.getString("finduserbynicknameresult"));
					}
				}
				else if(json.containsKey("sendchat")){
					JSONObject message=json.getJSONObject("sendchat");
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					SendtoServer.receiveChat(message.getInt("user1_id"), message.getInt("user2_id"), message.getString("content"),
							                 message.getString("send_time"), df.format(new Date()));
					if(user.getchatpanels().containsKey(message.getInt("user1_id"))){
						user.getchatpanels().get(message.getInt("user1_id")).appendMessage(message);
					}
					else{
						user.getMsgLb().setText("有消息");
						user.injectMessage(UserPanel.ChatMessage, json.getJSONObject("sendchat").toString());
					}					
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
}

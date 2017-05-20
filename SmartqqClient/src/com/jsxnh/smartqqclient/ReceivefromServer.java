package com.jsxnh.smartqqclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
//				login.notifyall(str);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
}

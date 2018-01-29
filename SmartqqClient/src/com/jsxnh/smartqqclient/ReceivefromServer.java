package com.jsxnh.smartqqclient;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

public class ReceivefromServer extends Thread{
	private final static String SUCCESS = "success";
	private final static String FAILURE = "failure";
	private Socket socket;
	private Login login;
	private UserPanel user;
	private static String relativelyPath = System.getProperty("user.dir");
	private String path;
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
		path = relativelyPath+"\\"+String.valueOf(user.getUser().getUser_id());
		File f = new File(relativelyPath+"\\"+String.valueOf(user.getUser().getUser_id()));
		if(!f.exists()){
			f.mkdir();
		}
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try{
			while(true){
				/**
				BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String str=new String(in.readLine());
				JSONObject json=JSONObject.fromObject(str);**/
				DataInputStream din = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				JSONObject json = JSONObject.fromObject(din.readUTF());
				System.out.println(json);
				if(json.containsKey("registerresult")){
					String result=json.getString("registerresult");
					if(result.equals(SUCCESS)){
						login.notifyall(Login.RegisterSuccessMessage, result);
					}else{
						login.notifyall(Login.RegisterFailureMessage, result);
					}
				}
				else if(json.containsKey("loginresult")){
					String result=json.getString("loginresult");
					if(result.equals(SUCCESS)){
						login.notifyall(Login.LoginSuccessMessage,json.toString());
					}else{
						login.notifyall(Login.LoginFailureMessage, result);
					}
				}
				else if(json.containsKey("modifypasswordresult")){
					String result=json.getString("modifypasswordresult");
					if(result.equals(SUCCESS)){
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
				}else if(json.containsKey("sendFileResult")){
					user.notifyall(UserPanel.SendFileResult,json.getString("sendFileResult").toString());
				}else if(json.containsKey("filereceive")){
					user.getFileManager().setText("待收文件");
				}else if(json.containsKey("getFiles")){
					user.notifyall(UserPanel.GetFiles,json.getJSONObject("getFiles").toString());
				}else if(json.containsKey("receiveFileResult")){
					user.notifyall(UserPanel.ReceiveFileMResult,json.getString("receiveFileResult"));
				}else if(json.containsKey("ReceiveFile")){
					byte[] inputByte = new byte[1024];
					int length = 0;


					FileOutputStream fout = new FileOutputStream(new File(path+"\\"+json.getString("filename")));
					int l = json.getInt("length");
					int ll = 0 ;

					while ((length = din.read(inputByte, 0, inputByte.length)) !=-1) {
						fout.write(inputByte, 0, length);
						fout.flush();
						ll = ll+length;

						if(ll==l){
							break;
						}

					}
					System.out.println("hwllo");

				}else if(json.containsKey("filefeedback")){
					user.getMsgLb().setText("有消息");
					user.injectMessage(UserPanel.FileFeedBack, json.getJSONObject("filefeedback").toString());
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
}

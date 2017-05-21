package com.jsxnh.smartqq.tcpserver;

 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jsxnh.smartqq.entities.Friend;
import com.jsxnh.smartqq.entities.Packet;
import com.jsxnh.smartqq.entities.TemporaryFriend;
import com.jsxnh.smartqq.entities.TemporaryMessage;
import com.jsxnh.smartqq.entities.User;
import com.jsxnh.smartqq.service.ChatService;
import com.jsxnh.smartqq.service.FriendService;
import com.jsxnh.smartqq.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ServerThread implements Runnable {

	private Socket socket;
	private UserService userService=null;
	private FriendService friendService=null;
	private ChatService chatService=null;
	private ApplicationContext ctx=null;
	private BufferedReader in=null;
	private PrintWriter out=null;
	private Integer user_id;
	
	
	public Integer getUser_id(){
		return this.user_id;
	}
	
	public ServerThread(Socket socket){
		this.socket=socket;
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	    friendService = ctx.getBean(FriendService.class);
	    userService=ctx.getBean(UserService.class);
	    chatService=ctx.getBean(ChatService.class);
//	    try{
//	    	
//	    }catch(IOException e){
//	    	
//	    }
	}
		
	@Override
	public void run() {
		while(true){
		try {		
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			JSONObject json=JSONObject.fromObject(doread(in));
			//in.close();
			System.out.println(json);
			out=new PrintWriter(socket.getOutputStream());
			@SuppressWarnings("rawtypes")
			Iterator it=json.keys();
			String function=it.next().toString();
			JSONObject functionjson=JSONObject.fromObject(json.get(function));
			String result="";
			if(function.equals("ע��")){
				result=Register(functionjson);
			}
			else if(function.equals("��¼")){
				result=Login(functionjson);
			}
			else if(function.equals("����ǩ��")){
				result=updateSignature(functionjson);
			}
			else if(function.equals("������Ϣ")){
				result=updateDatas(functionjson);
			}
			else if(function.equals("�����ܱ�����")){
				result=findProblem(functionjson);
			}
			else if(function.equals("�޸�����")){
				result=modifyPassword(functionjson);
			}
			else if(function.equals("�޸��ǳ�")){
				result=updateNickName(functionjson);
			}
			else if(function.equals("���ѱ�ע")){
				result=modifyRemark(functionjson);
			}
			else if(function.equals("��ӷ���")){
				result=addPacket(functionjson);
			}
			else if(function.equals("�޸ķ���")){
				result=modifyPacket(functionjson);
			}
			else if(function.equals("ɾ������")){
				result=deletePacket(functionjson);
			}
			else if(function.equals("�ƶ�����")){
				result=movePacket(functionjson);
			}
			else if(function.equals("��ȷ����")){
				result=findUserById(functionjson);
			}
			else if(function.equals("ģ������")){
				result=findUsersByNickName(functionjson);
			}
			else if(function.equals("�ǳ�")){
				Logout(functionjson);
			}
			else if(function.equals("��Ӻ���")){
				result=addFriend(functionjson);
			}
			else if(function.equals("ͬ�����")){
				result=agreeAddFriend(functionjson);
			}
			else if(function.equals("�ܾ����")){
				result=disagreeAddFriend(functionjson);
			}
			else if(function.equals("������Ϣ")){
				result=chatSend(functionjson);
			}
			else if(function.equals("������Ϣ")){
				result=chatReceive(functionjson);
			}
			System.out.println(result);
			out.println(result);
			out.flush();
//			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		}
	}
	
	public static String  doread(BufferedReader in){
//	    try {
//			System.out.println(in.available());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String strinputstream = "";
		try{
//		ByteArrayOutputStream baos=new ByteArrayOutputStream();
//		byte[] by=new byte[2048];
//		int n;
//		while((n=in.read(by))!=-1){
//			baos.write(by, 0, n);
//		}
		strinputstream=new String(in.readLine());
		}catch(IOException e){
			e.printStackTrace();
		}
	    return strinputstream;
	}

	//��¼undone
	private String Login(JSONObject json){
		this.user_id=(Integer)json.get("user_id");
		Integer user_id=(Integer)json.get("user_id");
		String password=(String)json.get("password");
		String ip=socket.getInetAddress().getHostAddress();
		JSONObject result=new JSONObject();
		if(userService.LogIn(user_id, password, ip)){
			result.put("loginresult", "��¼�ɹ�");
			//������Ϣ
			User user=userService.findUserById(user_id);
			JSONObject user_message=new JSONObject();
			user_message.put("user_id", user_id);
			user_message.put("nickname", user.getNick_name());
			user_message.put("signature", user.getSignature());
			user_message.put("name", user.getName());
			user_message.put("age", user.getAge());
			user_message.put("message", user.getMessage());
			user_message.put("status", user.getStatus());
			Set<Packet> packets=(Set<Packet>) user.getPackets();
			JSONArray packet=new JSONArray();
			for(Packet packet_:packets){
				packet.add(packet_.getPacket_name());
			}
	        user_message.put("packets",packet);
			result.put("user", user_message);
			//�����б�
			JSONArray friend=new JSONArray();
			for(Packet packet_:packets){
				List<Friend> frineds=friendService.findPacketFriends(user_id, packet_.getPacket_name());
				JSONArray packetfriend=new JSONArray();
				for(Friend friend_:frineds){
					JSONObject friend_message=new JSONObject();
					if(friend_.getUser1_id().equals(user_id)){
						User friend_user=userService.findUserById(friend_.getUser2_id());
						friend_message.put("user_id", friend_user.getUser_id());
						friend_message.put("nickname", friend_user.getNick_name());
						friend_message.put("remarkname", friend_.getRemark1_name());
						friend_message.put("signature", friend_user.getSignature());
						friend_message.put("status", friend_user.getStatus());
					}
					else{
						User friend_user=userService.findUserById(friend_.getUser1_id());
						friend_message.put("user_id", friend_user.getUser_id());
						friend_message.put("nickname", friend_user.getNick_name());
						friend_message.put("remarkname", friend_.getRemark2_name());
						friend_message.put("signature", friend_user.getSignature());
						friend_message.put("status", friend_user.getStatus());
					}
					packetfriend.add(friend_message);
				}
				JSONObject jsonpacket=new JSONObject();
				jsonpacket.put(packet_.getPacket_name(), packetfriend);
				friend.add(jsonpacket);
			}
			result.put("friends", friend);
			
			List<TemporaryFriend> temporaryFriends=friendService.findTemporaryFriends(user_id);
			if(temporaryFriends.size()>0){
				JSONArray addFriends=new JSONArray();
				for(TemporaryFriend tem:temporaryFriends){
					JSONObject addFriend=new JSONObject();
					addFriend.put("user1_id", tem.getUser1_id());
					addFriend.put("user2_id", tem.getUser2_id());
					addFriend.put("packet1_name", tem.getPacket_name());
					addFriends.add(addFriend);
				}
				result.put("addFriends",addFriends);
			}
			
			List<TemporaryMessage> temporaryMessages=chatService.findMessages(user_id);
			if(temporaryMessages.size()>0){
				JSONArray messages=new JSONArray();
				for(TemporaryMessage tem:temporaryMessages){
					JSONObject message=new JSONObject();
					message.put("user1_id", tem.getSend_id());
					message.put("user2_id", tem.getReceive());
					message.put("sendtime", tem.getSend_time());
					message.put("content", tem.getContent());
					messages.add(message);
				}
				result.put("chatMessages", messages);
			}
			
		}
		else{
			result.put("loginresult", "��¼ʧ��");
		}
		return result.toString();
	}
	private void   Logout(JSONObject json){
		userService.LogOut((Integer)json.get("user_id"));
	}
    private String Register(JSONObject json){
    	JSONObject result=new JSONObject();
    	Integer user_id=(Integer)json.get("user_id");
    	User user_=userService.findUserById(user_id);
    	if(user_==null){
    	User user=new User();
    	user.setUser_id((Integer)json.get("user_id"));
    	user.setPassword((String)json.get("password"));
    	user.setNick_name((String)json.get("nickname"));
    	user.setRegister_time(new Date(new java.util.Date().getTime()));
    	if(json.containsKey("name")){
    	     user.setName((String)(json.get("name")));
    	}
    	if(json.containsKey("age")){
    	     user.setAge((Integer)json.get("age"));
    	}
    	if(json.containsKey("message")){
    	     user.setMessage((String)json.get("message"));
    	}
    	userService.userRegister(user, (String)json.get("problem"), (String)json.get("answer"));
    	result.put("registerresult", "ע��ɹ�");
    	}else{
    		result.put("registerresult","ע��ʧ��");
    	}
    	return result.toString();
    }
    private String updateNickName(JSONObject json){
    	JSONObject result=new JSONObject();
    	userService.updateNickName((Integer)json.get("user_id"), (String)json.get("nickname"));
    	result.put("updatenicknameresult", "���³ɹ�");
    	return result.toString();
    }
    private String modifyRemark(JSONObject json){
    	JSONObject result=new JSONObject();
    	friendService.modifyRemarkName(json.getInt("user1_id"), json.getInt("user2_id"), json.getString("remarkname"));
    	result.put("modifyremarkresult", "�޸ĳɹ�");
    	return result.toString();
    }
    private String updateSignature(JSONObject json){
    	userService.updateSignature((Integer)json.get("user_id"), (String)json.get("signature"));
    	JSONObject result=new JSONObject();
    	result.put("updatesignatureresult", "���³ɹ�");
    	return result.toString();
    }
    private String updateDatas(JSONObject json){
    	HashMap<String, String>datas=new HashMap<>();
    	if(json.containsKey("name")){
    	    datas.put("name", (String)json.get("name"));
    	}
    	if(json.containsKey("age")){
    	    datas.put("age", String.valueOf((Integer)json.get("age")));
    	}
    	if(json.containsKey("message")){
    	    datas.put("message", (String)json.get("message"));
    	}
    	userService.updateData((Integer)json.get("user_id"), datas);
    	JSONObject result=new JSONObject();
    	result.put("updatedatasresult", "���³ɹ�");
        return result.toString();
    }
    private String findProblem(JSONObject json){
    	JSONObject result=new JSONObject();
    	Integer user_id=json.getInt("user_id");
    	User user=userService.findUserById(user_id);
    	if(user==null){
    		result.put("findproblemresult", "failure");
    	}else{
    	    String answer=userService.findProblem((Integer)json.get("user_id"));   	
    	    result.put("findproblemresult", answer);
    	}
    	return result.toString();
    }
    private String modifyPassword(JSONObject json){
    	JSONObject result=new JSONObject();
    	if(userService.checkAnswer((Integer)json.get("user_id"),(String)json.get("answer"))){
    		userService.modifyPassword((Integer)json.get("user_id"),(String)json.get("password"));
    		result.put("modifypasswordresult", "�޸ĳɹ�");
    	}
    	else{
    		result.put("modifypasswordresult", "�ܱ�����");
    	}
    	return result.toString();
    }
    private String addPacket(JSONObject json){
    	JSONObject result=new JSONObject();
    	userService.addPacket((Integer)json.get("user_id"), (String)json.get("packetname"));
    	result.put("addpacketresult", "��ӳɹ�");
    	return result.toString();
    }
    private String deletePacket(JSONObject json){
    	JSONObject result=new JSONObject();
    	userService.deletePacket((Integer)json.get("user_id"), (String)json.get("packetname"));
    	result.put("deletepacketresult", "ɾ���ɹ�");
    	return result.toString();
    }
    private String modifyPacket(JSONObject json){
    	JSONObject result=new JSONObject();
    	userService.modifyPacket((Integer)json.get("user_id"),(String)json.get("oldpacketname"),
    			(String)json.get("newpacketname"));
    	result.put("modifypacketresult", "�޸ĳɹ�");
    	return result.toString();
    }
    private String movePacket(JSONObject json){
    	JSONObject result=new JSONObject();
    	friendService.movePacket(json.getInt("user1_id"), json.getInt("user2_id"), json.getString("oldpacketname"), 
    			                 json.getString("newpacketname"));
    	result.put("movepacketresult", "�ƶ��ɹ�");
    	return result.toString();
    }
    private String findUserById(JSONObject json){
    	JSONObject result=new JSONObject();
    	User user=userService.findUserById((Integer)json.get("user_id"));
    	if(user!=null&&friendService.findFriend(user_id, user.getUser_id())==null){
    		result.put("finduserbyidresult", "���ҳɹ�");
    		JSONObject userjson=new JSONObject();
    		userjson.put("user_id", user.getUser_id());
    		userjson.put("nickname", user.getNick_name());
    		userjson.put("name", user.getName());
    		userjson.put("age", user.getAge());
    		userjson.put("message", user.getMessage());
    		result.put("user", userjson);
    	}
    	else{
    		result.put("finduserbyidresult", "����ʧ��");
    	}
    	return result.toString();
    }
    private String findUsersByNickName(JSONObject json){
    	JSONObject result=new JSONObject();
    	List<User> users=userService.findUsersByNickName((String)json.get("nickname"));
    	if(users.size()>0){
    		result.put("finduserbynicknameresult", "���ҳɹ�");
    		JSONArray usersarray=new JSONArray();
    		for(User user_:users){
    			if(friendService.findFriend(user_id, user_.getUser_id())!=null){
    				continue;
    			}
    			JSONObject userjson=new JSONObject();
    			userjson.put("user_id", user_.getUser_id());
    			userjson.put("nickname", user_.getNick_name());
    			userjson.put("name", user_.getName());
    			userjson.put("age", user_.getAge());
    			userjson.put("message", user_.getMessage());
    			usersarray.add(userjson);
    		}
    		result.put("users", usersarray);
    	}else{
    		result.put("finduserbunicknameresult","����ʧ��");
    	}
    	return result.toString();
    }
    private String addFriend(JSONObject json){
    	JSONObject result=new JSONObject();
    	this.user_id=(Integer)json.get("user1_id");
    	User user=userService.findUserById((Integer)json.get("user2_id"));
    	if(user.getStatus()==1){
    		JSONObject addjson=new JSONObject();
    		addjson.put("addfriend", json);
    		sendFriendMessage((Integer)json.get("user2_id"),addjson.toString());
    		result.put("addfriendresult","�ȴ����");
    	}
    	else{
    		friendService.addTemporaryFriend((Integer)json.get("user1_id"),
    				(Integer)json.get("user2_id"), (String)json.get("packetname"));
    		result.put("addfriendresult", "�ȴ�����");
    	}
    	return result.toString();
    }
    
    private String agreeAddFriend(JSONObject json){
    	friendService.addFriend((Integer)json.get("user1_id"),(Integer)json.get("user2_id"), 
    			(String)json.get("packet1_name"),(String)json.get("packet2_name")); 	
    	if(friendService.findTemporaryFriend((Integer)json.get("user1_id"),(Integer)json.get("user2_id"))!=null){
    		friendService.deleteTemporaryFriend((Integer)json.get("user1_id"),(Integer)json.get("user2_id"));
    	}
    	User user2=userService.findUserById(json.getInt("user2_id"));
    	User user1=userService.findUserById(json.getInt("user1_id"));
    	json.put("nickname1", user1.getNick_name());
    	json.put("nickname2", user2.getNick_name());
    	json.put("status2", user2.getStatus());
    	json.put("status1", user1.getStatus());
    	if(user2.getSignature()!=null){
    		json.put("signature2", user2.getSignature());
    	}
    	if(user1.getSignature()!=null){
    		json.put("signature1", user2.getSignature());
    	}
    	JSONObject result=new JSONObject();
    	result.put("agreeaddfriendresult", "��ӳɹ�");
    	JSONObject message=new JSONObject();
    	message.put("agreeaddfriendresult", "��ӳɹ�");
    	message.put("agreeaddfriend", json);
    	sendFriendMessage((Integer)json.get("user1_id"), message.toString());
    	return result.toString();
    }
    
    private String disagreeAddFriend(JSONObject json){
    	if(friendService.findTemporaryFriend((Integer)json.get("user1_id"),(Integer)json.get("user2_id"))!=null){
    		friendService.deleteTemporaryFriend((Integer)json.get("user1_id"),(Integer)json.get("user2_id"));
    	}
    	JSONObject result=new JSONObject();
    	result.put("disagreeaddfriendresult", "�ܾ��ɹ�");
    	JSONObject message=new JSONObject();
    	message.put("disagreeaddfriendresult", "�ܾ����");
    	message.put("disagreeaddfriend", json);
        sendFriendMessage((Integer)json.get("user1_id"), message.toString());
    	return result.toString();
    }
    
    private String chatSend(JSONObject json){
    	JSONObject result=new JSONObject();
    	this.user_id=(Integer)json.get("user1_id");
    	User user=userService.findUserById((Integer)json.get("user2_id"));
    	if(user.getStatus()==1){
    		sendChatMessage((Integer)json.get("user2_id"), json.toString());
    	}else{
    		chatService.sendMessage((Integer)json.get("user1_id"),(Integer)json.get("user2_id"),
    				(String)json.get("content"));
    	}
    	return result.toString();
    }
    
    private String chatReceive(JSONObject json){
    	JSONObject result=new JSONObject();
    	this.user_id=(Integer)json.get("user2_id");
    	if(chatService.findTemporaryMessage((Integer)json.get("user1_id"),(Integer)json.get("user2_id"))){
    		chatService.receiveMessage((Integer)json.get("user1_id"),(Integer)json.get("user2_id"));
    	}else{
    		chatService.saveMessage((Integer)json.get("user1_id"),(Integer)json.get("user2_id"), 
    				(String)json.get("content"),(java.util.Date)json.get("send_time"));
    	}
    	return result.toString();
    }
    
    private void sendFriendMessage(Integer user_id,String message){
    	List<ServerThread>serverThreads=TCPServer.getserverThreads();
    	for(ServerThread serverThread:serverThreads){
    		if(serverThread.getUser_id()==user_id){
    			serverThread.convertFriendMessage(message);
    			break;
    		}
    	}
    }
    
    private void sendChatMessage(Integer user_id,String message){
    	List<ServerThread>serverThreads=TCPServer.getserverThreads();
    	for(ServerThread serverThread:serverThreads){
    		if(serverThread.getUser_id()==user_id){
    			
    			break;
    		}
    	}
    }
    
    public void convertFriendMessage(String message){
    	PrintWriter write=null;
    	try {
			write=new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	write.println(message);
    	write.flush();
    }
    
    public void convertChatMessage(String message){
    	PrintWriter write=null;
    	try {
			write=new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	write.println(message);
    	write.flush();
    }
  
}




package com.jsxnh.smartqq.tcpserver;

 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.jsxnh.smartqq.service.CommandService;
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
	private final  static String SUUCESS = "success";
	private final static String FAILURE = "failure";
	private Socket socket;
	private UserService userService=null;
	private FriendService friendService=null;
	private ChatService chatService=null;
	private CommandService commandService = null;
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
	    commandService = ctx.getBean(CommandService.class);
//	    try{
//	    	
//	    }catch(IOException e){
//	    	
//	    }
	}
		
	@Override
	public void run() {
		try {
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out=new PrintWriter(socket.getOutputStream());
		while(true){
			JSONObject json=JSONObject.fromObject(doread(in));
			//in.close();
			System.out.println(json);
			@SuppressWarnings("rawtypes")
			Iterator it=json.keys();
			String function=it.next().toString();
			JSONObject functionjson=JSONObject.fromObject(json.get(function));
			String methodstr = commandService.getFunction(function);
			Method m = null;
			String result="";
			try {
				m = ServerThread.class.getDeclaredMethod(methodstr,JSONObject.class);
				result = (String) m.invoke(this,functionjson);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			System.out.println(result);
			if (!result.equals("success")) {
				out.println(result);
				out.flush();
			}

//			out.close();
		}
		}
		catch (IOException e) {
			TCPServer.getServerThreadMap().remove(user_id);
			if(!socket.isClosed()){
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
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

	//登录undone
	private String Login(JSONObject json){
		this.user_id=(Integer)json.get("user_id");
		TCPServer.getServerThreadMap().put(user_id,this);
		Integer user_id=(Integer)json.get("user_id");
		String password=(String)json.get("password");
		String ip=socket.getInetAddress().getHostAddress();
		JSONObject result=new JSONObject();
		if(userService.LogIn(user_id, password, ip)){
			result.put("loginresult",SUUCESS);
			//个人信息
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
			//朋友列表
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
					User user1=userService.findUserById(tem.getUser1_id());
					addFriend.put("nickname1", user1.getNick_name());
					addFriend.put("status1", user1.getStatus());
					if(user1.getSignature()!=null){
						addFriend.put("signature1", user1.getSignature());
					}
					addFriend.put("user1_id", tem.getUser1_id());
					addFriend.put("user2_id", tem.getUser2_id());
					addFriend.put("packet1_name", tem.getPacket_name());
					addFriends.add(addFriend);
				}
				result.put("addFriends",addFriends);
			}
			
			List<TemporaryMessage> temporaryMessages=chatService.findMessages(user_id);
			if(temporaryMessages.size()>0){
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				JSONArray messages=new JSONArray();
				Map<Integer,ArrayList<TemporaryMessage>> m = new HashMap<>();
				for(TemporaryMessage t:temporaryMessages){
					if(m.containsKey(t.getSend_id())){
						m.get(t.getSend_id()).add(t);
					}else{
						ArrayList<TemporaryMessage> l = new ArrayList<>();
						l.add(t);
						m.put(t.getSend_id(),l);
					}
				}
				for(Integer key:m.keySet()){
					JSONObject message = new JSONObject();
					User user1=userService.findUserById(m.get(key).get(0).getSend_id());
					message.put("user1_id",user1.getUser_id());
					message.put("nickname1",user1.getNick_name());
					message.put("status1",user1.getStatus());
					message.put("user2_id",user_id);
					if(user1.getSignature()!=null){
						message.put("signature1",user1.getSignature());
					}
					JSONArray contents = new JSONArray();
					for(TemporaryMessage tm:m.get(key)){
						JSONObject j = new JSONObject();
						j.put("user1_id",tm.getSend_id());
						j.put("user2_id",tm.getReceive());
						j.put("send_time",df.format(tm.getSend_time()));
						j.put("content",tm.getContent());
						contents.add(j);
					}
					message.put("contents",contents);
					messages.add(message);
				}

				result.put("chatMessages", messages);
			}
			
		}
		else{
			result.put("loginresult", FAILURE);
		}
		return result.toString();
	}
	private String   Logout(JSONObject json){
		userService.LogOut((Integer)json.get("user_id"));
		TCPServer.getServerThreadMap().remove(user_id);
		return "success";
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
    	result.put("registerresult", SUUCESS);
    	}else{
    		result.put("registerresult",FAILURE);
    	}
    	return result.toString();
    }
    private String updateNickName(JSONObject json){
    	JSONObject result=new JSONObject();
    	userService.updateNickName((Integer)json.get("user_id"), (String)json.get("nickname"));
    	result.put("updatenicknameresult", SUUCESS);
    	return result.toString();
    }
    private String modifyRemark(JSONObject json){
    	JSONObject result=new JSONObject();
    	friendService.modifyRemarkName(json.getInt("user1_id"), json.getInt("user2_id"), json.getString("remarkname"));
    	result.put("modifyremarkresult", SUUCESS);
    	return result.toString();
    }
    private String updateSignature(JSONObject json){
    	userService.updateSignature((Integer)json.get("user_id"), (String)json.get("signature"));
    	JSONObject result=new JSONObject();
    	result.put("updatesignatureresult", SUUCESS);
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
    	result.put("updatedatasresult", SUUCESS);
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
    		result.put("modifypasswordresult", SUUCESS);
    	}
    	else{
    		result.put("modifypasswordresult", FAILURE);
    	}
    	return result.toString();
    }
    private String addPacket(JSONObject json){
    	JSONObject result=new JSONObject();
    	userService.addPacket((Integer)json.get("user_id"), (String)json.get("packetname"));
    	result.put("addpacketresult", SUUCESS);
    	return result.toString();
    }
    private String deletePacket(JSONObject json){
    	JSONObject result=new JSONObject();
    	userService.deletePacket((Integer)json.get("user_id"), (String)json.get("packetname"));
    	result.put("deletepacketresult", SUUCESS);
    	return result.toString();
    }
    private String modifyPacket(JSONObject json){
    	JSONObject result=new JSONObject();
    	userService.modifyPacket((Integer)json.get("user_id"),(String)json.get("oldpacketname"),
    			(String)json.get("newpacketname"));
    	result.put("modifypacketresult", SUUCESS);
    	return result.toString();
    }
    private String movePacket(JSONObject json){
    	JSONObject result=new JSONObject();
    	friendService.movePacket(json.getInt("user1_id"), json.getInt("user2_id"), json.getString("oldpacketname"), 
    			                 json.getString("newpacketname"));
    	result.put("movepacketresult", SUUCESS);
    	return result.toString();
    }
    private String findUserById(JSONObject json){
    	JSONObject result=new JSONObject();
    	User user=userService.findUserById((Integer)json.get("user_id"));
    	if(user!=null&&friendService.findFriend(user_id, user.getUser_id())==null){
    		result.put("finduserbyidresult", SUUCESS);
    		JSONObject userjson=new JSONObject();
    		userjson.put("user_id", user.getUser_id());
    		userjson.put("nickname", user.getNick_name());
    		userjson.put("name", user.getName());
    		userjson.put("age", user.getAge());
    		userjson.put("message", user.getMessage());
    		result.put("user", userjson);
    	}
    	else{
    		result.put("finduserbyidresult", FAILURE);
    	}
    	return result.toString();
    }
    private String findUsersByNickName(JSONObject json){
    	JSONObject result=new JSONObject();
    	List<User> users=userService.findUsersByNickName((String)json.get("nickname"));
    	if(users.size()>0){
    		result.put("finduserbynicknameresult", SUUCESS);
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
    		result.put("finduserbunicknameresult",FAILURE);
    	}
    	return result.toString();
    }
    private String addFriend(JSONObject json){
    	JSONObject result=new JSONObject();
    	User user=userService.findUserById((Integer)json.get("user2_id"));
    	User user1=userService.findUserById(json.getInt("user1_id"));
    	json.put("nickname1", user1.getNick_name());
    	json.put("status1", user1.getStatus());
    	if(user1.getSignature()!=null){
    		json.put("signature1", user1.getSignature());
    	}
    	if(user.getStatus()==1){
    		JSONObject addjson=new JSONObject();
    		addjson.put("addfriend", json);
    		sendMessage((Integer)json.get("user2_id"),addjson.toString());
    		result.put("addfriendresult","等待添加");
    	}
    	else{
    		friendService.addTemporaryFriend((Integer)json.get("user1_id"),
    				(Integer)json.get("user2_id"), (String)json.get("packetname"));
    		result.put("addfriendresult", "等待上线");
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
//    	json.put("nickname1", user1.getNick_name());
    	json.put("nickname2", user2.getNick_name());
    	json.put("status2", user2.getStatus());
//    	json.put("status1", user1.getStatus());
    	if(user2.getSignature()!=null){
    		json.put("signature2", user2.getSignature());
    	}
//    	if(user1.getSignature()!=null){
//    		json.put("signature1", user2.getSignature());
//    	}
    	JSONObject result=new JSONObject();
    	result.put("agreeaddfriendresult", "添加成功");
    	JSONObject message=new JSONObject();
    	message.put("agreeaddfriendresult", "添加成功");
    	message.put("agreeaddfriend", json);
    	if(user1.getStatus()==1){
			sendMessage((Integer)json.get("user1_id"), message.toString());
		}
    	return result.toString();
    }
    
    private String disagreeAddFriend(JSONObject json){
    	if(friendService.findTemporaryFriend((Integer)json.get("user1_id"),(Integer)json.get("user2_id"))!=null){
    		friendService.deleteTemporaryFriend((Integer)json.get("user1_id"),(Integer)json.get("user2_id"));
    	}
    	JSONObject result=new JSONObject();
    	result.put("disagreeaddfriendresult", "拒绝成功");
    	JSONObject message=new JSONObject();
    	message.put("disagreeaddfriendresult", "拒绝添加");
    	message.put("disagreeaddfriend", json);
		User user1=userService.findUserById(json.getInt("user1_id"));
		if(user1.getStatus()==1){
			sendMessage((Integer)json.get("user1_id"), message.toString());
		}
    	return result.toString();
    }
    
    private String chatSend(JSONObject json){
    	JSONObject result=new JSONObject();
    	User user=userService.findUserById((Integer)json.get("user2_id"));
    	User user1=userService.findUserById(json.getInt("user1_id"));
    	json.put("nickname1", user1.getNick_name());
    	if(user1.getSignature()!=null){
    		json.put("signature1", user1.getSignature());
    	}
    	if(user.getStatus()==1){
    		JSONObject sendchat=new JSONObject();
    		sendchat.put("sendchat", json);
    		sendMessage((Integer)json.get("user2_id"), sendchat.toString());
    	}else{
    		chatService.sendMessage((Integer)json.get("user1_id"),(Integer)json.get("user2_id"),
    				(String)json.get("content"));
    	}
    	return result.toString();
    }
    
    private String chatReceive(JSONObject json) throws ParseException{
    	JSONObject result=new JSONObject();
    	SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(chatService.findTemporaryMessage((Integer)json.get("user1_id"),(Integer)json.get("user2_id"),json.getString("send_time"))){
    		chatService.receiveMessage((Integer)json.get("user1_id"),(Integer)json.get("user2_id"),json.getString("send_time"));
    	}else{
    		chatService.saveMessage((Integer)json.get("user1_id"),(Integer)json.get("user2_id"), 
    				(String)json.get("content"),df.parse(json.getString("send_time")),
    				df.parse(json.getString("receive_time")));
    	}
    	return result.toString();
    }
    
    private void sendMessage(Integer user_id,String message){

		ServerThread s = TCPServer.getServerThreadMap().get(user_id);
		if(s!=null){
			s.convertMessage(message);
		}
    }
    public void convertMessage(String message){
    	PrintWriter write=null;
    	try {
			write=new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(message);
    	write.println(message);
    	write.flush();
    }
}




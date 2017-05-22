/**
 * 
 * author@jsxnh
 */

package com.jsxnh.smartqqclient;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserPanel extends JFrame{

	/*
	 * ��Ϣ��ʶ��
	 * */
	public final static Integer UpdateNicknameMessage=0,UpdateSignatureMessage=1,UpdateDatasMessage=2,
			                    DeletePacketMessage=3,AddPacketMessage=4,ModifyPacketMessage=5,
			                    ModifyRemarkMessage=6,MovePacketMessage=7,AddFriendMessage=8,
			                    AddFriendResultMessage=9,AgreeAddFriendMessage=10,DisagreeFriendMessage=11,
			                    FindUserByIdSuccessMessage=12,FindUserByNicknameSuccessMessage=13,
			                    FindUserByIdFailureMessage=14,FindUserByNicknameFailureMessage=15,
			                    ChatMessage=16;
	
	private User user;
	private Map<String, LinkedList<Friend>> friends=new HashMap<>();
	private Map<Integer, String> messages=new HashMap<Integer, String>();//��Ϣmap
	private Map<Integer, ChatPanel> chatpanels=new HashMap<>();
	private JTree friendsTree;//�����б���
	private DefaultMutableTreeNode root;
	private JScrollPane jsPanel;
	private Container con=this.getContentPane();
	private JLabel nickname,signature,weather;
	private JLabel headimg,headimglb,MSGLb;
	private Point point;
	private JButton close,min,searchbt,MSGBt;
	private JTextField searchtf;
	private JMenuBar modify;
	private Friend currentfriend;
	private String currentpacket;
	private DefaultMutableTreeNode currentnode;
	private JPopupMenu popupmenu;
	
	public JLabel getMsgLb(){
		return this.MSGLb;
	}
	
	public void injectMessage(Integer message,String json){
		messages.put(message, json);
	}
	
	public Map<Integer, ChatPanel> getchatpanels(){
		return this.chatpanels;
	}
	
	public User getUser(){
		return this.user;
	}
	
	public UserPanel(){
		
	}
	
	/*
	 * ��ʼ���û���������Ϣ
	 * */
	private void initUserandFriend(String str){
		JSONObject json=JSONObject.fromObject(str);
		JSONObject user=json.getJSONObject("user");
		this.user=new User();
		this.user.setUser_id((Integer)user.get("user_id"));
		this.user.setNickname(user.getString("nickname"));
		if(user.containsKey("signature")){
			this.user.setSignature(user.getString("signature"));
		}
		if(user.containsKey("name")){
			this.user.setName(user.getString("name"));
		}
		if(user.containsKey("age")){
			this.user.setAge(user.getInt("age"));
		}
		if(user.containsKey("message")){
			this.user.setMessage(user.getString("message"));
		}		
		this.user.setStatus(user.getInt("status"));
		JSONArray packets=user.getJSONArray("packets");
		for(int i=0;i<packets.size();i++){
			this.user.getPackets().add(packets.getString(i));
		}
		JSONArray friends=json.getJSONArray("friends");
		for(int i=0;i<friends.size();i++){
			JSONObject subpacket=friends.getJSONObject(i);
			Iterator it=subpacket.keys();
			String packetname=it.next().toString();
			JSONArray subfriends=subpacket.getJSONArray(packetname);
			LinkedList<Friend> friendslist=new LinkedList<>();
			for(int j=0;j<subfriends.size();j++){
				Friend friend=new Friend();
				friend.setNickname(subfriends.getJSONObject(j).getString("nickname"));
				friend.setUser_id(subfriends.getJSONObject(j).getInt("user_id"));
				if(subfriends.getJSONObject(j).containsKey("remarkname")){
					friend.setRemarkname(subfriends.getJSONObject(j).getString("remarkname"));
				}
				if(subfriends.getJSONObject(j).containsKey("signature")){
					friend.setSignature(subfriends.getJSONObject(j).getString("signature"));
				}				
				friend.setStatus(subfriends.getJSONObject(j).getInt("status"));
				friend.setPacket(packetname);
				friendslist.add(friend);
			}
			this.friends.put(packetname, friendslist);
		}
		
	}
	
	public void initRoot(){
		//��ʼ��������
		root=new DefaultMutableTreeNode();
		for(Map.Entry<String, LinkedList<Friend>> entry:friends.entrySet()){
			DefaultMutableTreeNode packetname=new DefaultMutableTreeNode(entry.getKey());
			for(Friend friend_:entry.getValue()){
				packetname.add(new DefaultMutableTreeNode(friend_));
			}
			root.add(packetname);
		}
	}
	
	public void lunch(String str){
		initUserandFriend(str);
		this.setLayout(null);
		this.setSize(284, 674);
		/*
		 * ���ƶ�
		 * */
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				point=e.getPoint();
			}
		});
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e){
				Point newpoint=e.getLocationOnScreen();
				setLocation(newpoint.x-point.x, newpoint.y-point.y);
			}
		});
		
		//�ǳ�
		nickname=new JLabel();
		nickname.setText(user.getNickname());
		nickname.setFont(new Font("΢���ź�",Font.BOLD,12));
		nickname.setForeground(Color.black);
		nickname.setBounds(79, 37, 80, 17);
		con.add(nickname);
		//ͷ��
		headimg=new JLabel(new ImageIcon(this.getClass().getResource("headimg2.jpg")));
		headimglb=new JLabel(new ImageIcon(this.getClass().getResource("headimgbg.png")));
		headimg.setBounds(11, 41, 61, 60);
		headimglb.setBounds(9, 39, 65, 65);
		con.add(headimg);
		con.add(headimglb);
		headimg.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				headimglb.setBorder(BorderFactory.createLineBorder(new Color(147,112,219),20));
			}
			public void mouseEntered(MouseEvent e) {
				headimglb.setBorder(BorderFactory.createLineBorder(new Color(199,21,133 ),20));
				}
		});
		//����ǩ��
		signature=new JLabel();
		signature.setText(user.getSignature());
		signature.setFont(new Font("΢���ź�",Font.PLAIN,12));
		signature.setForeground(Color.black);
		signature.setBounds(79, 54, 200, 20);
		con.add(signature);
		weather=new  JLabel();
		weather.setIcon(new ImageIcon(this.getClass().getResource("tianqi.png")));
		weather.setBounds(220, 30, 60, 50);
		con.add(weather);
		
		searchtf=new JTextField("�����˺ţ��ǳ�");
		searchtf.setBorder(null);
		searchtf.setFont(new Font("����",Font.PLAIN,14));
		searchtf.setForeground(Color.darkGray);
		searchtf.setBackground(new Color(248,248,255));
        searchtf.addMouseListener(new MouseAdapter() {	
			public void mouseEntered(MouseEvent e) {				
				searchtf.setBackground(Color.white);
				}
		});
        searchtf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchtf.setText("");   
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//searchtf.setText("�����˺ţ��ǳ�");
			}
		});
        searchbt=new JButton();
        searchbt.setIcon(new ImageIcon(this.getClass().getResource("search.png")));
        searchbt.setRolloverIcon(new ImageIcon(this.getClass().getResource("search_hover.png")));
        searchbt.setPressedIcon(new ImageIcon(this.getClass().getResource("search_press.png")));
        searchbt.setBorder(null);
        searchbt.setFocusPainted(false);
        searchbt.setContentAreaFilled(false);
        searchtf.setBounds(1, 108, 249, 33);
        searchbt.setBounds(250, 111, 22, 25);
        searchbt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String str=searchtf.getText();
				if(str.equals("")){
					return;
				}
				if(str.matches("\\d+")){
					SendtoServer.finduserById(Integer.parseInt(str));
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(FindUserByIdFailureMessage)){
						JOptionPane.showMessageDialog(UserPanel.this, "���˺�");
						messages.remove(FindUserByIdFailureMessage);
					}else{						
						new addFriendbyidFrame().lunch(messages.get(FindUserByIdSuccessMessage));
						messages.remove(FindUserByIdSuccessMessage);
					}
				}else{
					SendtoServer.finduserByName(str);
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(FindUserByNicknameFailureMessage)){
						JOptionPane.showMessageDialog(UserPanel.this, "���˺�");
						messages.remove(FindUserByNicknameFailureMessage);
					}else{
						new addFriendbynicknameFrame().lunch(messages.get(FindUserByNicknameSuccessMessage));
						messages.remove(FindUserByNicknameSuccessMessage);
					}					
				}
			}
		});
        con.add(searchtf);
        con.add(searchbt);
        
		jsPanel=new JScrollPane();
		jsPanel.setBorder(null);
		jsPanel.setBounds(2, 182, 278, 430);
		jsPanel.setVisible(true);		
		initRoot();
		DefaultTreeModel model=new DefaultTreeModel(root);
		friendsTree=new JTree();
		friendsTree.setVisible(true);
		friendsTree.setModel(model);
		//��ȡ��������������Ѷ���
		friendsTree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node=(DefaultMutableTreeNode)friendsTree.getLastSelectedPathComponent();
				if(node==null){
					return;
				}
				currentnode=node;
				if(node.getUserObject()==null){
					return;
				}
				if(node.getUserObject() instanceof Friend){
					currentfriend=(Friend)node.getUserObject();
				}else{
					currentpacket=(String)node.getUserObject();
				}
				
				
			}
		});
		jsPanel.setViewportView(friendsTree);
		friendsTree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e){
				if(e.isPopupTrigger()){
					popupmenu.show(friendsTree, e.getX(), e.getY());
				}
			}
			public void mouseReleased(MouseEvent e)
		   {
				if(e.isPopupTrigger()){
					popupmenu.show(friendsTree, e.getX(), e.getY());
				}
		   }
		});
		con.add(jsPanel);
		//�һ��˵�
		popupmenu=new JPopupMenu();
		popupmenu.setVisible(true);
		JMenuItem deletepacket=new JMenuItem("ɾ������");
		deletepacket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(friends.get(currentpacket).size()>0){
					JOptionPane.showMessageDialog(UserPanel.this, "��ɾ���յķ���");
					return;
				}
				SendtoServer.deletePacket(user.getUser_id(), currentpacket);
				synchronized (UserPanel.this) {
					try {
						UserPanel.this.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(messages.containsKey(DeletePacketMessage)){
					messages.remove(DeletePacketMessage);
					user.getPackets().remove(currentpacket);
					friends.remove(currentpacket);
					JOptionPane.showMessageDialog(UserPanel.this, "ɾ���ɹ�");
					root.remove(currentnode);
					friendsTree.setModel(new DefaultTreeModel(root));
					jsPanel.repaint();
				}
			}
		});
		JMenuItem addpacket=new JMenuItem("���ӷ���");
		addpacket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new addpacketFrame().lunch();
			}
		});
		JMenuItem modifypacket=new JMenuItem("�޸ķ���");
		//�޸ķ�������¼�
		modifypacket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new modifypacketFrame().lunch();
			}
			
		});
		JMenuItem movepacket=new JMenuItem("�ƶ�����");
		movepacket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new movepacketFrame().lunch();
			}
		});
		JMenuItem modifyremark=new JMenuItem("�޸ı�ע");
		modifyremark.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new modifyremarkFrame().lunch();
			}
		});
		JMenuItem friendmessage=new JMenuItem("�鿴����");
		friendmessage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new friendmessageFrame();
			}
		});
		JMenuItem chatwith=new JMenuItem("������Ϣ");
		chatwith.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChatPanel chatpanel=new ChatPanel();
				chatpanels.put(currentfriend.getUser_id(), chatpanel);
				chatpanel.lunch(UserPanel.this, currentfriend, "");
			}
		});
		JMenuItem shownickname=new JMenuItem("��ʾ�ǳ�");
		shownickname.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(Map.Entry<String, LinkedList<Friend>> entry:friends.entrySet()){
					LinkedList<Friend> friends_=entry.getValue();
					for(Friend item:friends_){
						item.setIsnickname(true);
					}
				}
				initRoot();
				friendsTree.setModel(new DefaultTreeModel(root));
				jsPanel.setViewportView(friendsTree);
				jsPanel.repaint();
				jsPanel.validate();
			}
		});
		JMenuItem showremark=new JMenuItem("��ʾ��ע");
		showremark.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(Map.Entry<String, LinkedList<Friend>> entry:friends.entrySet()){
					LinkedList<Friend> friends_=entry.getValue();
					for(Friend item:friends_){
						item.setIsnickname(false);
					}
				}
				initRoot();
				friendsTree.setModel(new DefaultTreeModel(root));
				jsPanel.setViewportView(friendsTree);
				jsPanel.repaint();
				jsPanel.validate();
			}
		});
		
		popupmenu.add(deletepacket);
		popupmenu.add(addpacket);
		popupmenu.add(modifypacket);
		popupmenu.add(movepacket);
		popupmenu.addSeparator();
		popupmenu.add(modifyremark);
		popupmenu.add(friendmessage);
		popupmenu.add(chatwith);
		popupmenu.addSeparator();
		popupmenu.add(shownickname);
		popupmenu.add(showremark);
		popupmenu.setInvoker(friendsTree);
		con.add(popupmenu);
		
		
		modify=new JMenuBar();		
		JMenu edit=new JMenu("�༭����");
		JMenuItem editnickname=new JMenuItem("�޸��ǳ�");
		JMenuItem editsignature=new JMenuItem("�޸�ǩ��");
		JMenuItem editdatas=new JMenuItem("�޸�����");
		editnickname.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new nicknameFrame().lunch();
			}
		});
		editsignature.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new signatureFrame().lunch();
			}
		});
		editdatas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new datasFrame().lunch();
			}
		});
		edit.add(editnickname);
		edit.add(editsignature);
		edit.add(editdatas);
		modify.add(edit);
		modify.setBounds(167, 0, 60, 18);
		modify.setBorder(null);
		con.add(modify);
		
		MSGLb=new JLabel();
		MSGLb.setFont(new Font("����",Font.PLAIN,14));
		MSGLb.setForeground(Color.white);
		MSGLb.setText("����Ϣ");
		MSGLb.setBounds(107, 0, 60, 18);
		MSGBt=new JButton();
		MSGBt.setBorder(null);
		MSGBt.setFocusPainted(false);
		MSGBt.setContentAreaFilled(false);
		MSGBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(messages.containsKey(AddFriendMessage)){
					new acceptaddfriendFrame().lunch(messages.get(AddFriendMessage));
					messages.remove(AddFriendMessage);
					MSGLb.setText("����Ϣ");
				}
				else if(messages.containsKey(AgreeAddFriendMessage)){
					if(!messages.get(AgreeAddFriendMessage).equals("��ӳɹ�")){
						JSONObject addfriend=JSONObject.fromObject(messages.get(AgreeAddFriendMessage)).getJSONObject("agreeaddfriend");
						Integer user_id=addfriend.getInt("user2_id");
						JOptionPane.showMessageDialog(UserPanel.this, String.valueOf(user_id)+"ͬ�����");
						Friend newFriend=new Friend();
						newFriend.setUser_id(user_id);
						newFriend.setNickname(addfriend.getString("nickname2"));
						if(addfriend.containsKey("signature2")){
							newFriend.setSignature(addfriend.getString("signature2"));
						}						
						newFriend.setStatus(addfriend.getInt("status2"));
						newFriend.setPacket(addfriend.getString("packet1_name"));
						friends.get(newFriend.getPacket()).add(newFriend);
						initRoot();
						friendsTree.setModel(new DefaultTreeModel(root));
						jsPanel.setViewportView(friendsTree);
						jsPanel.repaint();
						jsPanel.validate();
						messages.remove(AgreeAddFriendMessage);
						MSGLb.setText("����Ϣ");
					}
				}
				else if(messages.containsKey(DisagreeFriendMessage)){
					if(!messages.get(DisagreeFriendMessage).equals("�ܾ��ɹ�")){
						JSONObject disagreejson=JSONObject.fromObject(messages.get(DisagreeFriendMessage)).getJSONObject("disagreeaddfriend");
						Integer user_id=disagreejson.getInt("user2_id");
						JOptionPane.showMessageDialog(UserPanel.this, String.valueOf(user_id)+"�ܾ����");
						messages.remove(DisagreeFriendMessage);
						MSGLb.setText("����Ϣ");
					}
				}
				else if(messages.containsKey(ChatMessage)){
					JSONObject message=JSONObject.fromObject(messages.get(ChatMessage));					
					ChatPanel chatpanel=new ChatPanel();
					chatpanels.put(message.getInt("user1_id"), chatpanel);
					chatpanel.lunch(UserPanel.this, null, message.toString());			
					messages.remove(ChatMessage);
					MSGLb.setText("����Ϣ");
				}
			}
		});
		MSGBt.setBounds(107, 0, 60, 18);
		con.add(MSGLb);
		con.add(MSGBt);
		
		close=new JButton();
		close.setIcon(new ImageIcon(this.getClass().getResource("Mainclose.png")));
		close.setRolloverIcon(new ImageIcon(this.getClass().getResource("Mainclose_hover.png")));
		close.setPressedIcon(new ImageIcon(this.getClass().getResource("Mainclose_press.png")));
		close.setBorder(null);
		close.setFocusPainted(false);
		close.setContentAreaFilled(false);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SendtoServer.logout(user.getUser_id());
			    UserPanel.this.dispose();				
			}
		});
		min=new JButton();
		min.setIcon(new ImageIcon(this.getClass().getResource("Mainmin.png")));
		min.setRolloverIcon(new ImageIcon(this.getClass().getResource("Mainmin_hover.png")));
		min.setPressedIcon(new ImageIcon(this.getClass().getResource("Mainmin_press.png")));
		min.setBorder(null);
		min.setToolTipText("��С��");
		min.setFocusPainted(false);
		min.setContentAreaFilled(false);
		min.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		min.setBounds(227,-2 ,28, 20);
		close.setBounds(255, -2, 28, 20);
		con.add(close);
		con.add(min);
		backgournd bg=new backgournd();
		bg.setImage(this.getToolkit().getImage(this.getClass().getResource("mainbg.png")));
		bg.setBounds(0, 0, 284, 674);
		con.add(bg);
		this.setIconImage(this.getToolkit().getImage(this.getClass().getResource("title.png")));
		this.setUndecorated(true);
//		this.setAlwaysOnTop(true);
		this.setVisible(true);
		
	}
	
	public void notifyall(Integer messageid,String str){
		synchronized (this) {
			this.messages.put(messageid, str);
			notifyAll();
		}
	}
	
	/*
	 * �޸��ǳ����
	 */
	private class nicknameFrame extends JFrame{
		private JTextField subnickname;
		private JButton confirm;
		private Container container=this.getContentPane();
		public nicknameFrame(){
			
		}		
		public void lunch(){
			this.setLayout(null);
			this.setSize(300, 150);
			subnickname=new JTextField(1000);
			subnickname.setBorder(BorderFactory.createLineBorder(Color.blue));
			confirm=new JButton("ȷ���޸�");
			subnickname.setBounds(1, 20, 174, 28);
			confirm.setBounds(181, 20, 86, 28);	
			confirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String text=subnickname.getText();
					if(text.equals("")){
						JOptionPane.showMessageDialog(nicknameFrame.this, "�ǳƲ���Ϊ��");
						return;
					}
					SendtoServer.updateNickname(user.getUser_id(), text);
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}					
					if(messages.containsKey(UpdateNicknameMessage)){
						user.setNickname(text);
						nickname.setText(text);
						JOptionPane.showMessageDialog(nicknameFrame.this, "�޸ĳɹ�");
						messages.remove(UpdateNicknameMessage);
					}
				}
			});
			container.add(subnickname);
			container.add(confirm);
			this.setVisible(true);
		}
	}
	
	/**
	 * �޸ĸ���ǩ�����
	 *
	 */
	private class signatureFrame extends JFrame{
		private JTextField subsignature;
		private JButton confirm;
		private Container container=this.getContentPane();
		public signatureFrame(){
			
		}		
		public void lunch(){
			this.setLayout(null);
			this.setSize(300, 150);
			subsignature=new JTextField(1000);
			subsignature.setBorder(BorderFactory.createLineBorder(Color.blue));
			confirm=new JButton("ȷ���޸�");
			subsignature.setBounds(1, 20, 174, 28);
			confirm.setBounds(181, 20, 86, 28);	
			confirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String text=subsignature.getText();
					SendtoServer.updateSignature(user.getUser_id(), text);
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}					
					if(messages.containsKey(UpdateSignatureMessage)){
						user.setSignature(text);
						signature.setText(text);
						JOptionPane.showMessageDialog(signatureFrame.this, "�޸ĳɹ�");
						messages.remove(UpdateSignatureMessage);
					}
				}
			});
			container.add(subsignature);
			container.add(confirm);
			this.setVisible(true);
		}
	}
	
	/*
	 * �޸���Ϣ���
	 * */
	
	private class datasFrame extends JFrame{
		private JLabel namelabel,agelabel,messagelabel;
		private JTextField nametf,agetf;
		private JTextArea messagetf;
		private JButton confirm;
		private Container container=this.getContentPane();
		public datasFrame(){
			
		}
		public void lunch(){
			setLayout(null);
			setSize(350, 280);
			Font font=new Font("����",Font.BOLD,19);
			namelabel=new JLabel("��ʵ����");
			namelabel.setFont(font);
			nametf=new JTextField(1000);
			if(user.getName()!=null){
				nametf.setText(user.getName());
			}
			nametf.setBorder(BorderFactory.createLineBorder(Color.blue));
			namelabel.setBounds(20, 20, 86, 28);
			nametf.setBounds(110, 20, 174, 28);
			container.add(namelabel);
			container.add(nametf);
			agelabel=new JLabel("��    ��");
			agelabel.setFont(font);
			agetf=new JTextField(1000);
			if(user.getAge()!=null){
				agetf.setText(String.valueOf(user.getAge()));
			}
			agetf.setBorder(BorderFactory.createLineBorder(Color.blue));
			agelabel.setBounds(20, 53, 86, 28);
			agetf.setBounds(110, 53, 174, 28);
			container.add(agelabel);
			container.add(agetf);
			messagelabel=new JLabel("��ע��Ϣ");
			messagelabel.setFont(font);
			messagetf=new JTextArea();
			messagetf.setLineWrap(true);
			if(user.getMessage()!=null){
				messagetf.setText(user.getMessage());
			}
			messagelabel.setBounds(20, 86, 86, 28);
			messagetf.setBounds(110, 86, 174, 84);
			container.add(messagelabel);
			container.add(messagetf);
			confirm=new JButton("ȷ���޸�");
			confirm.setBounds(120, 180, 100, 30);
			confirm.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String namestr=nametf.getText();
					String agestr=agetf.getText();
					String messagestr=messagetf.getText();
					if(!agestr.matches("\\d+")){
					    JOptionPane.showMessageDialog(datasFrame.this, "����Ϊ����");
					    return;
					}
					SendtoServer.updateDatas(user.getUser_id(), namestr, Integer.parseInt(agestr), messagestr);
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(UpdateDatasMessage)){
						user.setName(namestr);
						user.setAge(Integer.parseInt(agestr));
						user.setMessage(messagestr);
						messages.remove(UpdateDatasMessage);
						JOptionPane.showMessageDialog(datasFrame.this, "�޸ĳɹ�");
					}
				}
			});
			container.add(confirm);
			this.setVisible(true);
		}
	}
	
	private class addpacketFrame extends JFrame{	
		private JTextField addpackettf;
		private JButton confirm;
		private Container container=this.getContentPane();
		public void lunch(){
			this.setLayout(null);
			this.setSize(300, 150);
			this.setTitle("��ӷ���");
			addpackettf=new JTextField(1000);
			addpackettf.setBorder(BorderFactory.createLineBorder(Color.blue));
			addpackettf.setBounds(1, 20, 124, 28);
			confirm=new JButton("ȷ�����");
			confirm.setBounds(181, 20, 86, 28);
			confirm.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String str=addpackettf.getText();
					if(str.equals("")){
						JOptionPane.showMessageDialog(UserPanel.this, "���������");
						return;
					}
					if(friends.containsKey(str)){
						JOptionPane.showMessageDialog(UserPanel.this, "�����벻ͬ����");
						return;
					}
					SendtoServer.addPacket(user.getUser_id(), str);
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(AddPacketMessage)){
						addpacketFrame.this.dispose();
						JOptionPane.showMessageDialog(UserPanel.this, "��ӳɹ�");
						messages.remove(AddPacketMessage);
						friends.put(str, new LinkedList<Friend>());
						user.getPackets().add(str);
						root.add(new DefaultMutableTreeNode(str));
						friendsTree.setModel(new DefaultTreeModel(root));
						jsPanel.setViewportView(friendsTree);
						jsPanel.repaint();
						jsPanel.validate();
						
					}
				}
			});
			container.add(addpackettf);
			container.add(confirm);
			this.setVisible(true);
		}
	}
	
	private class modifypacketFrame extends JFrame{
		private JTextField packettf;
		private JButton confirm;
		private Container container=this.getContentPane();
		public void lunch(){
			this.setLayout(null);
			this.setSize(300, 150);
			this.setTitle("�޸ķ���");
			packettf=new JTextField(1000);
			packettf.setBorder(BorderFactory.createLineBorder(Color.blue));
			packettf.setBounds(1, 20, 174, 28);
			packettf.setText(currentpacket);
			confirm=new JButton("ȷ���޸�");
			confirm.setBounds(181, 20, 86, 28);
			confirm.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String str=packettf.getText();
					if(packettf.getText().equals("")){
						JOptionPane.showMessageDialog(UserPanel.this, "��Ϊ��");
						return;
					}
					SendtoServer.modifyPacket(user.getUser_id(), currentpacket, packettf.getText());
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(ModifyPacketMessage)){
						JOptionPane.showMessageDialog(UserPanel.this, "�޸ĳɹ�");
						messages.remove(ModifyPacketMessage);
						user.getPackets().remove(currentfriend);
						user.getPackets().add(str);
						LinkedList<Friend> friends_=friends.get(currentpacket);
						friends.remove(currentpacket);
						friends.put(str, friends_);
						currentnode.setUserObject(str);
						currentpacket=str;
						jsPanel.repaint();
					}
				}
			});
			container.add(packettf);
			container.add(confirm);
			setVisible(true);
		}
	}
	
	private class friendmessageFrame extends JFrame{
		private JLabel nicknamelb,nicknametextlb,user_idlb,user_idtextlb,
		               signaturelb,signaturetextlb,statuslb,statustextlb,
		               remarklb,remarktextlb;
		private Container container=this.getContentPane();
		public friendmessageFrame(){
			setSize(330, 280);
			setLayout(null);
			Font font=new Font("����",Font.BOLD,19);
			nicknamelb=new JLabel("��    ��");
			nicknamelb.setFont(font);
			nicknametextlb=new JLabel();
			nicknametextlb.setFont(font);
			nicknametextlb.setText(currentfriend.getNickname());
			nicknamelb.setBounds(50, 50, 86, 28);
			nicknametextlb.setBounds(160, 50, 200, 28);
			container.add(nicknamelb);
			container.add(nicknametextlb);
			user_idlb=new JLabel("��    ��");
			user_idlb.setFont(font);
			user_idtextlb=new JLabel();
			user_idtextlb.setFont(font);
			user_idtextlb.setText(String.valueOf(currentfriend.getUser_id()));
			user_idlb.setBounds(50, 88, 86, 28);
			user_idtextlb.setBounds(160, 88, 200, 28);
			container.add(user_idlb);
			container.add(user_idtextlb);
			signaturelb=new JLabel("����ǩ��");
			signaturelb.setFont(font);
			signaturetextlb=new JLabel();
			signaturetextlb.setFont(font);
			if(currentfriend.getSignature()!=null){
				signaturetextlb.setText(currentfriend.getSignature());
			}
			signaturelb.setBounds(50, 121, 86, 28);
			signaturetextlb.setBounds(160, 121, 200, 28);
			container.add(signaturelb);
			container.add(signaturetextlb);
			statuslb=new JLabel("״    ̬");
			statuslb.setFont(font);
			statustextlb=new JLabel();
			statustextlb.setFont(font);
			if(currentfriend.getStatus().equals(1)){
				statustextlb.setText("����");
			}else{
				statustextlb.setText("����");
			}
			statuslb.setBounds(50, 154, 86, 28);
			statustextlb.setBounds(160, 154, 200, 28);
			remarklb=new JLabel("��    ע");
			remarklb.setFont(font);
			remarktextlb=new JLabel();
			remarktextlb.setFont(font);
			if(currentfriend.getRemarkname()!=null){
				remarktextlb.setText(currentfriend.getRemarkname());
			}
			remarklb.setBounds(50, 187, 86, 28);
			remarktextlb.setBounds(160, 187,200, 28);			
			container.add(statuslb);
			container.add(statustextlb);
			container.add(remarklb);
			container.add(remarktextlb);
			setVisible(true);
			
		}
	}
	
	private class modifyremarkFrame extends JFrame{
		private JTextField remarktf;
		private JButton confirm;
		public void lunch(){
			this.setSize(300, 150);
			this.setLayout(null);
			this.setTitle("�޸ı�ע");
			remarktf=new JTextField(1000);
			remarktf.setBorder(BorderFactory.createLineBorder(Color.black));
			if(currentfriend.getRemarkname()!=null){
				remarktf.setText(currentfriend.getRemarkname());
			}
			confirm=new JButton("ȷ���޸�");
			remarktf.setBounds(1, 20, 174, 28);
			confirm.setBounds(181, 20, 86, 28);
			confirm.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String str=remarktf.getText();
					if(remarktf.getText().equals("")){
						JOptionPane.showMessageDialog(modifyremarkFrame.this, "����д��ע");
						return;
					}
					SendtoServer.modifyRemark(user.getUser_id(), currentfriend.getUser_id(),str );
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(ModifyRemarkMessage)){
						messages.remove(ModifyRemarkMessage);
						JOptionPane.showMessageDialog(UserPanel.this, "�޸ĳɹ�");
						LinkedList<Friend> friends_=friends.get(currentfriend.getPacket());
						for(Friend item:friends_){
							if(item.equals(currentfriend)){
								item.setRemarkname(str);
							}
						}
						initRoot();
						friendsTree.setModel(new DefaultTreeModel(root));
						jsPanel.setViewportView(friendsTree);
						jsPanel.repaint();
						jsPanel.validate();
					}
				}
			});
			this.getContentPane().add(remarktf);
			this.getContentPane().add(confirm);
			this.setVisible(true);
		}
	}
	
	private class movepacketFrame extends JFrame{
		private JComboBox<String> packetbox;
		private JButton confirm;
		private Container container=this.getContentPane();
		public void lunch(){
			setSize(250, 150);
			setLayout(null);
			setTitle("�ƶ�����");
			String[] packets=user.getPackets().toArray(new String[0]);
			packetbox=new JComboBox<>(packets);
			packetbox.setBounds(1, 20, 86, 28);
			confirm=new JButton("ȷ���ƶ�");
			confirm.setBounds(100, 20, 86, 28);
			confirm.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String packet_=(String) packetbox.getSelectedItem();
					String oldpacket=currentfriend.getPacket();
					SendtoServer.movePacket(user.getUser_id(), currentfriend.getUser_id(), currentfriend.getPacket(), packet_);
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(MovePacketMessage)){
						messages.remove(MovePacketMessage);
						JOptionPane.showMessageDialog(UserPanel.this, "�ƶ��ɹ�");
						friends.get(oldpacket).remove(currentfriend);
						currentfriend.setPacket(packet_);
						friends.get(packet_).add(currentfriend);
						initRoot();
						friendsTree.setModel(new DefaultTreeModel(root));
						jsPanel.setViewportView(friendsTree);
						jsPanel.repaint();
						jsPanel.validate();
					}
				}
			});
			container.add(packetbox);
			container.add(confirm);
			setVisible(true);
		}
	}
	
	private class addFriendbyidFrame extends JFrame{
		private JLabel user_idlb,user_idtextlb,nicknamelb,nicknametextlb;
		private JButton add;
		private Container container=this.getContentPane();
		public void lunch(String json){
			JSONObject userjson=JSONObject.fromObject(json);
			Integer user_id=userjson.getInt("user_id");
			String nickanme=userjson.getString("nickname");
			setLayout(null);
			setSize(300, 300);
			Font font=new Font("����",Font.BOLD,19);
			user_idlb=new JLabel("��   ��");
			user_idlb.setFont(font);
			user_idtextlb=new JLabel();
			user_idtextlb.setFont(font);
			user_idtextlb.setText(String.valueOf(user_id));
			user_idlb.setBounds(40, 40, 86, 28);
			user_idtextlb.setBounds(130, 40, 150, 28);
			container.add(user_idlb);
			container.add(user_idtextlb);
			nicknamelb=new JLabel("��    ��");
			nicknamelb.setFont(font);
			nicknametextlb=new JLabel();
			nicknametextlb.setFont(font);
			nicknametextlb.setText(nickanme);
			nicknamelb.setBounds(40, 73, 86, 28);
			nicknametextlb.setBounds(130, 73, 150, 28);
			container.add(nicknamelb);
			container.add(nicknametextlb);
			add=new JButton("ȷ�����");
			add.setBounds(80, 120, 120, 28);
			add.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new addFriendFrame().lunch(user_id);
				}
			});
			container.add(add);
			setVisible(true);
		}
	}
	
	private class addFriendbynicknameFrame extends JFrame{
		private JLabel[] user_idtextlb;
		private JLabel[] nicknametextlb;
		private JButton[] confirm;
		private JLabel user_idlb,nicknamelb;
		private Container container=this.getContentPane();
		public void lunch(String json){
			JSONArray usersjson=JSONArray.fromObject(json);
			Integer size=usersjson.size();
			setSize(500, 33*size+100);
			setLayout(null);
			Font font=new Font("����",Font.BOLD,19);
			
			user_idlb=new JLabel("��      ��");
			user_idlb.setFont(font);
			nicknamelb=new JLabel("��      ��");
			nicknamelb.setFont(font);
			user_idlb.setBounds(40, 40, 120, 28);
			nicknamelb.setBounds(165, 40, 150, 28);
			container.add(user_idlb);
			container.add(nicknamelb);
			user_idtextlb=new JLabel[size];
			nicknametextlb=new JLabel[size];
			confirm=new JButton[size];
			for(int i=0;i<size;i++){
				JSONObject item=usersjson.getJSONObject(i);
				user_idtextlb[i]=new JLabel();
				user_idtextlb[i].setText(String.valueOf(item.getInt("user_id")));
				user_idtextlb[i].setFont(font);
				user_idtextlb[i].setBounds(40, 40+33*i, 120,28);
				nicknametextlb[i]=new JLabel();
				nicknametextlb[i].setText(item.getString("nickname"));
				nicknametextlb[i].setFont(font);
				nicknametextlb[i].setBounds(165, 40+33*i, 150, 28);
				confirm[i]=new JButton("ȷ�����");
				confirm[i].setBounds(320, 40+33*i, 120, 28);
				confirm[i].addActionListener(new MyactionListener());
				container.add(user_idtextlb[i]);
				container.add(nicknametextlb[i]);
				container.add(confirm[i]);
			}
			setVisible(true);
		}
		
		public class MyactionListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<confirm.length;i++){
					if(e.getSource()==confirm[i]){
						new addFriendFrame().lunch(Integer.parseInt(user_idtextlb[i].getText()));
						break;
					}
				}
			}			
		}
	}
	
	private class addFriendFrame extends JFrame{
		private JComboBox<String> packetbox;
		private JButton confirm;
		private Container container=this.getContentPane();
		public void lunch(Integer user_id){
			setSize(250, 150);
			setLayout(null);
			setTitle("��Ӻ���");
			String[] packets=user.getPackets().toArray(new String[0]);
			packetbox=new JComboBox<>(packets);
			packetbox.setBounds(1, 20, 86, 28);
			confirm=new JButton("ȷ�����");
			confirm.setBounds(100, 20, 86, 28);
			confirm.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String packet_=(String) packetbox.getSelectedItem();
					SendtoServer.addFriend(user.getUser_id(), user_id, packet_);
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(AddFriendResultMessage)){
						JOptionPane.showMessageDialog(UserPanel.this, messages.get(AddFriendResultMessage));
						messages.remove(AddFriendResultMessage);
					}
				}
			});
			container.add(packetbox);
			container.add(confirm);
			setVisible(true);
		}
	}
	
	private class acceptaddfriendFrame extends JFrame{
		private JLabel user_idlb,user_idtextlb;
		private JButton agree,disagree;
		private Container container=this.getContentPane();
		
		public void lunch(String json){
			JSONObject jobject=JSONObject.fromObject(json);
			Integer  user_id=jobject.getInt("user1_id");
			setLayout(null);
			setSize(250, 150);
			setTitle("��Ӻ���");
			Font font=new Font("����",Font.BOLD,19);
			user_idlb=new JLabel("��  ��");
			user_idlb.setFont(font);
			user_idtextlb=new JLabel();
			user_idtextlb.setFont(font);
			user_idtextlb.setText(String.valueOf(user_id));
			user_idlb.setBounds(20, 20, 86, 28);
			user_idtextlb.setBounds(110, 20, 174, 18);
			agree=new JButton("ͬ�����");
			agree.setBounds(30, 53, 80, 28);
			agree.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new agreeaddfriendFrame().lunch(json);
				}
			});
			disagree=new JButton("�ܾ����");
			disagree.setBounds(110, 53, 80, 28);
			disagree.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					SendtoServer.disagreeAddFriend(user_id,jobject.getInt("user2_id"));
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(DisagreeFriendMessage)){
						if(messages.get(DisagreeFriendMessage).equals("�ܾ��ɹ�")){
							JOptionPane.showMessageDialog(UserPanel.this, "�ܾ��ɹ�");
							messages.remove(DisagreeFriendMessage);
						}
					}
				}
			});
			container.add(user_idlb);
			container.add(user_idtextlb);
			container.add(agree);
			container.add(disagree);
			setVisible(true);
		}
	}
	
	private class agreeaddfriendFrame extends JFrame{
		private JComboBox<String> packetbox;
		private JButton confirm;
		private Container container=this.getContentPane();
		public void lunch(String  json){
			JSONObject userjson=JSONObject.fromObject(json);
			setSize(250, 150);
			setLayout(null);
			setTitle("��Ӻ���");
			String[] packets=user.getPackets().toArray(new String[0]);
			packetbox=new JComboBox<>(packets);
			packetbox.setBounds(1, 20, 86, 28);
			confirm=new JButton("ȷ�����");
			confirm.setBounds(100, 20, 86, 28);
			confirm.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String packet_=(String) packetbox.getSelectedItem();
					SendtoServer.agreeAddFriend(userjson.getInt("user1_id"),userjson.getInt("user2_id"),userjson.getString("packetname"),
							                packet_);
					synchronized (UserPanel.this) {
						try {
							UserPanel.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(AgreeAddFriendMessage)){
						if(messages.get(AgreeAddFriendMessage).equals("��ӳɹ�")){
							messages.remove(AgreeAddFriendMessage);
							JOptionPane.showMessageDialog(UserPanel.this, "��ӳɹ�");
							Friend friend_=new Friend();
							friend_.setUser_id(userjson.getInt("user1_id"));
							friend_.setNickname(userjson.getString("nickname1"));
							friend_.setStatus(userjson.getInt("status1"));
							friend_.setPacket(packet_);
							if(userjson.containsKey("signature1")){
								friend_.setSignature(userjson.getString("signature1"));
							}
							friends.get(friend_.getPacket()).add(friend_);
							initRoot();
							friendsTree.setModel(new DefaultTreeModel(root));
							jsPanel.setViewportView(friendsTree);
							jsPanel.repaint();
							jsPanel.validate();							
						}
					}
				}
			});
			container.add(packetbox);
			container.add(confirm);
			setVisible(true);
		}
	}
		
	private class backgournd extends JPanel{
		private Image image;
		public backgournd(){
			setOpaque(false);
			setLayout(null);
		}
		public void setImage(Image image){
			this.image=image;
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			if(image!=null){
				g.drawImage(image, 0, 0,this);
			}
		}
	}	
}

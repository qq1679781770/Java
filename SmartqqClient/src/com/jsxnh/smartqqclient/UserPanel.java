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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserPanel extends JFrame{

	/*
	 * ��Ϣ��ʶ��
	 * */
	public final static Integer UpdateNicknameMessage=0,UpdateSignatureMessage=1,UpdateDatasMessage=2,
			                    DeletePacketMessage=3,AddPacketMessage=4,ModifyPacketMessage=5;
	
	private User user;
	private Map<String, LinkedList<Friend>> friends=new HashMap<>();
	private Map<Integer, String> messages=new HashMap<Integer, String>();//��Ϣmap
	private JTree friendsTree;//�����б���
	private DefaultMutableTreeNode root;
	private JScrollPane jsPanel;
	private Container con=this.getContentPane();
	private JLabel nickname,signature,weather;
	private JLabel headimg,headimglb;
	private Point point;
	private JButton close,min;
	private JMenuBar modify;
	private Friend currentfriend;
	private String currentpacket;
	private DefaultMutableTreeNode currentnode;
	private JPopupMenu popupmenu;
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
		
		//������Ϣ��Ŀ¼
		root=new DefaultMutableTreeNode("");
		jsPanel=new JScrollPane();
		jsPanel.setBorder(null);
		jsPanel.setBounds(2, 182, 278, 430);
		jsPanel.setVisible(true);
		//��ʼ��������
		for(Map.Entry<String, LinkedList<Friend>> entry:friends.entrySet()){
			DefaultMutableTreeNode packetname=new DefaultMutableTreeNode(entry.getKey());
			for(Friend friend_:entry.getValue()){
				packetname.add(new DefaultMutableTreeNode(friend_));
			}
			root.add(packetname);
		}
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
		
		JMenuItem addpacket=new JMenuItem("���ӷ���");
		JMenuItem modifypacket=new JMenuItem("�޸ķ���");
		//�޸ķ�������¼�
		modifypacket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new JFrame(){
					private JTextField packettf;
					private JButton confirm;
					private Container container=this.getContentPane();
					public void lunch(){
						this.setLayout(null);
						this.setSize(300, 150);
						packettf=new JTextField(1000);
						packettf.setBorder(BorderFactory.createLineBorder(Color.blue));
						packettf.setBounds(1, 20, 174, 28);
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
									jsPanel.repaint();
								}
							}
						});
						container.add(packettf);
						container.add(confirm);
						setVisible(true);
					}
				}.lunch();
			}
			
		});
		JMenuItem friendmessage=new JMenuItem("�鿴����");
		JMenuItem chatwith=new JMenuItem("������Ϣ");
		popupmenu.add(deletepacket);
		popupmenu.add(addpacket);
		popupmenu.add(modifypacket);
		popupmenu.addSeparator();
		popupmenu.add(friendmessage);
		popupmenu.add(chatwith);
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

package com.jsxnh.smartqqclient;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.sf.json.JSONObject;

public class ChatPanel extends JFrame{

	private static final long serialVersionUID = 1L;
	private Integer user_id;//朋友id
	private Friend friend;
	private UserPanel userpanel;
	private JButton min,close,sendmessagebt;
	private JLabel lbn1[]=new JLabel[6];
	private String lbn1_str[]={"shexiang","yuyin","wjcs","taolun","yczm","pmfx"};
	private JLabel lbn2[]=new JLabel[9];
	private String lbn2_str[]={"ziti","biaoqing","mfbq","pmdd","yyxx","dgnsr","fstp","diange","jietu"};
	private JLabel title_img,user_signature,friendqqshow,myqqshow,user_nickname;
	private JPanel pane1,pane2;
	private JTextArea showmessage;
	private JTextArea sendmessage;
	private Point point;
	private Font font=new Font("微软雅黑",Font.PLAIN,12);
	private Container con=this.getContentPane();
	
	/**
	 * 
	 */
	
	public void appendMessage(JSONObject json){
		showmessage.append(friend.getNickname()+"说: "+json.get("send_time")+"\n");
		showmessage.append(json.getString("content")+"\n");
	}

	public void lunch(UserPanel u,Friend f,String json){
		JSONObject message=null;
		if(json.equals("")){
			this.userpanel=u;
			this.friend=f;
			this.user_id=friend.getUser_id();
		}else{
			this.userpanel=u;
			message=JSONObject.fromObject(json);
			friend=new Friend();
			friend.setUser_id(message.getInt("user1_id"));
			friend.setNickname(message.getString("nickname1"));
			if(message.containsKey("signature1")){
				friend.setSignature(message.getString("signature1"));
			}
			this.user_id=message.getInt("user1_id");
		}
		this.setLayout(null);
		this.setSize(590, 620);
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension screensize=toolkit.getScreenSize();
		int screenwidth=(int)screensize.getWidth();
		int screenheight=(int)screensize.getHeight();
		this.setLocation(screenwidth/2-this.getWidth()/2, screenheight/2-this.getHeight()/2);
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
		/**
		min=new JButton();
		min.setIcon(new ImageIcon("images\\dialog\\min.png"));
		min.setPressedIcon(new ImageIcon("images\\dialog\\min_p.png"));
		min.setRolloverIcon(new ImageIcon("images\\dialog\\min_hover.png"));
		min.setToolTipText("最小化");
		min.setBorder(null);
		min.setFocusPainted(false);
		min.setContentAreaFilled(false);
		min.setBounds(464, 2, 25, 16);
        min.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 
				setExtendedState(JFrame.ICONIFIED);
			}
		});
        con.add(min);
        close=new JButton();
        close.setIcon(new ImageIcon("images\\dialog\\close.png"));
		close.setRolloverIcon(new ImageIcon("images\\dialog\\close_hover.png"));
		close.setPressedIcon(new ImageIcon("images\\dialog\\close_p.png"));
		close.setBorder(null);
		close.setFocusPainted(false);
		close.setContentAreaFilled(false);
		close.setBounds(520, 2, 33, 16);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userpanel.getchatpanels().remove(ChatPanel.this.user_id);
				ChatPanel.this.dispose();
			}
		});
		con.add(close);
		**/
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				userpanel.getchatpanels().remove(ChatPanel.this.user_id);
				ChatPanel.this.dispose();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				userpanel.getchatpanels().remove(ChatPanel.this.user_id);
				ChatPanel.this.dispose();
			}
		});
		sendmessagebt=new JButton();
		sendmessagebt.setIcon(new ImageIcon("images\\dialog\\fasong.png"));
		sendmessagebt.setRolloverIcon(new ImageIcon("images\\dialog\\fasong_hover.png"));
		sendmessagebt.setPressedIcon(new ImageIcon("images\\dialog\\fasong_p.png"));
		sendmessagebt.setBorder(null);
		sendmessagebt.setFocusPainted(false);
		sendmessagebt.setContentAreaFilled(false);
		sendmessagebt.setBounds(310, 503, 83, 24);
		sendmessagebt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String content=sendmessage.getText();
				if(content.equals("")){
					return;
				}
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date=df.format(new Date());
				SendtoServer.sendChat(userpanel.getUser().getUser_id(),ChatPanel.this.user_id, content,date);
				showmessage.append("我说: "+date+"\n");
				showmessage.append(content+"\n");
				sendmessage.setText("");
			}
		});
		con.add(sendmessagebt);
		
		friendqqshow=new JLabel(new ImageIcon("images\\dialog\\duifang.png"));
		friendqqshow.setBounds(405, 90, 143, 228);
		con.add(friendqqshow);
		myqqshow=new JLabel(new ImageIcon("images\\dialog\\ziji.png"));
		myqqshow.setBounds(405, 390, 143, 142);
		con.add(myqqshow);
		title_img=new JLabel(new ImageIcon("images\\dialog\\touxiang.png"));
		title_img.setBounds(11, 11, 44, 44);
		con.add(title_img);
		user_nickname=new JLabel();
		user_nickname.setBounds(56, 12, 300, 20);
		user_nickname.setFont(new Font("楷体",Font.BOLD,19));
		user_nickname.setText(friend.getNickname());
		con.add(user_nickname);
		user_signature=new JLabel();
		user_signature.setBounds(60,33,400,20);
		user_signature.setFont(new Font("楷体",Font.PLAIN,12));
		if(friend.getSignature()!=null){
			user_signature.setText(friend.getSignature());
		}
		con.add(user_signature);
		
		
		sendmessage=new JTextArea();
		sendmessage.setBorder(null);
		sendmessage.setLineWrap(true);
		JScrollPane jsa1=new JScrollPane(sendmessage, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane1=new JPanel();
		pane1.setLayout(new BorderLayout());
		pane1.add(jsa1);
		pane1.setBounds(5,410, 395, 90);
		con.add(pane1);
		showmessage=new JTextArea();
		showmessage.setBorder(null);
		showmessage.setLineWrap(true);
		showmessage.setFont(font);
		showmessage.setEditable(false);
		if(message!=null){
			appendMessage(message);
		}
		JScrollPane jsa2=new JScrollPane(showmessage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane2=new JPanel();
		pane2.setLayout(new BorderLayout());
		pane2.add(jsa2);
		pane2.setBounds(5, 92, 395, 285);
		con.add(pane2);
		for(int i=0;i<6;i++){
			lbn1[i]=new JLabel(new ImageIcon("images\\dialog\\"+lbn1_str[i]+".png"));
			lbn1[i].setBorder(null);
			lbn1[i].setBackground(new Color(255,255,255));
			lbn1[i].setBounds(11+40*i, 56, 34, 34);
			con.add(lbn1[i]);
		}
		for(int i=0;i<9;i++){
			lbn2[i]=new JLabel(new ImageIcon("images\\dialog\\"+lbn2_str[i]+".png"));
			lbn2[i].setBorder(null);
			lbn2[i].setBounds(3+30*i, 382, 20, 20);
			con.add(lbn2[i]);
		}
		
		backgournd bg=new backgournd();
		bg.setImage(this.getToolkit().getImage("images\\dialog\\dialogbg.png"));
		bg.setBounds(0, 0, 555, 535);
		con.add(bg);		
		this.setIconImage(this.getToolkit().getImage("images\\title.png"));
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.setVisible(true);		
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

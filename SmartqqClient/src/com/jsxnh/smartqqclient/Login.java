package com.jsxnh.smartqqclient;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Login extends JFrame{
 
	public final static Integer LoginSuccessMessage=0,LoginFailureMessage=1,RegisterSuccessMessage=2,
			                    RegisterFailureMessage=3,
			                    ModifyPasswordSuccessMessage=4,
			                    ModifyPasswordFailureMessage=5,FindProblemSuccessMessage=6,FindProblemFailureMessage=7;
	private JTextField userField;
	private JPasswordField passwordField;
	private JLabel headlb,headlbbg;
	private JButton login,register,findbackpassword,close,min;
	private Container container=this.getContentPane();
	private Point point;
	private Socket socket;
	private Map<Integer, String> messages=new HashMap<Integer, String>();
	private ReceivefromServer  rec;
	public Login(){
//		socket=new Socket("127.0.0.1",9001);
		try {
			socket=new Socket("127.0.0.1",9001);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		rec=new ReceivefromServer(socket, this);
		rec.start();
		SendtoServer.socket=socket;
//		sendtoserver.start();
	}
	
	public void lunch(){
		this.setSize(437,340);
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
		
		headlb=new JLabel(new ImageIcon("images\\headimg.png"));
		headlbbg=new JLabel(new ImageIcon("images\\headimgbg.png"));
		this.setLayout(null);
		headlb.setBounds(42, 197, 80, 79);
		headlbbg.setBounds(40, 195, 84,84);
		container.add(headlb);
		container.add(headlbbg);
		
		userField=new JTextField(1000);
		userField.setBorder(BorderFactory.createLineBorder(Color.black));
		userField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				userField.setBorder(BorderFactory.createLineBorder(Color.red));
			}
			public void mouseEntered(MouseEvent e){
				userField.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			public void mouseExited(MouseEvent e){
				userField.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		});
		
		passwordField=new JPasswordField(1000);
		passwordField.setBorder(BorderFactory.createLineBorder(Color.black));
		passwordField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==10){
					do_login();
				}
			}
		});
		passwordField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				passwordField.setBorder(BorderFactory.createLineBorder(Color.red));
			}
			public void mouseEntered(MouseEvent e){
				passwordField.setBorder(BorderFactory.createLineBorder(Color.blue));
			}
			public void mouseExited(MouseEvent e){
				passwordField.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		});
		userField.setFont(new Font("楷体",Font.BOLD,19));
		userField.setForeground(Color.black);
		userField.setToolTipText("请输入用户名");
		passwordField.setFont(new Font("楷体",Font.BOLD,19));
		passwordField.setForeground(Color.black);
		passwordField.setEchoChar('●');
		passwordField.setToolTipText("请输入密码");
		passwordField.requestFocus(true);
		userField.setBounds(140, 197,174, 28);
		passwordField.setBounds(140, 230, 174, 28);
		container.add(userField);
		container.add(passwordField);
		
		register=new JButton();
		register.setIcon(new ImageIcon("images\\zhuce_normal.png"));
		register.setRolloverIcon(new ImageIcon("images\\zhuce_hover.png"));
		register.setPressedIcon(new ImageIcon("images\\zhuce_press.png"));
		register.setBorder(null);
		register.setFocusPainted(false);
		register.setContentAreaFilled(false);
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Register().lunch();
			}
		});
		
		findbackpassword=new JButton();
		findbackpassword.setIcon(new ImageIcon("images\\mima_normal.png"));
		findbackpassword.setRolloverIcon(new ImageIcon("images\\mima_hover.png"));
		findbackpassword.setPressedIcon(new ImageIcon("images\\mima_press.png"));
		findbackpassword.setBorder(null);
		findbackpassword.setFocusPainted(false);
		findbackpassword.setContentAreaFilled(false);
		findbackpassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				new FindbackPassword().lunch();
			}
		});
		register.setBounds(330, 200, 51,16);
		findbackpassword.setBounds(330, 230, 51, 16);
		container.add(register);
		container.add(findbackpassword);
		
		JLabel loginlb=new JLabel("安 全 登 录");
		loginlb.setForeground(Color.white);
		loginlb.setFont(new Font("宋体",Font.BOLD,19));
		login=new JButton();
		login.setIcon(new ImageIcon("images\\button_blue_normal.png"));
		login.setRolloverIcon(new ImageIcon("images\\button_blue_hover.png"));
		login.setPressedIcon(new ImageIcon("images\\button_blue_press.png"));
		login.setBorder(null);
		login.setFocusPainted(false);
		login.setContentAreaFilled(false);
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				do_login();
			}
		});
		loginlb.setBounds(172, 292, 250, 40);
		login.setBounds(110, 288, 237, 48);
		container.add(loginlb);
		container.add(login);
		
		close=new JButton();
		close.setIcon(new ImageIcon("images\\close.png"));
		close.setRolloverIcon(new ImageIcon("images\\close_hover.png"));
		close.setPressedIcon(new ImageIcon("images\\close_press.png"));
		close.setBorder(null);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		min=new JButton();
		min.setIcon(new ImageIcon("images\\min.png"));
		min.setRolloverIcon(new ImageIcon("images\\min_hover.png"));
		min.setPressedIcon(new ImageIcon("images\\min_press.png"));
		min.setBorder(null);
		min.setToolTipText("最小化");
		min.setFocusPainted(false);
		min.setContentAreaFilled(false);
		min.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		min.setBounds(375, 5,27, 17);
		close.setBounds(400,5, 27, 19);
		container.add(min);
		container.add(close);
		backgournd bg=new backgournd();
		bg.setImage(this.getToolkit().getImage("images\\loginbg.png"));
		bg.setBounds(0, 0, 437, 340);
		container.add(bg);
		container.repaint();
		
		this.setIconImage(this.getToolkit().getImage("images\\title.png"));
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		setVisible(true);
	}
	
	private void do_login(){
		String id=userField.getText();
		char[] password=passwordField.getPassword();
		if(!id.matches("\\d+")){
			JOptionPane.showMessageDialog(this, "用户名为数字");
			return;
		}
		if(password.equals("")){
			JOptionPane.showMessageDialog(this, "密码不能为空");
			return;
		}
		SendtoServer.login(Integer.parseInt(id), String.valueOf(password));
		synchronized (this) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(messages.containsKey(LoginSuccessMessage)){
			//System.out.println(messages.get(LoginSuccessMessage));
			UserPanel userpanel=new UserPanel();
			userpanel.lunch(messages.get(LoginSuccessMessage));
			rec.setUser(userpanel);
			messages.remove(LoginSuccessMessage);
			setVisible(false);
		}else{
			JOptionPane.showMessageDialog(this, "用户名不存在或密码错误");
			messages.remove(LoginFailureMessage);
		}
	}
	
	public  void notifyall(Integer messageid,String str){
		synchronized (this) {
			messages.put(messageid, str);
			this.notifyAll();
		}
	}
	
	private class Register extends JFrame{
		private JTextField user_id,nickname,problem,answer,age,name;
		private JTextArea message;
		private JPasswordField password1,password2;
		private JButton register;
		private Container con=this.getContentPane();
		public Register() {
			
		}
		public void lunch(){
            this.setSize(550, 600);
            this.setLayout(null);
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
    		
    		Font font=new Font("楷体",Font.BOLD,19);
    		JLabel userlabel=new JLabel("账    号");
    		userlabel.setFont(font);
    		user_id=new JTextField(1000);
    		user_id.setBorder(BorderFactory.createLineBorder(Color.blue));
    		user_id.setFont(font);
    		userlabel.setBounds(120, 50, 86,28);
    		user_id.setBounds(210, 50, 174, 28);
    		con.add(userlabel);
    		con.add(user_id);
    		JLabel password1label=new JLabel("输入密码");
    		password1label.setFont(font);
    		password1=new JPasswordField(1000);
    		password1.setEchoChar('●');
    		password1.setBorder(BorderFactory.createLineBorder(Color.blue));
    		password1.setFont(font);
    		password1label.setBounds(120, 83, 86, 28);
    		password1.setBounds(210, 83, 174,28);
    		con.add(password1label);
    		con.add(password1);
    		JLabel password2label=new JLabel("重复密码");
    		password2label.setFont(font);
    		password2=new JPasswordField(1000);
    		password2.setEchoChar('●');
    		password2.setBorder(BorderFactory.createLineBorder(Color.blue));
    		password2.setFont(font);
    		password2label.setBounds(120, 116, 86, 28);
    		password2.setBounds(210, 116, 174,28);
    		con.add(password2label);
    		con.add(password2);
    		JLabel nicknamelabel=new JLabel("昵    称");
    		nicknamelabel.setFont(font);
    		nickname=new JTextField(1000);
    		nickname.setFont(font);
    		nickname.setBorder(BorderFactory.createLineBorder(Color.blue));
    		nicknamelabel.setBounds(120, 149, 86, 28);
    		nickname.setBounds(210, 149, 174, 28);
    		con.add(nicknamelabel);
    		con.add(nickname);
    		JLabel problemlabel=new JLabel("密保问题");
    		problemlabel.setFont(font);
    		problem=new JTextField(1000);
    		problem.setFont(font);
    		problem.setBorder(BorderFactory.createLineBorder(Color.blue));
    		problemlabel.setBounds(120, 182, 86, 28);
    		problem.setBounds(210, 182, 174, 28);
    		con.add(problemlabel);
    		con.add(problem);
    		JLabel answerlabel=new JLabel("密保答案");
    		answerlabel.setFont(font);
    		answer=new JTextField(1000);
    		answer.setFont(font);
    		answer.setBorder(BorderFactory.createLineBorder(Color.blue));
    		answerlabel.setBounds(120, 215, 86, 28);
    		answer.setBounds(210, 215,174, 28);
    		con.add(answerlabel);
    		con.add(answer);
    		JLabel namelabel=new JLabel("真实姓名");
    		namelabel.setFont(font);
    		name=new JTextField(1000);
    		name.setFont(font);
    		name.setBorder(BorderFactory.createLineBorder(Color.blue));
    		namelabel.setBounds(120, 248, 86, 28);
    		name.setBounds(210, 248, 174, 28);
    		con.add(namelabel);
    		con.add(name);
    		JLabel agelabel=new JLabel("年    龄");
    		agelabel.setFont(font);
    		age=new JTextField(1000);
    		age.setFont(font);
    		age.setBorder(BorderFactory.createLineBorder(Color.blue));
    		agelabel.setBounds(120, 281, 86, 28);
    		age.setBounds(210, 281, 174, 28);
    		con.add(agelabel);
    		con.add(age);
    		JLabel messagelabel=new JLabel("备注信息");
    		messagelabel.setFont(font);
    		message=new JTextArea();
    		message.setFont(font);
    		message.setBorder(BorderFactory.createLineBorder(Color.blue));
    		message.setLineWrap(true);
    		messagelabel.setBounds(120, 314, 86, 28);
    		message.setBounds(210, 314, 174,84);
    		con.add(messagelabel);
    		con.add(message);
    		
    		this.register=new JButton();
    		this.register.setIcon(new ImageIcon("images\\zhuce.png"));
    		this.register.setRolloverIcon(new ImageIcon("images\\zhuce_hover.jpg"));
    		this.register.setBorder(null);
    		this.register.setFocusPainted(false);
    		this.register.setContentAreaFilled(false);
    		this.register.setBounds(150, 433,200,50);
    		this.register.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(user_id.getText().equals("")||password1.getPassword().length==0||password2.getPassword().length==0||
							nickname.getText().equals("")||problem.getText().equals("")||answer.getText().equals("")||age.getText().equals("")||
							name.getText().equals("")||message.getText().equals("")){
						JOptionPane.showMessageDialog(Register.this, "请填写完整");
						return;
					}
					if(!String.valueOf(password1.getPassword()).equals(String.valueOf(password2.getPassword()))){
						JOptionPane.showMessageDialog(Register.this, "两次密码不相等");
						return;
					}
					if(!user_id.getText().matches("\\d+")){
						JOptionPane.showMessageDialog(Register.this, "用户id为数字");
						return;
					}
					if(!age.getText().matches("\\d+")){
						JOptionPane.showMessageDialog(Register.this,"年龄为数字");
						return;
					}
					SendtoServer.register(Integer.parseInt(user_id.getText()), String.valueOf(password1.getPassword()), nickname.getText(),
							problem.getText(),answer.getText(), Integer.parseInt(age.getText()),name.getText(), message.getText());
					synchronized (Login.this) {
						try {
							Login.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(RegisterSuccessMessage)){
						JOptionPane.showMessageDialog(Register.this, "注册成功");
						messages.remove(RegisterSuccessMessage);
					}else{
						JOptionPane.showMessageDialog(Register.this, "注册失败，已有id");
					}
				}
			});
    		con.add(this.register);
    		
    		JButton subclose=new JButton();
    		subclose.setIcon(new ImageIcon("images\\close.png"));
    		subclose.setRolloverIcon(new ImageIcon("images\\close_hover.png"));
    		subclose.setPressedIcon(new ImageIcon("images\\close_press.png"));
    		subclose.setBorder(null);
    		subclose.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    			    Register.this.dispose();
    			    Login.this.setVisible(true);    				
    			}
    		});
    		subclose.setBounds(523, 5, 27, 19);
    		con.add(subclose);
			backgournd bg=new backgournd();
			bg.setImage(this.getToolkit().getImage("images\\registerbg.jpg"));
			bg.setBounds(0, 0,550, 600);
			con.add(bg);
			con.repaint();
			this.setUndecorated(true);
			setVisible(true);
		}
	}
	
	private class FindbackPassword extends JFrame{
		
		private JTextField user_id,answer;
		private JPasswordField password1,password2;
		private JButton findproblem,modify;
		private JLabel problem;
		private Container con=this.getContentPane();
        public FindbackPassword(){
        	 
        }
         
        public void lunch(){
        	this.setSize(500, 400);
            this.setLayout(null);
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
    		
    		Font font=new Font("楷体",Font.BOLD,19);
    		JLabel userlabel=new JLabel("输入账号");
    		userlabel.setFont(font);
    		user_id=new JTextField(1000);
    		user_id.setFont(font);
    		user_id.setBorder(BorderFactory.createLineBorder(Color.blue));
    		findproblem=new JButton();
    		findproblem.setIcon(new ImageIcon("images\\findproblem.jpg"));
    		findproblem.setRolloverIcon(new ImageIcon("images\\findproblem_hover.jpg"));
    		findproblem.setBorder(null);
    		findproblem.setFocusPainted(false);
     		findproblem.setContentAreaFilled(false);
     		findproblem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!user_id.getText().matches("\\d+")){
						JOptionPane.showMessageDialog(FindbackPassword.this, "账号为数字");
						return;
					}
					SendtoServer.findProblem(Integer.parseInt(user_id.getText()));
					synchronized (Login.this) {
						try {
							Login.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(messages.containsKey(FindProblemSuccessMessage)){
						problem.setText(messages.get(FindProblemSuccessMessage)+"?");
						messages.remove(FindProblemSuccessMessage);
					}else{
						JOptionPane.showMessageDialog(FindbackPassword.this, "没有账号");
						problem.setText("");
						messages.remove(FindProblemFailureMessage);
					}					
				}
			});
    		userlabel.setBounds(100, 50, 86, 28);
    		user_id.setBounds(190, 50, 174, 28);
    		findproblem.setBounds(380,50,50,28);
    		con.add(userlabel);
    		con.add(user_id);
    		con.add(findproblem);
    		JLabel problemlabel=new JLabel("密保问题");
    		problemlabel.setFont(font);
    		problem=new JLabel();
    		problem.setFont(font);
    		problemlabel.setBounds(100, 83, 86, 28);
    		problem.setBounds(190, 83, 174, 28);
    		con.add(problemlabel);
    		con.add(problem);
    		JLabel answerlabel=new JLabel("输入答案");
    		answerlabel.setFont(font);
    		answer=new JTextField(1000);
    		answer.setFont(font);
    		answer.setBorder(BorderFactory.createLineBorder(Color.blue));
    		answerlabel.setBounds(100, 116, 86, 28);
    		answer.setBounds(190, 116, 174, 28);
    		con.add(answerlabel);
    		con.add(answer);
    		JLabel password1label=new JLabel("新密码");
    		password1label.setFont(font);
    		password1=new JPasswordField(1000);
    		password1.setFont(font);
    		password1.setEchoChar('●');
    		password1.setBorder(BorderFactory.createLineBorder(Color.blue));
    		password1label.setBounds(100, 149, 86, 28);
    		password1.setBounds(190, 149, 174, 28);
    		con.add(password1label);
    		con.add(password1);
    		JLabel password2label=new JLabel("确认密码");
    		password2label.setFont(font);
    		password2=new JPasswordField(1000);
    		password2.setFont(font);
    		password2.setEchoChar('●');
    		password2.setBorder(BorderFactory.createLineBorder(Color.blue));
    		password2label.setBounds(100,182 , 86, 28);
    		password2.setBounds(190, 182, 174, 28);
    		con.add(password2label);
    		con.add(password2);
    		
    		JLabel modifylb=new JLabel("修 改 密 码");
    		modifylb.setForeground(Color.white);
    		modifylb.setFont(new Font("宋体",Font.BOLD,19));
    		modify=new JButton();
    		modify.setIcon(new ImageIcon("images\\button_blue_normal.png"));
    		modify.setRolloverIcon(new ImageIcon("images\\button_blue_hover.png"));
    		modify.setPressedIcon(new ImageIcon("images\\button_blue_press.png"));
    		modify.setBorder(null);
    		modify.setFocusPainted(false);
    		modify.setContentAreaFilled(false);
    		modify.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {			
    				if(user_id.getText().equals("")||answer.getText().equals("")||password1.getPassword().length==0||
    						password2.getPassword().length==0){
    					JOptionPane.showMessageDialog(FindbackPassword.this, "请填写完整");
    					return;
    				}
    				if(!String.valueOf(password1.getPassword()).equals(String.valueOf(password2.getPassword()))){
    					JOptionPane.showMessageDialog(FindbackPassword.this, "密码不一致");
    					return;
    				}
    				SendtoServer.modifyPassword(Integer.parseInt(user_id.getText()), answer.getText(), String.valueOf(password1.getPassword()));
    				synchronized (Login.this) {
						try {
							Login.this.wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
    				if(messages.containsKey(ModifyPasswordSuccessMessage)){
    					JOptionPane.showMessageDialog(FindbackPassword.this, "修改成功");
    					messages.remove(ModifyPasswordSuccessMessage);
    				}else{
    					JOptionPane.showMessageDialog(FindbackPassword.this, "密保错误");
    					messages.remove(ModifyPasswordFailureMessage);
    				}
    			}
    		});
    		modifylb.setBounds(224, 230, 250, 40);
    		modify.setBounds(162, 226,237, 48);
    		con.add(modifylb);
    		con.add(modify);
    		
    		JButton subclose=new JButton();
    		subclose.setIcon(new ImageIcon("images\\close.png"));
    		subclose.setRolloverIcon(new ImageIcon("images\\close_hover.png"));
    		subclose.setPressedIcon(new ImageIcon("images\\close_press.png"));
    		subclose.setBorder(null);
    		subclose.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    			    FindbackPassword.this.dispose();
    			    Login.this.setVisible(true);    				
    			}
    		});
    		subclose.setBounds(473, 5, 27, 19);
    		con.add(subclose);
    		backgournd bg=new backgournd();
			bg.setImage(this.getToolkit().getImage("images\\registerbg.jpg"));
			bg.setBounds(0, 0,500, 400);
			con.add(bg);
			con.repaint();
			this.setUndecorated(true);
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
	public static void main(String[] args) {
		new Login().lunch();
	}
}

package com.lxj.kbms.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lxj.kbms.dao.customerdao;
import com.lxj.kbms.entities.Customer;

@SuppressWarnings("serial")
public class Login extends JFrame{

	private JTextField user;
	private JLabel userlb,pswlb;
	private JPasswordField psw;
	private JButton login,register,tourist;
	private Container con=this.getContentPane();
	
	public Login(){
		setLayout(null);
		setSize(550, 500);
		setTitle("水污染知识库信息系统");
		
		Font font=new Font("宋体",Font.BOLD,19);
		userlb=new JLabel("账    号");
		userlb.setFont(font);
		userlb.setBounds(40, 200, 86, 28);
		con.add(userlb);
		pswlb=new JLabel("密    码");
		pswlb.setFont(font);
		pswlb.setBounds(40, 260, 86, 28);
		con.add(pswlb);
		
		user=new JTextField(1000);
		user.setBounds(170,200,174,28);
		user.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		user.setForeground(Color.BLACK);
		user.setFont(new Font("宋体",Font.BOLD,14));
		con.add(user);
		psw=new JPasswordField();
		psw.setBounds(170, 260, 174, 28);
		psw.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		psw.setForeground(Color.BLACK);
		con.add(psw);
		login =new JButton();
		login.setIcon(new ImageIcon("images\\login.png"));
		login.setBounds(40, 330, 468, 71);
		login.setBorder(null);
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id=user.getText();
				String ps=String.valueOf(psw.getPassword());
				if(!id.matches("\\d+")){
					JOptionPane.showMessageDialog(Login.this, "账号为数字");
					return;
				}
				
				Customer user=new customerdao().login(Integer.parseInt(id), ps);
				if(user==null){
					JOptionPane.showMessageDialog(Login.this, "用户名不存在或密码错误");
					return;
				}
				new Main(user);
				Login.this.dispose();
			}
		});
		con.add(login);
		
		register =new JButton("用 户 注 册");
		register.setFont(font);
		register.setBackground(Color.gray);
		register.setBorder(null);
		register.setFocusPainted(false);
		register.setBounds(388, 200, 120, 30);
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Register(Login.this);
				Login.this.setVisible(false);
			}
		});
		con.add(register);
		tourist =new JButton("游 客 进 入");
		tourist.setBackground(Color.gray);
		tourist.setFont(font);
		tourist.setBorder(null);
		tourist.setFocusPainted(false);
		tourist.setBounds(388, 260, 120, 30);
		tourist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Main(null);
				Login.this.dispose();
			}
		});
		con.add(tourist);
		Background bg=new Background();
		bg.setImage(this.getToolkit().getImage("images\\loginbg.png"));
		bg.setBounds(-15, -15, 565, 515);
		con.add(bg);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Login();
	}
}

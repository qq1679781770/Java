package com.lxj.kbms.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lxj.kbms.dao.customerdao;

@SuppressWarnings("serial")
public class Register extends JFrame{

	private JLabel userlb,psw1lb,psw2lb;
	private JTextField user;
	private JPasswordField psw1,psw2;
	private JButton register;
	private Container con=this.getContentPane();
	
	public Register(Login login){
		setLayout(null);
		setTitle("注册");
		setSize(364,400);
		Font font=new Font("宋体",Font.BOLD,19);
		userlb=new JLabel("账    号");
		userlb.setFont(font);
		userlb.setBounds(40, 80, 86, 28);
		con.add(userlb);
		psw1lb=new JLabel("密    码");
		psw1lb.setFont(font);
		psw1lb.setBounds(40, 120, 86, 28);
		con.add(psw1lb);
		psw2lb=new JLabel("重复密码");
		psw2lb.setFont(font);
		psw2lb.setBounds(40, 160, 86, 28);
		con.add(psw2lb);
		
		user=new JTextField(1000);
		user.setBorder(BorderFactory.createLineBorder(Color.black));
		user.setBounds(150, 80, 174, 28);
		user.setFont(new Font("宋体",Font.BOLD,14));
		con.add(user);
		psw1=new JPasswordField();
		psw1.setBorder(BorderFactory.createLineBorder(Color.black));
		psw1.setBounds(150, 120, 174, 28);
		con.add(psw1);
		psw2=new JPasswordField();
		psw2.setBorder(BorderFactory.createLineBorder(Color.black));
		psw2.setBounds(150, 160, 174, 28);
		con.add(psw2);
		register =new JButton();
		register.setIcon(new ImageIcon("images\\register.png"));
		register.setBounds(40, 220, 284, 71);
		register.setBorder(null);
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String user=Register.this.user.getText();
				String psw1str=String.valueOf(psw1.getPassword());
				String psw2str=String.valueOf(psw2.getPassword());
				Integer id=Integer.parseInt(user);
				if(!user.matches("\\d+")){
					JOptionPane.showMessageDialog(Register.this, "账号为数字");
					return;
				}
				if(!psw1str.equals(psw2str)){
					JOptionPane.showMessageDialog(Register.this, "两次密码不想等");
					return;
				}
				customerdao cus=new customerdao();
				if(cus.isRegistered(id)){
					JOptionPane.showMessageDialog(Register.this, "注册失败，已有id");
					return;
				}
				cus.regsiter(id, psw1str);
				JOptionPane.showMessageDialog(Register.this, "注册成功");
				Register.this.user.setText("");
				psw1.setText("");
				psw2.setText("");
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				login.setVisible(true);
				Register.this.dispose();
			
			}
		});
		con.add(register);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Register(null);
	}
}

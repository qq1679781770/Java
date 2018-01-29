package com.lxj.kbms.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.lxj.kbms.entities.Customer;

@SuppressWarnings("serial")
public class Main extends JFrame{

	private Background bg;
	private JMenuBar bar;
	private JMenuItem addItem,searchItem,deleteItem,uploadItem,checkItem,userItem;
	private Customer user;
	private addPanel addpanel=new addPanel();
	private searchPanel searchpanel=new searchPanel();
	private deletePanel deletepanel=new deletePanel();
	private uploadPanel uploadpanel;
	private checkPanel checkpanel;
	private UserHome userhome;
	
	
	public Main(Customer cus){
		this.user=cus;
		bg=new Background();
		bar=new JMenuBar();
		setJMenuBar(bar);
		setSize(1800,1000);
		addItem=new JMenuItem("添加事件");
		addItem.setIcon(new ImageIcon("images\\add32.png"));
		addItem.setBackground(new Color(255, 222, 173));
		searchItem=new JMenuItem("搜索事件");
		searchItem.setIcon(new ImageIcon("images\\search32.png"));
		searchItem.setBackground(new Color(255, 222, 173));
		deleteItem=new JMenuItem("删除事件");
		deleteItem.setIcon(new ImageIcon("images\\delete32.png"));
		deleteItem.setBackground(new Color(255, 222, 173));
		uploadItem=new JMenuItem("上传事件");
		uploadItem.setIcon(new ImageIcon("images\\upload32.png"));
        uploadItem.setBackground(new Color(255, 222, 173));
		checkItem=new JMenuItem("审核事件");
		checkItem.setIcon(new ImageIcon("images\\check32.png"));
		checkItem.setBackground(new Color(255, 222, 173));
		userItem=new JMenuItem("个人信息");
		userItem.setIcon(new ImageIcon("images\\user32.png"));
		userItem.setBackground(new Color(255, 222, 173));
		addItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(user==null||user.getIdentity().equals("user")){
					JOptionPane.showMessageDialog(Main.this, "没有管理员权限");
					return;
				}
				bg.removeAll();
				bg.add(addpanel);
				bg.setImage(Main.this.getToolkit().getImage("images\\bg.jpg"));
				bg.repaint();
				validate();
			}
		});
		searchItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bg.removeAll();
				bg.add(searchpanel);
				bg.setImage(Main.this.getToolkit().getImage("images\\bg.jpg"));
				bg.repaint();
				validate();
			}
		});
		
		deleteItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(user==null||user.getIdentity().equals("user")){
					JOptionPane.showMessageDialog(Main.this, "没有管理员权限");
					return;
				}
				bg.removeAll();
				bg.add(deletepanel);
				bg.repaint();
				validate();
			}
		});
		
		uploadItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bg.removeAll();
				uploadpanel=new uploadPanel(cus);
				bg.add(uploadpanel);
				bg.repaint();
				validate();
			}
		});
		
		checkItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(cus==null||!cus.getIdentity().equals("admin")){
					JOptionPane.showMessageDialog(Main.this, "没有管理员权限");
					return;
				}
				bg.removeAll();
				checkpanel=new checkPanel(cus);
				bg.add(checkpanel);
				bg.repaint();
				bg.validate();
			}
		});
		
		userItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(cus==null){
					JOptionPane.showMessageDialog(Main.this, "请先登录或注册");
					return;
				}
				bg.removeAll();
				userhome=new UserHome(cus);
				bg.add(userhome);
				bg.repaint();
				bg.validate();				
			}
		});
		if(user!=null&&user.getIdentity().equals("admin")){
			bar.add(addItem);
		}		
		bar.add(searchItem);
		bar.add(deleteItem);
		if(user!=null&&user.getIdentity().equals("user")){
			bar.add(uploadItem);
		}		
		bar.add(checkItem);
		bar.add(userItem);
		bg.removeAll();
		bg.add(searchpanel);
		bg.setImage(Main.this.getToolkit().getImage("images\\bg.jpg"));
		bg.repaint();
		validate();
		bg.setBounds(0, 0,1800,1000);
		add(bg);
		setVisible(true);
		
	}
}

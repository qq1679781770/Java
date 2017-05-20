package com.jsxnh.smartqqclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Test extends JFrame{

	private JScrollPane js;
	public Test(){
		setSize(400, 400);
		js=new JScrollPane();
		JButton b1=new JButton();
		JButton b2=new JButton();
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(Test.this,"dsd");
			}
		});
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		JTree tree=new JTree();
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("好友列表");
		DefaultMutableTreeNode list1=new DefaultMutableTreeNode("张三");
		DefaultMutableTreeNode list2=new DefaultMutableTreeNode("李四");
		root.add(list1);
		root.add(list2);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		DefaultTreeModel model=new DefaultTreeModel(root);
		tree.setModel(model);
		js.setViewportView(tree);
		add(js);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Test();
	}
}

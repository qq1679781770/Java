package com.jsxnh.test;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.jsxnh.component.CellRenderer;
import com.jsxnh.component.FriendNode;
import com.jsxnh.component.FriendTreeUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Test extends JFrame{

    private JTree jTree;
    public Test(){
        FriendNode root = new FriendNode(new ImageIcon("images/arrow_right.png"),"root","root");
        for(int i=0;i<4;i++){
            FriendNode node1 = new FriendNode(new ImageIcon("images/arrow_right.png"),"分组"+i,"分组"+i);
            for(int j=0;j<6;j++){
                FriendNode node_ = new FriendNode(new ImageIcon("images/headimg.png"),"简史小男孩","python","jiansxnh");
                node1.add(node_);
            }
            root.add(node1);
        }
        jTree = new JTree();
        jTree.setModel(new DefaultTreeModel(root));
        jTree.setRootVisible(false);
        jTree.setCellRenderer(new CellRenderer());
        /**jTree.addMouseListener(new MouseAdapter() { // 添加鼠标事件处理
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) { // 点击了鼠标左键
                    TreePath path = jTree.getSelectionPath();
                    if(path != null) {
                        if(jTree.isCollapsed(path)){
                            jTree.expandPath(path);
                        }else{
                            jTree.collapsePath(path);
                        }
                        // 展开节点
                    }
                } // End if
            } // End mousePressed function
        });**/
        jTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                TreePath path = jTree.getSelectionPath();
                if(path != null) {
                    if(jTree.isCollapsed(path)){
                        jTree.expandPath(path);
                    }else{
                        jTree.collapsePath(path);
                    }
                    // 展开节点
                }
                jTree.clearSelection();
            }
        });
        //jTree.putClientProperty("JTree.lineStyle", "None");
        FriendTreeUI ui = new FriendTreeUI();
        ui.setTree(jTree);
        jTree.setUI(new FriendTreeUI());
        jTree.setLayout(null);
        this.setSize(500,550);
        this.Init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.show();

    }
    private void Init() {

        Container cp = this.getContentPane();

        cp.setLayout(null);

        JScrollPane jsp = new JScrollPane(jTree);

        jsp.setBounds(100,20,300,500);

        cp.add(jsp);

    }

    public  static void main(String[] args){
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("Tree.rowHeight",42);
        }
        catch(Exception e) {
            //TODO exception
        }
        new Test();
    }

}

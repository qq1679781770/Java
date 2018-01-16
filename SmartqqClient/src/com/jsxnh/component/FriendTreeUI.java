package com.jsxnh.component;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.TreePath;
import java.awt.*;

public class FriendTreeUI extends BasicTreeUI {

    public void setTree(JTree tree){
        this.tree =  tree;
    }

    public FriendTreeUI(){
        super();
        //super.validCachedPreferredSize = true;
    }
    // 去除JTree的垂直线
    @Override
    protected void paintVerticalLine(Graphics g, JComponent c, int x, int top,
                                     int bottom) {
    }

    // 去除JTree的水平线
    @Override
    protected void paintHorizontalLine(Graphics g, JComponent c, int y,
                                       int left, int right) {
    }


    // 实现父节点与子节点左对齐
    @Override
    public void setLeftChildIndent(int newAmount) {

    }

    // 实现父节点与子节点右对齐
    @Override
    public void setRightChildIndent(int newAmount) {
       
    }



}

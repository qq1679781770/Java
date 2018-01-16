package com.jsxnh.component;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;

public class CellRenderer  extends DefaultTreeCellRenderer  {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean expanded, boolean leaf, int row,
                                                  boolean hasFocus) {
        FriendNode node = (FriendNode) value;
        if (node.getLevel() == 1) {
            if (expanded) {
                node.iconLabel.setIcon(new ImageIcon("images/arrow_down.png"));
            } else {
                node.iconLabel.setIcon(new ImageIcon("images/arrow_right.png"));
            }

            return node.getCateView();
        }
        if (node.getLevel() == 2) {
            ImageIcon image = new ImageIcon("images/headimg.png");
            image.setImage(image.getImage().getScaledInstance(39, 40,Image.SCALE_DEFAULT ));
            node.iconLabel.setIcon(image);
           return node.getNodeView();
        }
        return this;
    }
}

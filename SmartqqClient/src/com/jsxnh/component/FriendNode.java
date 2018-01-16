package com.jsxnh.component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class FriendNode extends DefaultMutableTreeNode {

    private Icon icon;
    /** 文字 */
    private String name;
    /** 签名 */
    private String sign;

    public JPanel cateContent;
    public JPanel nodeContent;

    public JLabel iconLabel;
    public JLabel nameLabel;
    public JLabel signLabel;

    /**
     * 初始化分组节点
     * @param name 名称
     */
    public FriendNode(Icon icon, String name,Object userobject) {
        super(userobject);
        this.icon = icon;
        this.name = name;
        // 初始化UI
        initCateGUI();
    }

    /**
     * 初始化好友节点
     * @param icon 头像
     * @param nick 昵称
     * @param sign 签名
     */
    public FriendNode(Icon icon, String nick, String sign,Object userobject) {
        super(userobject);
        this.icon = icon;
        this.name = nick;
        this.sign = sign;
        // 初始化UI
        initNodeGUI();
    }

    /**
     * 自定义分组UI
     */
    private void initCateGUI() {
        cateContent = new JPanel();
        cateContent.setLayout(null);
        cateContent.setOpaque(false);
        cateContent.setPreferredSize(new Dimension(258,32));
        //cateContent.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        iconLabel = new JLabel(icon);
        iconLabel.setBounds(6, 0, 20, 20);
        cateContent.add(iconLabel);

        nameLabel = new JLabel(name);
        nameLabel.setBounds(28, 0, 132, 20);
        cateContent.add(nameLabel);
    }

    /**
     * 自定义好友UI
     */
    private void initNodeGUI() {
        nodeContent = new JPanel();
        nodeContent.setLayout(null);
        nodeContent.setOpaque(false);
        nodeContent.setPreferredSize(new Dimension(258, 50));
        nodeContent.setSize(258,50);

        //nodeContent.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        iconLabel = new JLabel(icon);
        iconLabel.setBounds(8, 4, 39, 40);
        nodeContent.add(iconLabel);

        nameLabel = new JLabel(name);
        nameLabel.setBounds(59, 5, 132, 19);
        nodeContent.add(nameLabel);

        signLabel = new JLabel(sign);
        signLabel.setFont(new Font("宋体",Font.PLAIN,14));
        signLabel.setBounds(59, 28, 132, 17);
        nodeContent.add(signLabel);
    }

    /**
     * 将自定义UI返回给渲染器 <br/>
     * 供渲染器调用，返回的必须是一个Component
     * @return
     */
    public Component getCateView() {
        return cateContent;
    }

    /**
     * 将自定义UI返回给渲染器 <br/>
     * 供渲染器调用，返回的必须是一个Component
     * @return
     */
    public Component getNodeView() {
        return nodeContent;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

package com.jsxnh.test;

import javax.swing.*;
import java.awt.*;

public class Test3 extends JFrame{
    private JScrollPane jsp;
    private JList<JPanel> list;

    public Test3(){
        setSize(400,200);
        setLayout(null);
        list = new JList();
        DefaultListModel<JPanel> model = new DefaultListModel();
        /**for(int i=0;i<10;i++){
            model.addElement(new mypanel());
        }**/

        list.setModel(model);
        list.setCellRenderer(new listcellrenender());
        list.setFixedCellHeight(50);
        jsp = new JScrollPane();
        jsp.setPreferredSize(new Dimension(350,200));
        jsp.setViewportView(list);
        //jsp.setBounds(0,0,350,200);
        this.getContentPane().add(jsp);
        setVisible(true);
    }


    private class mypanel extends JPanel{
        private JLabel label;
        private JButton button;

        public mypanel(){
            setLayout(null);
            setPreferredSize(new Dimension(300,36));
            setOpaque(false);
            label = new JLabel("简史小男孩");
            label.setBounds(0,4,150,28);
            add(label);
            button = new JButton();
            button.setText("接收");
            button.setBounds(220,4,60,28);
            add(button);
        }
    }

    public static void main(String[] args){
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Test3();
    }

    private class listcellrenender implements ListCellRenderer{

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            return new mypanel();
        }
    }
}

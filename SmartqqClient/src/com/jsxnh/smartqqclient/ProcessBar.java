package com.jsxnh.smartqqclient;

import javax.swing.*;
import java.awt.*;

public class ProcessBar extends JFrame {

    private Container container = this.getContentPane();
    private JProgressBar processBar;

    public ProcessBar(){
        setLayout(null);
        setSize(300,150);
        processBar = new JProgressBar();
        processBar.setBounds(20,20,150,50);
        processBar.setBackground(Color.GREEN);
        processBar.setValue(0);
        container.add(processBar);
        background bg=new background();
        bg.setImage(this.getToolkit().getImage("images\\bg1.jpg"));
        bg.setBounds(0, 0,300, 150);
        container.add(bg);
        container.repaint();
        setVisible(true);
    }

    public void setValue(int value){
        processBar.setValue(value);
    }

}

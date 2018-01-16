package com.jsxnh.smartqqclient;

import javax.swing.*;
import java.awt.*;

public class background  extends JPanel{
    private Image image;
    public background(){
        setOpaque(false);
        setLayout(null);
    }
    public void setImage(Image image){
        this.image=image;
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(image!=null){
            g.drawImage(image, 0, 0,this);
        }
    }
}

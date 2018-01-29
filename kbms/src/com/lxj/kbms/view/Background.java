package com.lxj.kbms.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Background extends JPanel{

	private Image image;
	public void setImage(Image image){
		this.image=image;
	}
	public Background(){
		setOpaque(false);
		setLayout(null);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(image!=null){
			g.drawImage(image, 0, 0, this);
		}		
	}
}

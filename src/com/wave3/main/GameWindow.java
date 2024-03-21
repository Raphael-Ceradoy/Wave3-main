package com.wave3.main;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

public class GameWindow extends JFrame { 	

	public static int GAMEWIDTH = 900, GAMEHEIGHT = 600;
	
	public GameWindow(){ 
		GridBagLayout layout = new GridBagLayout();   
		this.setLayout(layout);
		
		this.setTitle("Wave 3");
		this.setSize(GAMEWIDTH + 15, GAMEHEIGHT + 40);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}
}
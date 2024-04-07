package com.wave3.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.wave3.gameElement.Handler;
import com.wave3.listeners.KeyboardListener;
import com.wave3.main.GameWindow;

public class Player extends GameObject{
	
	private KeyboardListener keyboardListener;

	public Player(Handler handler, KeyboardListener keyboardListener) {
		super(handler);
		
		this.keyboardListener = keyboardListener;
		id = ID.PLAYER;
		
		velX = 0;
		velY = 0;
		
		width = 40;
		height = 40;
		
		x = GameWindow.GAMEWIDTH / 2 - width/2;
		y = GameWindow.GAMEHEIGHT / 2 - height/2;
		
	}

	@Override
	public void tick() {
		//Handle player input
		boolean[] keys = keyboardListener.getKeys();
		if(!keys[3] || !keys[1]) {
			if(keys[1]) 
				velX = -5;
			if(keys[3])
				velX = 5;
		}
		if(!keys[0] || !keys[2]) {
			if(keys[0]) 
				velY = -5;
			if(keys[2])
				velY = 5;
		}
		if(!keys[3] && !keys[1])
			velX = 0;
		if(!keys[0] && !keys[2])
			velY = 0;
	
		//Update player position
		x += velX;
		y += velY;
		

		//Keep player in bounds
		clamp();
		

		//Create trail behind player
		handler.addObject(new Trail(x, y, Color.white, (int)width, (int)height, 0.1f, handler));
	
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.white);
		g2d.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void collision(ID id) {
		// TODO Auto-generated method stub
	}

}
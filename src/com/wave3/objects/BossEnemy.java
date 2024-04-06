package com.wave3.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import com.wave3.gameElement.Handler;
import com.wave3.main.GameWindow;
import com.wave3.main.Gamestate;

public class BossEnemy extends GameObject{
	
	private int spawn_timer = 30;
	private int exit_timer = 1250;
	private int speed = 1;
	
	private float rotation = 0;

	public BossEnemy(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
		id = ID.BOSSENEMY;
		width = 100;
		height = 100;
		x = GameWindow.GAMEWIDTH/2 - width/2;
		y = -100;
		
		this.velY = 1;
		this.velX = 0;
		
		
	}

	@Override
	public void tick() {
		// Update the position
		x += velX;
		y += velY;
		
		spawn_timer--;
		if(spawn_timer <= 0 && velY == 0) {
			spawn_timer = 30;
			spawnPellet();
		}
		
		if(y + height/2 == GameWindow.GAMEHEIGHT/2) {
			velY = 0;
			exit_timer--;
		}
		
		if(exit_timer <= 0) {
			velY = 1;
		}

//		handler.addObject(new Trail(x, y, Color.DARK_GRAY, (int)width, (int)height, 0.05f, handler));

		if(y > GameWindow.GAMEHEIGHT) {
			handler.removeObject(this);
		}

	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.white);   
		
		Stroke oldStroke = g2d.getStroke();
		g2d.setStroke(new BasicStroke(5));
	    g2d.drawRect((int)x, (int)y, (int)width, (int)height);
	    
		g2d.setColor(Color.DARK_GRAY);
	    g2d.fillRect((int)x, (int)y, (int)width, (int)height);
	    
	    g2d.setStroke(oldStroke);
		
		
	}

	@Override
	public void collision(ID id) {
		if(id == ID.PLAYER) {
			Gamestate.health -= 10;
		}
	}
	
	private void spawnPellet() {
		double speedX, speedY;
		
		for(int i = 0; i < 360; i += 90) {
			
			double radians = Math.toRadians(i + rotation);
			double sinX = (float) Math.sin(radians);
			speedX = speed * sinX;
			
			radians = Math.toRadians(180 - 90 - i - rotation);
			double sinY = (float) Math.sin(radians);
			speedY = speed * sinY;
			
			handler.addObject(new ExplosionPelletEnemy(handler, x + width/2 - 10, y + height/2 - 10, (float) speedX, (float) speedY));
		}
		rotation += 7.5;
		
	}
	
}
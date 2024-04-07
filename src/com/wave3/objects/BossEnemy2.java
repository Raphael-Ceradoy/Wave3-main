package com.wave3.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import com.wave3.gameElement.Handler;
import com.wave3.main.GameWindow;
import com.wave3.main.Gamestate;

public class BossEnemy2 extends GameObject{
	
	private int speed = 1;
	private int timer = 0;
	private int stage  = 0;
	private int count = 0;
	
	private float rotation = 0;

	public BossEnemy2(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
		id = ID.BOSSENEMY;
		width = 100;
		height = 100;
		x = GameWindow.GAMEWIDTH/2 - width/2;
		y = -100;
		
		this.velY = speed;
		this.velX = 0;
		
		
	}

	@Override
	public void tick() {
		// Update the position
		x += velX;
		y += velY;
		
		if(stage == 0 && y >= 10) {
			velY = 0;
			velX = speed;
			stage = 1;
		}
		
		if(stage == 1 && timer <= 0) {
			timer = 100;
			
			for(int i = 0; i < 50; i++) {
				if((i * 20) + 10 + 25 >= x && (i*20) <= x + width + 10)continue;
				
				if((i * 20) + 10 >= GameWindow.GAMEWIDTH)break;
					
				handler.addObject(new ExplosionPelletEnemy(handler, i * 20, 0, 0f, 7.5f));
				handler.addObject(new ExplosionPelletEnemy(handler, i * 20, 0, 0f, 7.5f));
			}
			
			count++;
			
			if(count >= 30) stage = 2;
		}
		
		if(stage == 2) {
			velY = -1;
			velX = 0;
			stage = 3;
			
		}
		
		if(x + width >= GameWindow.GAMEWIDTH || x <= 0) velX *= -1;
		
		timer--;
		
		
		
		

//		handler.addObject(new Trail(x, y, Color.DARK_GRAY, (int)width, (int)height, 0.05f, handler));

		if(y + height < 0) {
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
	    
		g2d.setColor(new Color(75, 100, 75));
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
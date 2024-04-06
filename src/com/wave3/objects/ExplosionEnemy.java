package com.wave3.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.wave3.gameElement.Handler;
import com.wave3.main.Gamestate;

public class ExplosionEnemy extends GameObject{
	
	private int spawn_timer = 60;
	private int speed = 2;

	public ExplosionEnemy(Handler handler, float x, float y) {
		super(handler, x, y);
		// TODO Auto-generated constructor stub
		id = ID.WAITINGENEMY;
		width = 100;
		height = 100;
		this.velX = (handler.getRandom().nextInt(2) * 2 - 1) * speed;
		this.velY = (handler.getRandom().nextInt(2) * 2 - 1) * speed;
		
		
	}
	
	public ExplosionEnemy(Handler handler, float x, float y, float velX, float velY) {
		super(handler, x, y, velX, velY);
		// TODO Auto-generated constructor stub
		id = ID.WAITINGENEMY;
		width = 100;
		height = 100;
		
	}

	@Override
	public void tick() {
		if(spawn_timer > 0) {
			spawn_timer--;
			return;
		}
		if(spawn_timer == 0) {
			id = ID.EXPLOSIONENEMY;
			spawn_timer--;
		}
		// Update the position
		x += velX;
		y += velY;

		handler.addObject(new Trail(x, y, Color.red, (int)width, (int)height, 0.05f, handler));

		clamp();
		
		if(hit.get("left") || hit.get("right")) velX *= -1;
		if(hit.get("up") || hit.get("down")) velY *= -1;
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.red);
		if(spawn_timer > 0) {
			g2d.drawRect((int)x, (int)y, (int)width, (int)height);
		}
		else {
			g2d.fillRect((int)x, (int)y, (int)width, (int)height);
		}
	}

	@Override
	public void collision(ID id) {
		if(id == ID.PLAYER && spawn_timer == -1) {
			Gamestate.health -= 10;
		}
		
		if(id == ID.EXPLOSIONENEMY && spawn_timer == -1) {
			int spawnVelX = 0, spawnVelY = speed;

			handler.addObject(new ExplosionPelletEnemy(handler, x + width/2, y + height/2, 0, speed));
			handler.addObject(new ExplosionPelletEnemy(handler, x + width/2, y + height/2, 0, -speed));
			handler.addObject(new ExplosionPelletEnemy(handler, x + width/2, y + height/2, speed, 0));
			handler.addObject(new ExplosionPelletEnemy(handler, x + width/2, y + height/2, speed, speed));
			handler.addObject(new ExplosionPelletEnemy(handler, x + width/2, y + height/2, speed, -speed));
			handler.addObject(new ExplosionPelletEnemy(handler, x + width/2, y + height/2, -speed, 0));
			handler.addObject(new ExplosionPelletEnemy(handler, x + width/2, y + height/2, -speed, speed));
			handler.addObject(new ExplosionPelletEnemy(handler, x + width/2, y + height/2, -speed, -speed));
			
			handler.removeObject(this);
		}
	}
	
}
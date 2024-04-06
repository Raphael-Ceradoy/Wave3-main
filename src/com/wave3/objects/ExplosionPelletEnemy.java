package com.wave3.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.wave3.gameElement.Handler;
import com.wave3.main.Gamestate;

public class ExplosionPelletEnemy extends GameObject{
	
	public ExplosionPelletEnemy(Handler handler, float x, float y, float velX, float velY) {
		super(handler, x, y, velX, velY);
		
		this.width = 20;
		this.height = 20;
	}

	@Override
	public void tick() {
		// Update the position
		x += velX;
		y += velY;
		
		velX *= 1.005;
		velY *= 1.005;

		handler.addObject(new Trail(x, y, Color.LIGHT_GRAY, (int)width, (int)height, 0.1f, handler));

		clamp();
		
		if(hit.get("left") || hit.get("right") || hit.get("up") || hit.get("down")) {
			handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	@Override
	public void collision(ID id) {
		// Temporary code to remove the BasicEnemy if it hits the player
		if(id == ID.PLAYER) {
			Gamestate.health -= 50;
			handler.removeObject(this);
		}
	}
	
}
package com.wave3.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.wave3.gameElement.Handler;
import com.wave3.main.GameWindow;
import com.wave3.main.Gamestate;

public class BasicEnemy extends GameObject{
	
	private int spawn_timer = 60;

	public BasicEnemy(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
		id = ID.WAITINGENEMY;
		
		x = handler.getRandom().nextInt(GameWindow.GAMEWIDTH - 200) + 50;
		y = handler.getRandom().nextInt(GameWindow.GAMEHEIGHT - 200) + 50;
		width = 40;
		height = 40;
		
	}

	@Override
	public void tick() {
		if(spawn_timer > 0) {
			spawn_timer--;
			return;
		}
		if(spawn_timer == 0) {
			this.velX = (handler.getRandom().nextInt(2) * 2 - 1) * 5;
			this.velY = (handler.getRandom().nextInt(2) * 2 - 1) * 5;
			id = ID.BASICENEMY;
			spawn_timer--;
		}
		// Update the position
		x += velX;
		y += velY;

		handler.addObject(new Trail(x, y, Color.blue, (int)width, (int)height, 0.1f, handler));

		clamp();
		
		if(hit.get("left") || hit.get("right")) velX *= -1;
		if(hit.get("up") || hit.get("down")) velY *= -1;
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.blue);
		if(spawn_timer > 0) {
			g2d.drawRect((int)x, (int)y, (int)width, (int)height);
		}
		else {
			g2d.fillRect((int)x, (int)y, (int)width, (int)height);
		}
	}

	@Override
	public void collision(ID id) {
		// Temporary code to remove the BasicEnemy if it hits the player
		if(id == ID.PLAYER && spawn_timer == -1) {
			Gamestate.health-=5;
		}
	}
	
}
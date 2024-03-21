package com.wave3.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import com.wave3.gameElement.Handler;
import com.wave3.main.GameWindow;
import com.wave3.main.Gamestate;

public class SmartEnemy extends GameObject{
	
	private int spawn_timer = 60;
	private GameObject player;

	public SmartEnemy(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
		id = ID.WAITINGENEMY;
		
		
		x = handler.getRandom().nextInt(GameWindow.GAMEWIDTH - 200) + 50;
		y = handler.getRandom().nextInt(GameWindow.GAMEHEIGHT - 200) + 50;
		width = 40;
		height = 40;
		
		
		for(int i = 0; i < handler.getObjects().size(); i++) {
			GameObject temp = handler.getObjects().get(i);
		
			if(temp.getId() == ID.PLAYER) {
				player = temp;
			}
		}
		
	}

	@Override
	public void tick() {
		if(spawn_timer > 0) {
			spawn_timer--;
			return;
		}
		if(spawn_timer == 0) {
			id = ID.BASICENEMY;
			spawn_timer--;
		}
		
		float diffX = (float) (x - player.getX() - width/2);
		float diffY = (float) (y - player.getY() - height/2);
		float distance = (float) Math.sqrt(
			(Math.pow(x - player.getX(), 2)) +
			(Math.pow(y - player.getY(), 2))
		);
		
		velX = (float) ((-1.0/distance) * diffX);
		velY = (float) ((-1.0/distance) * diffY);
		// Update the position
		x += velX;
		y += velY;

		clamp();
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.green);
		if(spawn_timer > 0) {
			g2d.drawRect((int) x, (int) y, (int) width, (int) height);
		}
		else {
			g2d.fillRect((int) x, (int) y, (int) width, (int) height);
		}
	}

	@Override
	public void collision(ID id) {
		// Temporary code to remove the BasicEnemy if it hits the player
		if(id == ID.PLAYER && spawn_timer == -1) {
			Gamestate.health -= 10;
		}
	}
	
}

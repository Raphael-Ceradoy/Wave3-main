package com.wave3.gameElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.wave3.main.GameWindow;
import com.wave3.main.Gamestate;

public class HUD {
	private float ratio;
	private Handler handler;
	private Spawner spawner;
	
	private int scorePerLevel = 250;
	
	
	
	public HUD(Handler handler, Spawner spawner) {
		this.handler = handler;
		this.spawner = spawner;
	}
	
	public void tick() {
		ratio = Gamestate.health/1000;
		if(Gamestate.score % scorePerLevel == 0) {
			Gamestate.level++;
			spawner.nextLevel();
		}
		
		if(Gamestate.health <= 0) {
			handler.removeAll();
			Gamestate.health = 1000;
		}
		Gamestate.score++;
		
	}
	
	public void render(Graphics2D g) {
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		g.setColor(Color.WHITE);
		g.drawString("Score: " + Gamestate.score, 5, 65);
		g.drawString("Level: " + Gamestate.level, 10, 95);
		
		g.setColor(Color.green);
		g.fillRect(5, 5, (int) (GameWindow.GAMEWIDTH/3 * ratio), 35);
		g.setColor(Color.white);
		g.drawRect(5, 5, GameWindow.GAMEWIDTH/3, 35);
		
	}
}

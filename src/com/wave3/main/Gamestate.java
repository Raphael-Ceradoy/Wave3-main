package com.wave3.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.wave3.gameElement.HUD;
import com.wave3.gameElement.Handler;
import com.wave3.gameElement.Spawner;
import com.wave3.listeners.KeyboardListener;
import com.wave3.objects.Player;

public class Gamestate {
	public static String GAMESTATE;
	public static float health = 1000;
	public static int score = 0, level = 0;
	
	
	private Handler handler;
	private KeyboardListener keyboardListener;
	
	private Spawner spawner;
	private HUD hud;
	
	public Gamestate(Handler handler) {
		this.handler = handler;
		this.keyboardListener = handler.getKeyboardListener();
		
		GAMESTATE = "start";
	}
	
	public void tick() {
		if(GAMESTATE == "start") {
//			TASKS
			
//			EXIT CONDITION
			if(keyboardListener.getEnter()) {
				startGame();
				GAMESTATE = "game";
			}
		}
		else if(GAMESTATE == "game") {
//			TASKS
			hud.tick();
			handler.tick();
			
//			EXIT CONDITION
			if(health <= 0) {
				GAMESTATE = "start";
			}
			else if(keyboardListener.getEscape()) {
				GAMESTATE = "pause";
			}
		}
		else if(GAMESTATE == "pause") {
//			TASKS
			
//			EXIT CONDITION
			if(keyboardListener.getEnter()) {
				GAMESTATE = "game";
			}
		}
	}
	
	
	
	public void render(Graphics2D g) {
		if(GAMESTATE == "start") {
			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
			g.setColor(Color.WHITE);
			g.drawString("Previous Score: " + Gamestate.score, 100, 200);
			g.drawString("Press Enter to Play!!!", 100, 300);
		}
		else if(GAMESTATE == "game") {
			handler.render(g);
			hud.render(g);
		}
		else if(GAMESTATE == "pause") {
			handler.render(g);
			hud.render(g);
			
			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
			g.setColor(Color.WHITE);
			g.drawString("Current Score: " + Gamestate.score, 100, 200);
			g.drawString("Press Enter to Continue", 100, 300);
		}
	}
	
	public void startGame() {
		handler.removeAll();
		health = 1000;
		score = 0;
		level = 0;
		this.spawner = new Spawner(handler);
		this.hud = new HUD(handler, spawner);
	}
}

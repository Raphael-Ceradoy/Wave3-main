package com.wave3.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import com.wave3.gameElement.HUD;
import com.wave3.gameElement.Handler;
import com.wave3.gameElement.Spawner;
import com.wave3.listeners.KeyboardListener;
import com.wave3.objects.Player;

public class Gamestate extends MouseAdapter{
	public static String GAMESTATE;
	public static float health = 1000;
	public static int score = 0, level = 0;
	
	
	private Handler handler;
	private KeyboardListener keyboardListener;
	
	private Spawner spawner;
	private HUD hud;
	
	public Gamestate(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		this.keyboardListener = handler.getKeyboardListener();
		
		GAMESTATE = "start";
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//play button
		if(mouseOver(mx, my, 350, 200, 200, 64)) {
			GAMESTATE = "game";
			startGame();
			
		}
		
		//resume button for paused game
		if(GAMESTATE == "pause") {
			if(mouseOver(mx, my, 350, 400, 200, 64)) {
				GAMESTATE = "quit";
			}
			
		}
		
		//help button
		if(mouseOver(mx, my, 350, 280, 200, 64)) {
			GAMESTATE = "help";
		}
		
		//back button for help
		if(GAMESTATE == "help") {
			if(mouseOver(mx, my, 350, 500, 200, 64)) {
				GAMESTATE = "start";
			}
		}
		
		//Quit button for menu
		if(GAMESTATE == "start") {
			if(mouseOver(mx, my, 350, 360, 200, 64)) {
				GAMESTATE = "quit";
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
			else return false;
		}
		else return false;
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
		else if(GAMESTATE == "help") {
			if(keyboardListener.getEnter()) {
				GAMESTATE = "game";
			}
		}
		else if(GAMESTATE == "quit") {
			System.exit(1);
		}
	}
	
	
	
	public void render(Graphics2D g) {
		if(GAMESTATE == "start" || GAMESTATE == "help" || GAMESTATE == "pause") {
	        // Load and draw the GIF image
	        ImageIcon gifIcon = new ImageIcon("res/menu/ryan-haight-kerry-park-ezgif.com-resize.gif"); // Replace "your_gif.gif" with the path to your GIF file
	        Image scaledImage = gifIcon.getImage().getScaledInstance(GameWindow.GAMEWIDTH, GameWindow.GAMEHEIGHT, Image.SCALE_DEFAULT);

	        g.drawImage(gifIcon.getImage(), 0, 0, null);
	    }
		else {
			ImageIcon backgroundImageIcon = new ImageIcon("res/background/kerry-park.png"); // Change "game_background.jpg" to your image path
	        Image backgroundImage = backgroundImageIcon.getImage();
	        g.drawImage(backgroundImage, 0, 0, GameWindow.GAMEWIDTH, GameWindow.GAMEHEIGHT, null);
		}
		if(GAMESTATE == "start") {
			/*g.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
			g.setColor(Color.WHITE);
			g.drawString("Previous Score: " + Gamestate.score, 100, 200);
			g.drawString("Press Enter to Play!!!", 100, 300);*/
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setColor(Color.black);

			g.setFont(fnt);
			g.drawString("Menu", 375, 70);

			g.setFont(fnt2);
			//g.drawRect(275, 165, 350, 100);
			g.drawString("Previous Score: " + Gamestate.score, 305, 150);
			//g.drawString("Press Enter to Play!", 300, 250);

			g.setColor(Color.white); // Change the color of the button name
	        g.fillRect(350, 200, 200, 64); // Change the background color of the button
	        g.setColor(Color.black); // Reset color to white for button name
	        g.drawRect(350, 200, 200, 64);
	        g.drawString("Play", 420, 240);
	        
	        g.setColor(Color.white); // Change the color of the button name
	        g.fillRect(350, 280, 200, 64); // Change the background color of the button
	        g.setColor(Color.black);
			g.drawRect(350, 280, 200, 64);
			g.drawString("Help", 420, 320);
			
			g.setColor(Color.white); // Change the color of the button name
	        g.fillRect(350, 360, 200, 64); // Change the background color of the button
	        g.setColor(Color.black);
			g.drawRect(350, 360, 200, 64);
			g.drawString("Quit", 420, 400);
		}
		else if(GAMESTATE == "game") {
			handler.render(g);
			hud.render(g);
		}
		else if(GAMESTATE == "pause") {
			//handler.render(g);
			//hud.render(g);
			/*
			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 48));
			g.setColor(Color.WHITE);
			g.drawString("Current Score: " + Gamestate.score, 100, 200);
			g.drawString("Press Enter to Continue", 100, 300); */
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setColor(Color.black);

			g.setFont(fnt);
			g.drawString("Game Paused", 275, 70);

			g.setFont(fnt2);
			g.drawRect(265, 145, 360, 100);
			g.drawString("Current Score: " + Gamestate.score, 310, 180);
			g.drawString("Press Enter to Continue", 270, 230);
			
			g.setColor(Color.white); // Change the color of the button name
	        g.fillRect(350, 400, 200, 64); // Change the background color of the button
	        g.setColor(Color.black);
			g.drawString("Quit", 415, 445);
			g.drawRect(350, 400, 200, 64);

		}
		else if(GAMESTATE == "help") {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 395, 70);
			
			g.setFont(fnt2);
			g.drawString("Use WASD to move and dodge eneimes", 180, 300);
			
			g.setFont(fnt2);
			g.setColor(Color.white); // Change the color of the button name
	        g.fillRect(350, 500, 200, 64); // Change the background color of the button
	        g.setColor(Color.black);
			g.drawRect(350, 500, 200, 64);
			g.drawString("Back", 415, 545);
			//gameEffect.render(g);
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
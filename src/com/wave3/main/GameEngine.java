package com.wave3.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.wave3.gameElement.HUD;
import com.wave3.gameElement.Handler;
import com.wave3.gameElement.Spawner;
import com.wave3.gbc.GBC;
import com.wave3.listeners.KeyboardListener;
import com.wave3.objects.BasicEnemy;
import com.wave3.objects.ExplosionEnemy;
import com.wave3.objects.FastEnemy;
import com.wave3.objects.Player;
import com.wave3.objects.SmartEnemy;

public class GameEngine implements Runnable{
	
	private Thread thread;
	private BufferStrategy bufferStrategy;
	
	private GameCanvas gameCanvas;
	private GameWindow gameWindow;
	
	private Handler handler;
	private Spawner spawner;
	private Random random;
	private Gamestate gamestate;
	private KeyboardListener keyboardListener;
	private HUD hud;
	
	public GameEngine() {
		init();
	}
	
	public void init() {
		keyboardListener = new KeyboardListener();
		random = new Random();
		handler = new Handler(keyboardListener, random);
		this.gamestate = new Gamestate(handler);
		
		gameWindow  = new GameWindow();
		gameCanvas = new GameCanvas();
		gameCanvas.addKeyListener(keyboardListener.getListener());
	}
	
	public void start() {
		gameWindow.add(gameCanvas, new GBC(0, 0).setFill(GBC.BOTH).setWeight(1, 1));
		gameCanvas.createBufferStrategy();
		this.bufferStrategy = this.gameCanvas.getBufferStrategy();
		
		
		thread = new Thread(this, "Game");
		thread.start();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		int updatesCounter = 0; // for counting the updates
		int framesCounter = 0; // for counting the frames

		// Updates per second cap
		final int UPS_CAP = 60;

		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / (double) (1000000000 / UPS_CAP);
			lastTime = now;

			while (delta >= 1) {
				tick();
				updatesCounter++;
				delta--;
//				I MADE A SMALL CHANGE
				render();
				framesCounter++;
//				END OF SMALL CHANGE
			}

/*          Moving the comment below up
//			render();
//			framesCounter++;
 */

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("Updates per second: " + updatesCounter + ", Frames per second: " + framesCounter);
				updatesCounter = 0;
				framesCounter = 0;
			}
		}
	}
	
	private void tick() {
//		handler.tick();
//		hud.tick();
		gamestate.tick();
	}
	
	private void render() {
		Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, GameWindow.GAMEWIDTH, GameWindow.GAMEHEIGHT);
		
//		handler.render(g2d);
//		hud.render(g2d);
		gamestate.render(g2d);
		
		g2d.dispose();
		bufferStrategy.show();
		
	}
	
}

package com.wave3.gameElement;

import com.wave3.main.GameWindow;
import com.wave3.main.Gamestate;
import com.wave3.objects.BasicEnemy;
import com.wave3.objects.BossEnemy;
import com.wave3.objects.ExplosionEnemy;
import com.wave3.objects.FastEnemy;
import com.wave3.objects.Player;
import com.wave3.objects.SmartEnemy;

public class Spawner {
	
	private Handler handler;
	
	public Spawner(Handler handler) {
		this.handler = handler;
	}
	
	public void nextLevel() {
		
//		FIRST WAVE
		if(Gamestate.level == 1) {
			handler.addObject(new Player(handler, handler.getKeyboardListener()));
		}
		else if(Gamestate.level == 2) {
			handler.addObject(new ExplosionEnemy(handler, 100, 100, 0, 5));
			handler.addObject(new ExplosionEnemy(handler, 100, 400, 0, -5));
		}
		else if(Gamestate.level == 3) {
			handler.addObject(new BasicEnemy(handler));
		}
		else if(Gamestate.level == 4) {
			handler.addObject(new FastEnemy(handler));
		}
		else if(Gamestate.level == 5) {
			handler.addObject(new SmartEnemy(handler));
		}
		else if(Gamestate.level == 7) {
			handler.removeEnemies();
		}
		
//		SECOND WAVE
		else if(Gamestate.level == 8) {
			handler.addObject(new BossEnemy(handler));
		}
		else if(Gamestate.level == 9) {
			handler.addObject(new ExplosionEnemy(handler, 100, 100, 0, 5));
			handler.addObject(new ExplosionEnemy(handler, 100, 400, 0, -5));
			
			handler.addObject(new ExplosionEnemy(handler, GameWindow.GAMEWIDTH-200, 100, 0, 5));
			handler.addObject(new ExplosionEnemy(handler, GameWindow.GAMEWIDTH-200, 400, 0, -5));
		}
		else if(Gamestate.level == 10) {
			handler.addObject(new FastEnemy(handler));
			handler.addObject(new FastEnemy(handler));
		}
		else if(Gamestate.level == 11) {
			handler.addObject(new SmartEnemy(handler));
		}
		else if(Gamestate.level == 12) {
			handler.removeEnemies();
		}
		
//		THIRD WAVE
		else if(Gamestate.level == 13) {
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
		}
		else if(Gamestate.level == 14) {
			handler.addObject(new FastEnemy(handler));
			handler.addObject(new FastEnemy(handler));
		}
		else if(Gamestate.level == 15) {
			handler.addObject(new SmartEnemy(handler));
		}
		else if(Gamestate.level == 16) {
			handler.addObject(new ExplosionEnemy(handler, getRandomX(), 100));
			handler.addObject(new ExplosionEnemy(handler, getRandomX(), 400));
		}
		else if(Gamestate.level == 17) {
			handler.addObject(new ExplosionEnemy(handler, getRandomX(), 100));
			handler.addObject(new ExplosionEnemy(handler, getRandomX(), 400));
		}
		else if(Gamestate.level == 18) {
			handler.removeEnemies();
		}
		
//		FOURTH WAVE
		else if(Gamestate.level == 19) {
			handler.addObject(new BossEnemy(handler));
		}
		else if(Gamestate.level == 20) {
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
		}
		else if(Gamestate.level == 21) {
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
		}
		else if(Gamestate.level == 22) {
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
		}
		else if(Gamestate.level == 23) {
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
		}
		else if(Gamestate.level == 25) {
			handler.removeEnemies();
		}
		
//		FIFTH WAVE
		else if(Gamestate.level == 26) {
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
		}
		else if(Gamestate.level == 27) {
			handler.addObject(new FastEnemy(handler));
			handler.addObject(new FastEnemy(handler));
		}
		else if(Gamestate.level == 28) {
			handler.addObject(new SmartEnemy(handler));
		}
		else if(Gamestate.level == 29) {
			handler.addObject(new SmartEnemy(handler));
			handler.addObject(new ExplosionEnemy(handler, getRandomX(), 100));
			handler.addObject(new ExplosionEnemy(handler, getRandomX(), 225));
			handler.addObject(new ExplosionEnemy(handler, getRandomX(), 350));
			handler.addObject(new ExplosionEnemy(handler, getRandomX(), 475));
		}
		else if(Gamestate.level == 30) {
			handler.addObject(new BossEnemy(handler));
		}
		else if(Gamestate.level == 35) {
			handler.removeEnemies();
		}
		
//		FINAL WAVE
		else if(Gamestate.level == 36) {
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			handler.addObject(new BasicEnemy(handler));
			
			handler.addObject(new FastEnemy(handler));
			handler.addObject(new FastEnemy(handler));
			handler.addObject(new FastEnemy(handler));
			handler.addObject(new FastEnemy(handler));
			handler.addObject(new FastEnemy(handler));
			handler.addObject(new FastEnemy(handler));
			handler.addObject(new FastEnemy(handler));
			handler.addObject(new FastEnemy(handler));
			handler.addObject(new FastEnemy(handler));
			
			handler.addObject(new SmartEnemy(handler));
			handler.addObject(new SmartEnemy(handler));
			handler.addObject(new SmartEnemy(handler));
			handler.addObject(new SmartEnemy(handler));
		}
		
		
	}
	
	public int getRandomX() {
		return handler.getRandom().nextInt(GameWindow.GAMEWIDTH - 200) + 100;
	}
	
	public int getRandomY() {
		return handler.getRandom().nextInt(GameWindow.GAMEHEIGHT - 200) + 100;
	}
}	
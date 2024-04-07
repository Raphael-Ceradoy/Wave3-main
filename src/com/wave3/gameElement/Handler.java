package com.wave3.gameElement;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import com.wave3.listeners.KeyboardListener;
import com.wave3.objects.GameObject;
import com.wave3.objects.ID;
import com.wave3.objects.Player;

public class Handler {
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	private ArrayList<GameObject> temp_objects;
	
	private KeyboardListener keyboardListener;
	private Random random;
	
//	Constructor
	public Handler(KeyboardListener keyboardListener, Random random) {
		this.keyboardListener = keyboardListener;
		this.random = random;
	}
	
//	Logic to run after every tick
	public void tick() {
//		Run tick for every object
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).tick();
		}
		
//		Check for collisions between every object
		temp_objects = new ArrayList<GameObject>(objects);
		for(int i = 0; i < objects.size(); i++) {
			GameObject object1 = objects.get(i);
			temp_objects.remove(object1);
			
			if(object1.getId() == ID.PLAYER || object1.getId() == ID.EXPLOSIONENEMY || object1.getId() == ID.WAITINGENEMY)
			for(int j = 0; j < temp_objects.size(); j++) {
				GameObject object2 = temp_objects.get(j);
			
				if(object1.getRectangle().intersects(object2.getRectangle())) {
					object1.collision(object2.getId());
					object2.collision(object1.getId());
				}
			}
		}
	}
	
//	Render all GameObjects
	public void render(Graphics2D g2d) {
		for(int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g2d);
		}
	}
	
//	Add an object to handler
	public void addObject(GameObject object) {
		objects.add(object);
	}
//	Remove an object from handler
	public void removeObject(GameObject object) {
		objects.remove(object);
	}
	
//	Remove all objects
	public void removeAll() {
		objects.removeAll(objects);
	}

//	Remove all enemies
	public void removeEnemies() {
		for(int i = 0; i < objects.size(); i++) {
			GameObject temp = objects.get(i);
			
			if(temp.getId() != ID.PLAYER && temp.getId() != ID.EXPLOSIONSHARDENEMY) {
				this.removeObject(temp);
				i--;
			}
		}
		
	}
	
//	Get a list of all objects
	public ArrayList<GameObject> getObjects() {
		return objects;
	}

//	Get the random instance
	public Random getRandom() {
		return random;
	}
	
//	Get keyListener
	public KeyboardListener getKeyboardListener() {
		return keyboardListener;
	}
}
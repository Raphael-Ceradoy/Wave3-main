package com.wave3.objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.wave3.gameElement.Handler;
import com.wave3.main.GameWindow;

public abstract class GameObject {
	protected Handler handler;
	protected Rectangle rectangle;	
	protected float x, y, width, height;
	protected float velX, velY;
	protected ID id;
	protected Map<String, Boolean> hit = new HashMap<String,Boolean>();
	
//	Constructors
	public GameObject(Handler handler) {
		this.handler = handler;
		
		hit.put("up", false);
		hit.put("down", false);
		hit.put("right", false);
		hit.put("left", false);
	}
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public GameObject(Handler handler, float x, float y) {
		this(handler);
		this.x = x;
		this.y = y;
	}
	public GameObject(Handler handler, float x, float y, float velX, float velY) {
		this(handler, x, y);
		this.velX = velX;
		this.velY = velY;
	}
	
//	Methods that every GameObject should have
	public abstract void tick();
	public abstract void render(Graphics2D g2d);
	public abstract void collision(ID id);
	
//	Used to stop/indicate when a wall is hit
	public void clamp() {
		if(x < 0) {
			x = 0;
			hit.put("left", true);
		} else {hit.put("left", false);}
		
		if(x + width > GameWindow.GAMEWIDTH) {
			x = GameWindow.GAMEWIDTH - width;
			hit.put("right", true);
		} else {hit.put("right", false);}
		
		if(y < 0) {
			y = 0;
			hit.put("up", true);
		} else {hit.put("up", false);}
		
		if(y + height > GameWindow.GAMEHEIGHT) {
			y = GameWindow.GAMEHEIGHT - height;
			hit.put("down", true);
		} else {hit.put("down", false);}
	}
	
//	Getters and Setters
	public Rectangle getRectangle() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
	public void setRectangle(Rectangle newRectangle) {
		this.rectangle = newRectangle;
	}
	public float getX() {
		return this.x;
	}
	public float getY() {
		return this.y;
	}
	public float getWidth() {
		return this.width;
	}
	public float getHeight() {
		return this.height;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void setWidth(float width) {
		this.width = width;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getVelX() {
		return this.velX;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}
	public float getVelY() {
		return this.velY;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	public ID getId() {
		return this.id;
	}
	

}
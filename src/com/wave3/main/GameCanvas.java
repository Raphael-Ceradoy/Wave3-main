package com.wave3.main;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas{
	private BufferStrategy bufferStrategy;
	
	public void createBufferStrategy() {
		this.createBufferStrategy(2);
		bufferStrategy = this.getBufferStrategy();
	}
}

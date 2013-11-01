package com.pokemongame;

public class Background extends Thing {
	private final int VX = -10;
	
	Background(int x, int y) {
		xPos = x;
		yPos = y;
		vx = VX;
		vy = 0;
	}
	
	public void update() {
		xPos += vx;
		
		if (xPos <= -1280) {
			xPos += 2560;
		}
	}
}

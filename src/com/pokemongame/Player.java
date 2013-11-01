package com.pokemongame;

public class Player extends Thing{
	private boolean jumped = false;
	private final int XHOME = 100, YHOME = 540;
	
	Player() {
		xPos = XHOME;
		yPos = YHOME;
		vx = 0;
		vy = 1;
	}
	
	public void update() {
		if (vx < 0) {
			xPos += xPos;
		}
		else if (vx == 0) {
			
		}
		else {
			if (xPos <= 150) {
				xPos += vx;
			}
		}

		
		if (jumped) {
			vy += 1;
			if ((yPos + vy) >= YHOME) {
				yPos = YHOME;
				vy = 0;
				jumped = false;
			}
			else {
				yPos += vy;
			}
		}
		
		if ((xPos+vx) <= 60) {
			xPos = 61;
		}
	}

	public void moveRight() {
		vx = 6;
	}
	
	public void moveLeft() {
		vx = -6;
	}
	
	public void stop() {
		vx = 0;
	}
	
	public void jump() {
		if (!jumped) {
			System.out.println("Jumps");
			vy = -30;
			jumped = true;
		}
	}
}

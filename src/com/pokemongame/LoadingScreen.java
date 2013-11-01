package com.pokemongame;

import com.janetkim.framework.Graphics;
import com.janetkim.framework.Graphics.ImageFormat;
import com.janetkim.framework.Screen;
import com.janetkim.framework.Game;

public class LoadingScreen extends Screen {
	
	public LoadingScreen(Game game) {
		super(game);
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		//Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.background = g.newImage("game-background2.jpg", ImageFormat.RGB565);
		Assets.character1 = g.newImage("runleft.png", ImageFormat.RGB565);
		Assets.character2 = g.newImage("standing.png", ImageFormat.RGB565);
		Assets.character3 = g.newImage("runright.png", ImageFormat.RGB565);
		//Assets.click = game.getAudio().createSound("theme.mp3");
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub

	}

}

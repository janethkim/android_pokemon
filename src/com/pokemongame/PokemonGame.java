package com.pokemongame;

import com.janetkim.framework.Screen;
import com.janetkim.framework.implementation.AndroidGame;

public class PokemonGame extends AndroidGame {
	
	public Screen getInitScreen() {
		return new LoadingScreen(this);
	}
	

	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}
	
	public void onPause() {
		super.onPause();
		
	}
}

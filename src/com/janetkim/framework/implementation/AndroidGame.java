package com.janetkim.framework.implementation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.janetkim.framework.Audio;
import com.janetkim.framework.FileIO;
import com.janetkim.framework.Game;
import com.janetkim.framework.Graphics;
import com.janetkim.framework.Input;
import com.janetkim.framework.Screen;

public class AndroidGame extends Activity implements Game {
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); //calling superclass constructor
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//no Title Bar which is provided by default
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//check if it's orientation portrait or landscape
		boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
		int frameBufferWidth = isPortrait ? 800 : 1280;
		int frameBufferHeight = isPortrait ? 1280 : 800;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
				frameBufferHeight, Config.RGB_565);
		
		//adjust to device's aspect ratio?
		//Point size = new Point();
		//getWindowManager().getDefaultDisplay().getSize(size);
		float scaleX = (float) frameBufferWidth / getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight / getWindowManager().getDefaultDisplay().getHeight();
		
		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(getAssets(), frameBuffer);
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		screen = getInitScreen();
		setContentView(renderView);
		
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK , "MyGame");
	}
	
	public void onResume() {
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
	}
	
	public void onPause() {
		super.onPause();
		wakeLock.release();
		screen.pause();
		renderView.pause();
		
		if (isFinishing())
			screen.dispose();
	}
	
	@Override
	public Audio getAudio() {
		// TODO Auto-generated method stub
		return audio;
	}

	@Override
	public Input getInput() {
		// TODO Auto-generated method stub
		return input;
	}

	@Override
	public FileIO getFileIO() {
		// TODO Auto-generated method stub
		return fileIO;
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return graphics;
	}

	@Override
	public void setScreen(Screen screen) {
		// TODO Auto-generated method stub
		if (screen == null)
			throw new IllegalArgumentException("Screen can't be null");
		
		this.screen.pause();
		this.screen.dispose(); //what does this dispose do?
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	@Override
	public Screen getCurrentScreen() {
		// TODO Auto-generated method stub
		return screen;
	}

	@Override
	public Screen getInitScreen() {
		//what does this function do
		// TODO Auto-generated method stub
		return null;
	}

}

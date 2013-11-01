package com.pokemongame;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Paint;

import com.janetkim.framework.Game;
import com.janetkim.framework.Graphics;
import com.janetkim.framework.Image;
import com.janetkim.framework.Input.TouchEvent;
import com.janetkim.framework.Screen;

public class GameScreen extends Screen {
	List<myThing> things = new ArrayList<myThing>();
	private Player player;
	Animation charAnim;
	
	private class myThing {
		Thing thing;
		Image sprite;
		Animation anim;
		
		myThing(Thing t, Image s) {
			thing = t;
			sprite = s;
			anim = null;
		}
		
		myThing(Thing t, Image s, Animation a) {
			thing = t;
			sprite = s;
			anim = a;
		}
	}
	
	enum GameState {
		Ready, Running, Paused, GameOver
	}
	
	GameState state = GameState.Ready;
	int livesleft = 3;
	Paint paint;
	
	public GameScreen(Game game) {
		super(game);
		
		charAnim = new Animation();
		charAnim.addFrame(Assets.character1, 50);
		charAnim.addFrame(Assets.character2, 50);
		charAnim.addFrame(Assets.character3, 50);
		things.add(new myThing(new Background(0,0), Assets.background));
		things.add(new myThing(new Background(1280,0), Assets.background));
		player = new Player();
		things.add(new myThing(player, Assets.character1, charAnim));
		
		paint = new Paint();
		paint.setTextSize(30);
	}
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        // We have four separate update methods in this example.
        // Depending on the state of the game, we call different update methods.
        // Refer to Unit 3's code. We did a similar thing without separating the
        // update methods.
        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
	}
	
	public void animate() {
		for (myThing t: things) {
			if (t.anim != null) {
				t.anim.update(10);
			}
		}
	}
	 private void updateReady(List<TouchEvent> touchEvents) {
	       
	        // This example starts with a "Ready" screen.
	        // When the user touches the screen, the game begins.
	        // state now becomes GameState.Running.
	        // Now the updateRunning() method will be called!
	       
	        if (touchEvents.size() > 0)
	            state = GameState.Running;
	    }

	    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
	       
	        //This is identical to the update() method from our Unit 2/3 game.
	       for (myThing t : things) {
	    	   t.thing.update();
	    	   if (t.anim != null) {
	    		   t.sprite = t.anim.getImage();
	    	   }
	       }
	       animate();
	    	
	       
	        // 1. All touch input is handled here:
	        int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);

	            if (event.type == TouchEvent.TOUCH_DOWN) {
	            	player.jump();
	            	
	                if (event.x < 640) {
	                    // Move left.
	                }

	                else if (event.x > 640) {
	                    // Move right.
	                }

	            }

	            if (event.type == TouchEvent.TOUCH_UP) {

	                if (event.x < 640) {
	                    // Stop moving left.
	                }

	                else if (event.x > 640) {
	                    // Stop moving right. }
	                }
	            }

	           
	        }
	       
	        // 2. Check miscellaneous events like death:
	       
	        if (livesleft == 0) {
	            state = GameState.GameOver;
	        }
	       
	       
	        // 3. Call individual update() methods here.
	        // This is where all the game updates happen.
	        // For example, robot.update();
	    }

	    private void updatePaused(List<TouchEvent> touchEvents) {
	        int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            if (event.type == TouchEvent.TOUCH_UP) {

	            }
	        }
	    }
	    
	    private void updateGameOver(List<TouchEvent> touchEvents) {
	        int len = touchEvents.size();
	        for (int i = 0; i < len; i++) {
	            TouchEvent event = touchEvents.get(i);
	            if (event.type == TouchEvent.TOUCH_UP) {
	                if (event.x > 300 && event.x < 980 && event.y > 100
	                        && event.y < 500) {
	                    nullify();
	                    game.setScreen(new MainMenuScreen(game));
	                    return;
	                }
	            }
	        }

	    }
	    
	    private void nullify() {

	        // Set all variables to null. You will be recreating them in the
	        // constructor.
	        paint = null;

	        // Call garbage collector to clean up memory.
	        System.gc();
	    }
	
	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		for (myThing t : things) {
			g.drawImage(t.sprite, t.thing.xPos, t.thing.yPos);
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if (state == GameState.Running)
            state = GameState.Paused;
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
		pause();
	}

}

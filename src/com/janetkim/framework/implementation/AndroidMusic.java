package com.janetkim.framework.implementation;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import com.janetkim.framework.Music;

public class AndroidMusic implements Music, OnCompletionListener, OnSeekCompleteListener, OnPreparedListener,
OnVideoSizeChangedListener {
	MediaPlayer mediaPlayer;
	boolean isPrepared = false;
	
	public AndroidMusic(AssetFileDescriptor assetDescriptor){
		mediaPlayer = new MediaPlayer();
	      try {
	            mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
	                    assetDescriptor.getStartOffset(),
	                    assetDescriptor.getLength());
	            mediaPlayer.prepare();
	            isPrepared = true;
	            mediaPlayer.setOnCompletionListener(this);
	            mediaPlayer.setOnSeekCompleteListener(this);
	            mediaPlayer.setOnPreparedListener(this);
	            mediaPlayer.setOnVideoSizeChangedListener(this);
	           
	        } catch (Exception e) {
	            throw new RuntimeException("Couldn't load music");
	        }
	}
	@Override
	public void play() {
		// TODO Auto-generated method stub
       if (this.mediaPlayer.isPlaying())
            return;

        try {
            synchronized (this) {
                if (!isPrepared)
                    mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
      if (this.mediaPlayer.isPlaying() == true){
    	  this.mediaPlayer.stop();
      
    	  synchronized (this) {
    		  isPrepared = false;
    	  }
      }
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		if (mediaPlayer.isPlaying())
			mediaPlayer.pause();
	}

	@Override
	public void setLooping(boolean islooping) {
		// TODO Auto-generated method stub
		 mediaPlayer.setLooping(islooping);
	}

	@Override
	public void setVolume(float volume) {
		// TODO Auto-generated method stub
		 mediaPlayer.setVolume(volume, volume);
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return mediaPlayer.isPlaying();
	}

	@Override
	public boolean isStopped() {
		// TODO Auto-generated method stub
		return !isPrepared;
	}

	@Override
	public boolean isLooping() {
		// TODO Auto-generated method stub
		return mediaPlayer.isLooping();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	  if (this.mediaPlayer.isPlaying()){
          this.mediaPlayer.stop();
      }
      this.mediaPlayer.release();
	}

	@Override
	public void seekBegin() {
		// TODO Auto-generated method stub
		mediaPlayer.seekTo(0);
	}
	@Override
	public void onVideoSizeChanged(MediaPlayer arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPrepared(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		synchronized (this) {
            isPrepared = true;
        }
	}
	@Override
	public void onSeekComplete(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onCompletion(MediaPlayer player) {
		// TODO Auto-generated method stub
        synchronized (this) {
            isPrepared = false;
        }
	}

}

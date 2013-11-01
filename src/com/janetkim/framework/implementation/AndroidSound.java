package com.janetkim.framework.implementation;

import android.media.SoundPool;

import com.janetkim.framework.Sound;

public class AndroidSound implements Sound {
	int soundId;
	SoundPool soundPool;
	
	public AndroidSound(SoundPool soundpool, int soundId) {
		this.soundId = soundId;
		this.soundPool = soundpool;
	}
	
	@Override
	public void play(float volume) {
		// TODO Auto-generated method stub
		soundPool.play(soundId, volume, volume, 0, 0, 1);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		soundPool.unload(soundId);
	}

}

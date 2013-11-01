package com.janetkim.framework.implementation;

import android.graphics.Bitmap;

import com.janetkim.framework.Graphics.ImageFormat;
import com.janetkim.framework.Image;

public class AndroidImage implements Image {
	Bitmap bitmap;
	ImageFormat format;
	
	public AndroidImage(Bitmap bitmap, ImageFormat format) {
		this.bitmap = bitmap;
		this.format = format;
	}
	
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return bitmap.getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return bitmap.getHeight();
	}

	@Override
	public ImageFormat getFormat() {
		// TODO Auto-generated method stub
		return format;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		bitmap.recycle();
	}

}

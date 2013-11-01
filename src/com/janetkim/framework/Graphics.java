package com.janetkim.framework;

import android.graphics.Paint;

public interface Graphics {
	public static enum ImageFormat {
		ARGB8888, ARGB4444, RGB565
	}
	
	public Image newImage(String fileName, ImageFormat format);
	public void clearScreen(int color);
	public void drawImage(Image image, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight);
	public void drawImage(Image image, int x, int y);
	void drawString(String text, int x, int y, Paint paint);
	public int getWidth();
	public int getHeight();
	public void drawARGB(int i, int j, int k, int l);
}

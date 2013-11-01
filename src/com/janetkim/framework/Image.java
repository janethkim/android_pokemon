package com.janetkim.framework;

import com.janetkim.framework.Graphics.ImageFormat;

public interface Image {
	public int getWidth();
	public int getHeight();
	public ImageFormat getFormat();
	public void dispose();
}

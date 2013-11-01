package com.janetkim.framework.implementation;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.janetkim.framework.Graphics;
import com.janetkim.framework.Image;

public class AndroidGraphics implements Graphics {
	AssetManager assets;
	Bitmap frameBuffer;
	Canvas canvas;
	Paint paint;
	Rect srcRect = new Rect(); //source rectangle? for collisions?
	Rect dstRect = new Rect();
	
	public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
		this.assets = assets;
		this.frameBuffer = frameBuffer;
		this.canvas = new Canvas(frameBuffer);
		this.paint = new Paint();
	}
	
	@Override
	public Image newImage(String fileName, ImageFormat format) {
		// TODO Auto-generated method stub
		Config config = null;
		if (format == ImageFormat.RGB565)
			config = Config.RGB_565;
		else if (format == ImageFormat.ARGB4444)
			config = Config.ARGB_4444;
		else
			config = Config.ARGB_8888;
		
		Options options = new Options();
		options.inPreferredConfig = config;
		
		InputStream in = null;
		Bitmap bitmap = null;
		try {
			in = assets.open(fileName);
			bitmap = BitmapFactory.decodeStream(in, null, options);
			if (bitmap == null)
				throw new RuntimeException("Couldn't load bitmap from asset '" +
						fileName + "'");
			
		} catch(IOException e) {
			throw new RuntimeException("Couldn't load bitmap from asset '" 
					+ fileName + "'");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch(IOException e) {
				}
			}
		}
		
		//why are we doing this twice?
		if (bitmap.getConfig() == Config.RGB_565)
			format = ImageFormat.RGB565;
		else if (bitmap.getConfig() == Config.ARGB_4444)
			format = ImageFormat.ARGB4444;
		else
			format = ImageFormat.ARGB8888;
		
		return new AndroidImage(bitmap, format);
	}

	@Override
	public void clearScreen(int color) {
		// TODO Auto-generated method stub
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
				(color & 0xff));
	}

	@Override
	public void drawImage(Image image, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight) {
		// TODO Auto-generated method stub
		srcRect.left = srcX;
		srcRect.top = srcY;
		srcRect.right = srcX + srcWidth;
		srcRect.bottom = srcY + srcHeight;
		
		dstRect.left = x;
		dstRect.top = y;
		dstRect.right = x + srcWidth;
		dstRect.bottom = y + srcHeight;
		
		canvas.drawBitmap(((AndroidImage) image).bitmap, srcRect, dstRect, null);
	}

	@Override
	public void drawImage(Image image, int x, int y) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(((AndroidImage) image).bitmap, x, y, null);
	}

	@Override
	public void drawString(String text, int x, int y, Paint paint) {
		// TODO Auto-generated method stub
		canvas.drawText(text, x, y, paint);
	}

    public void drawScaledImage(Image Image, int x, int y, int width,
    		int height, int srcX, int srcY, int srcWidth, int srcHeight){
        
        
        srcRect.left = srcX;
           srcRect.top = srcY;
           srcRect.right = srcX + srcWidth;
           srcRect.bottom = srcY + srcHeight;
          
          
           dstRect.left = x;
           dstRect.top = y;
           dstRect.right = x + width;
           dstRect.bottom = y + height;
          
      
          
           canvas.drawBitmap(((AndroidImage) Image).bitmap, srcRect, dstRect, null); 
       }

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return frameBuffer.getWidth();
	}
	
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return frameBuffer.getHeight();
	}

	@Override
	public void drawARGB(int i, int j, int k, int l) {
		// TODO Auto-generated method stub
		paint.setStyle(Style.FILL);
		canvas.drawARGB(i, j, k, l);
	}

}

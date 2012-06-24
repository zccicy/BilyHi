package com.zccicy.tools;

import android.graphics.drawable.Drawable;

public class DrawableCacheList { 
	private static final int CAPACITY = 100;
	public static LruCache<Integer, Drawable> drawableCache = new LruCache<Integer, Drawable>(CAPACITY);
	public static LruCache<Integer, Drawable> commentDrawableCache=new LruCache<Integer, Drawable>(CAPACITY);
 
}

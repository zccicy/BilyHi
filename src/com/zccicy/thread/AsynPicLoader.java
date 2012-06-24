package com.zccicy.thread;

import android.graphics.drawable.Drawable;

import com.zccicy.common.util.HttpUtil;

public class AsynPicLoader extends Thread{

	private String picUrl;
	private Drawable drawable;
	private PicCompleteListener listener;
	public AsynPicLoader(String url,PicCompleteListener listener) {
		picUrl=url;
		this.listener=listener;
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		drawable=HttpUtil.getDrawableFromWeb(picUrl);
		listener.onDrawLoadComplete(drawable);
		super.run();
	}
	public Drawable getDrawable() {
		return drawable;
	}
	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	
	public interface PicCompleteListener
	{
		void onDrawLoadComplete(Drawable drawable);
	}

}

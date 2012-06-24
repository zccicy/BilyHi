package com.zccicy.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class HomeGalleryAdapter extends BaseAdapter{

	private List<ImageView> imageViews;
	private Context context;
	public HomeGalleryAdapter(Context context,List<ImageView> imageViews) {
		super();
		this.imageViews=imageViews;
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageViews.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return imageViews.get(position);
	}
	

	
}

package com.zccicy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.zccicy.R;
import com.zccicy.model.AvInfo;
import com.zccicy.model.AvList;
import com.zccicy.tools.DrawableCacheList;

public class AvListAdapter extends BaseAdapter {

	private List<AvInfo> list = new ArrayList<AvInfo>();
	private Context context;
	private LayoutInflater inflater;
	private Drawable iconNotFound;
	private ViewHolder holder;

	public AvListAdapter(Context context) {

		super();
		this.list=new ArrayList<AvInfo>();
		this.context = context;
		this.inflater = LayoutInflater.from(context);
		this.iconNotFound = context.getResources().getDrawable(R.drawable.icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
		if (convertView == null) {
			convertView = this.inflater.inflate(R.layout.item_avlist, null);
			holder = new ViewHolder();

			holder.avCover = (ImageView) convertView
					.findViewById(R.id.list_av_cover);
			holder.avState = (TextView) convertView
					.findViewById(R.id.list_av_state);
			holder.avName = (TextView) convertView
					.findViewById(R.id.list_av_name);
			holder.avDesc=(TextView)convertView.findViewById(R.id.list_av_desc);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final AvInfo avInfo = list.get(position);
		holder.avName.setText(avInfo.getTitle());
		holder.avState.setText("再生数" + avInfo.getPlay_count() + "   评论数"
				+ avInfo.getReview_count() + "\n弹幕数"
				+ avInfo.getVideo_review_count() + "   收藏数"
				+ avInfo.getFavorites());
		holder.avDesc.setText(avInfo.getAv_desc());
		if (DrawableCacheList.drawableCache.get(avInfo.getAid()) != null) {
			holder.avCover.setImageDrawable(DrawableCacheList.drawableCache
					.get(avInfo.getAid()));
		} else {
			// TODO: handle exception
			holder.avCover.setImageDrawable(iconNotFound);
		}

		return convertView;
	}

	private static class ViewHolder {

		ImageView avCover;
		TextView avName;
		TextView avState;
		TextView playCount;
		TextView reviewCount;
		TextView videoReviewCount;
		TextView favorites;
		TextView author;
		TextView postTime;
		TextView avDesc;
	}

	public List<AvInfo> getList() {
		if (list != null)
			return list;
		else {
			list = new ArrayList<AvInfo>();
			return list;
		}
	}

	public void setList(List<AvInfo> list) {
		this.list = list;
	}

}

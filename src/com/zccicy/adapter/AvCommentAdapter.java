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
import android.widget.TextView;

import com.zccicy.R;
import com.zccicy.model.AvComment;
import com.zccicy.tools.DrawableCacheList;

public class AvCommentAdapter extends BaseAdapter {

	private List<AvComment> list;
	private LayoutInflater inflater;
	private Context context;
	private ViewHolder holder;
	private Drawable iconNotFound;

	public AvCommentAdapter(Context context) {
		super();
		list = new ArrayList<AvComment>();
		this.inflater = LayoutInflater.from(context);
		this.context = context;
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
			convertView = this.inflater.inflate(R.layout.item_comment, null);
			holder = new ViewHolder();

			holder.commentCover = (ImageView) convertView
					.findViewById(R.id.item_comment_cover);
			holder.commentName = (TextView) convertView
					.findViewById(R.id.item_comment_name);
			holder.commentContent = (TextView) convertView
					.findViewById(R.id.item_comment_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		AvComment comment = list.get(position);
		try {
			holder.commentName.setText(comment.getUser_name());
			holder.commentContent.setText(comment.getComment_content());
			if (DrawableCacheList.commentDrawableCache.get(comment.getUser_id())!=null)
			{
			holder.commentCover.setImageDrawable(DrawableCacheList.commentDrawableCache.get(comment.getUser_id()));	
			}
			else
			{
				holder.commentCover.setImageDrawable(iconNotFound);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return convertView;
	}

	public List<AvComment> getList() {
		return list;
	}

	public void setList(List<AvComment> list) {
		this.list = list;
	}

	private static class ViewHolder {

		ImageView commentCover;
		TextView commentName;
		TextView commentContent;

	}

}

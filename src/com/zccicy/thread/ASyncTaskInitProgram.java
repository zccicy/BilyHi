package com.zccicy.thread;

import android.content.Context;

import com.zccicy.common.thread.BaseAsyncTask;
import com.zccicy.common.util.CommonUtil;
import com.zccicy.common.util.DBHelper;
import com.zccicy.factory.SessionFactory;
import com.zccicy.listener.AsyncTaskListener;

public class ASyncTaskInitProgram extends BaseAsyncTask<Void, Integer, String> {

	private Context context;

	public ASyncTaskInitProgram(Context context,
			AsyncTaskListener<Integer, String> asyncTaskListener) {
		super(asyncTaskListener);
		this.context = context;

	}

	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		CommonUtil.initCategory(context);
		SessionFactory.initFactory(context);
		CommonUtil.initDataPath();
		DBHelper dbHelper = new DBHelper(context);
		return null;
	}

}

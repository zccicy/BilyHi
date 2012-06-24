package com.zccicy.common.thread;

import android.os.AsyncTask;

import com.zccicy.listener.AsyncTaskListener;

public class BaseAsyncTask<S, R, T> extends AsyncTask<S, R, T> {
	protected AsyncTaskListener<R, T> asyncTaskListener;

	public BaseAsyncTask(AsyncTaskListener<R, T> asyncTaskListener) {
		super();
		this.asyncTaskListener = asyncTaskListener;
	}

	@Override
	protected T doInBackground(S... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		asyncTaskListener.onCancelled();
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(T result) {
		// TODO Auto-generated method stub
		asyncTaskListener.onPostExecute(result);
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		asyncTaskListener.onPreExecute();
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(R... values) {
		// TODO Auto-generated method stub
		asyncTaskListener.onProgressUpdate(values);
		super.onProgressUpdate(values);
	}

}

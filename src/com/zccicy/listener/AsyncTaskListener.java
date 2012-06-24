package com.zccicy.listener;

public interface AsyncTaskListener <R,T>{

	public void onCancelled();
	public void onPreExecute();
	public void onPostExecute(T result);
	public void onProgressUpdate(R... values);
	public void doInBackground();
}

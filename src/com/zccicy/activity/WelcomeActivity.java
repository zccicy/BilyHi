package com.zccicy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.zccicy.R;
import com.zccicy.listener.AsyncTaskListener;
import com.zccicy.thread.ASyncTaskInitProgram;

public class WelcomeActivity extends Activity {
    /** Called when the activity is first created. */
	private ASyncTaskInitProgram initTask;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_welcome);
       
        initAsyncTask();        
    }

 

	private void initAsyncTask() {
		// TODO Auto-generated method stub
		initTask=new ASyncTaskInitProgram(getApplicationContext(), initProgramListener);
		initTask.execute();
	}



	 
	private AsyncTaskListener<Integer, String> initProgramListener=new AsyncTaskListener<Integer, String>() {

		@Override
		public void onCancelled() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPreExecute() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPostExecute(String result) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(WelcomeActivity.this,HomeActivity.class);
			startActivity(intent);
			WelcomeActivity.this.finish();
		}

		@Override
		public void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void doInBackground() {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	
}
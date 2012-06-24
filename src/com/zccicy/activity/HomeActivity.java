package com.zccicy.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import com.zccicy.R;
import com.zccicy.common.util.CommonUtil;
import com.zccicy.common.util.DialogUtil;
import com.zccicy.common.util.DialogUtil.DialogConfirmListener;
import com.zccicy.common.util.PackageUtil;
import com.zccicy.factory.SessionFactory;
import com.zccicy.model.AvList;

public class HomeActivity extends Activity {

	private Gallery homeGallery;
	private RelativeLayout homeRl;
	private int screenWidth;
	private int screenHeight;
	private List<ImageView> imageViews;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SessionFactory.getAppActivityList().add(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_home);
		screenWidth = this.getWindowManager().getDefaultDisplay().getWidth();

		screenHeight = this.getWindowManager().getDefaultDisplay().getHeight();
		imageViews = new ArrayList<ImageView>();
		initGallery();
		initBtn();

	}

	private void initBtn() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			Dialog dialog = DialogUtil.createConfirmDialog(HomeActivity.this,
					R.string.dialog_title_home_exit,
					R.string.dialog_content_home_exit, listener,getResources().getDrawable(R.drawable.ykl));
			dialog.show();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	DialogConfirmListener listener = new DialogConfirmListener() {

		@Override
		public android.content.DialogInterface.OnClickListener onViewDialogOK() {
			// TODO Auto-generated method stub
			return new android.content.DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					PackageUtil.closeApp(HomeActivity.this);
				}
			};
		}

		@Override
		public android.content.DialogInterface.OnClickListener onViewDialogCancel() {
			// TODO Auto-generated method stub
			return new android.content.DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}
			};
		}

		@Override
		public OnCancelListener onCancelDialog() {
			// TODO Auto-generated method stub
			return null;
		}
	}; 
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = new MenuInflater(getApplicationContext());
		inflater.inflate(R.menu.home_menu, menu);
		return true;
//		return super.onCreateOptionsMenu(menu);
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.home_search:
			Intent intent=new Intent(HomeActivity.this,SearchActivity.class);
			startActivity(intent);
			break;

		}
		return false;
	}

	private void initGallery() {
		// TODO Auto-generated method stub
		homeRl = (RelativeLayout) findViewById(R.id.rl_home);

		ImageView imageView = null;
		RelativeLayout.LayoutParams layoutParams = null;

		List<AvList> firstList = CommonUtil.getChildCategory(
				getApplicationContext(), 0);

		for (int i = 0; i < firstList.size(); i++) {
			try {
				final AvList avList = firstList.get(i);
				imageView = new ImageView(HomeActivity.this);

				InputStream is = getResources().getAssets().open(
						"c" + avList.getTid() + "_bg.png");

				Drawable drawable = Drawable.createFromStream(is, "");
				imageView.setImageDrawable(drawable);
				layoutParams = new RelativeLayout.LayoutParams(
						android.widget.RelativeLayout.LayoutParams.FILL_PARENT,
						android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
				layoutParams.height = (screenHeight - 30) / 6;
				layoutParams.topMargin = (screenHeight - 30) / 6 * (i);
				imageView.setScaleType(ScaleType.CENTER_CROP);
				imageView.setLayoutParams(layoutParams);
				imageView.setBackgroundColor(0xff00ff00);
				imageViews.add(imageView);
				imageView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						DialogUtil.createHomeIndexDialog(HomeActivity.this,
								avList).show();
					}
				});
				homeRl.addView(imageView);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}

	}
}

package com.zccicy.activity;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.zccicy.R;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.common.util.HttpUtil;
import com.zccicy.common.util.VideoRealAddressConvertionUtil;
import com.zccicy.model.AvInfo;
import com.zccicy.model.AvVideoInfo;
import com.zccicy.service.AvInfoService;
import com.zccicy.service.AvVideoService;
import com.zccicy.tools.DrawableCacheList;

public class AvInfoActivity extends Activity {

	private AvInfoService avInfoService;
	private AvVideoService avVideoService;

	private Handler mainHandler;
	private ProgressDialog progressDialog;
	private int aid;
	private AvInfo avInfo;
	private TextView author, postTime, playCount, reviewCount,
			videoReviewCount, favorites, pageCount, tags, avDesc, avTitle;
	private Button showCommentBtn;
	private LinearLayout avVideoInfoLL;
	private ImageView avCoverIv;
	private List<AvVideoInfo> avVideoInfos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_avinfo);
		initService();
		initHandler();
		initViews();
		initData();
	}

	private void initData() {
		// TODO Auto-generated method stub
		avVideoInfos = new ArrayList<AvVideoInfo>();

		aid = getIntent().getIntExtra(
				GlobalConstants.INTENT_EXTRA_NAME_AVINFO_AID, -1);
		showCommentBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AvInfoActivity.this,
						AvCommentActivity.class);
				intent.putExtra("comment_aid", aid);
				if (aid > 0)
					startActivity(intent);
			}
		});
		DataThread thread = new DataThread(aid);
		thread.start();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		progressDialog = new ProgressDialog(AvInfoActivity.this);
		progressDialog.setMessage("正在加载中");
		progressDialog.show();
		author = (TextView) findViewById(R.id.avinfo_author);
		postTime = (TextView) findViewById(R.id.avinfo_posttime);
		playCount = (TextView) findViewById(R.id.avinfo_playcount);
		reviewCount = (TextView) findViewById(R.id.avinfo_review);
		videoReviewCount = (TextView) findViewById(R.id.avinfo_video_review);
		favorites = (TextView) findViewById(R.id.avinfo_favorites);
		pageCount = (TextView) findViewById(R.id.avinfo_page_count);
		tags = (TextView) findViewById(R.id.avinfo_tags);
		avDesc = (TextView) findViewById(R.id.avinfo_desc);
		avCoverIv = (ImageView) findViewById(R.id.avinfo_cover_iv);
		avVideoInfoLL = (LinearLayout) findViewById(R.id.avinfo_pages_area);
		showCommentBtn = (Button) findViewById(R.id.show_comment_btn);
		avTitle = (TextView) findViewById(R.id.avinfo_title);

	}

	private void initHandler() {
		// TODO Auto-generated method stub
		mainHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case GlobalConstants.MSG_AVINFO_MAIN_INITDATA:
					initAvInfoDetail();
					if (progressDialog.isShowing())
						progressDialog.dismiss();

					break;
				case GlobalConstants.MSG_AVINFO_MAIN_INIT_PART:
					initAvVideoInfo();
					break;

				default:
					break;
				}
			}

		};
	}

	protected void initAvVideoInfo() {
		// TODO Auto-generated method stub
		for (int i = 0; i < avVideoInfos.size(); i++) {
			final AvVideoInfo avVideoInfo = avVideoInfos.get(i);
			Button button = new Button(AvInfoActivity.this);
			Button webViewBtn = new Button(AvInfoActivity.this);
			Button faPlayerBtn = new Button(AvInfoActivity.this);
			LayoutParams layoutParams = new LayoutParams(
					LayoutParams.MATCH_PARENT, 100);
			faPlayerBtn.setText("视频片段" + (i + 1) + "  FaPlayer"
					+ avVideoInfo.getVideo_from_src());
			faPlayerBtn.setBackgroundResource(R.drawable.bilibili_list_bg);
			faPlayerBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
//					String url="";
					if (avVideoInfo.getUrlList().size()<=0)
						return;
					
					String url = avVideoInfo.getUrlList().get(0);
//					System.out.println(avVideoInfo.getUrlList().toString());
//					System.out.println(avVideoInfo.getUrlList().size());
//					url="";
					Intent intent = new Intent( );
					try {

						URLConnection conn = new URL(url).openConnection();
						conn.getContent();
						url = conn.getURL().toString();

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						return;
					}
					if (url.length() > 0) {

						ArrayList<String> playlist = new ArrayList<String>();
						playlist.add(url);
						intent.putExtra("selected", 0);
						intent.putExtra("playlist", playlist);
						startActivity(intent);
					}
				}
			});
			button.setText("视频片段" + (i + 1) + "  WebViewPlayer"
					+ avVideoInfo.getVideo_from_src());
			button.setBackgroundResource(R.drawable.bilibili_list_bg);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// Intent intent=new
					// Intent(AvInfoActivity.this,YoooPlayerActivity.class);
					// intent.putExtra("videoPart",avVideoInfo);
					// startActivity(intent);
					Intent intent = new Intent(AvInfoActivity.this,
							WebViewPlayer.class);
					System.out.println("url----" + avVideoInfo.toString());
					intent.putExtra("flashurl", avVideoInfo.getOffsite());
					startActivity(intent);

				}
			});

			// System.out.println("vid" + avVideoInfos.get(i).getVid());
			// System.out.println("src" + avVideoInfos.get(i).getOffsite());

			webViewBtn.setText("视频片段" + (i + 1) + " Mobo"
					+ avVideoInfos.get(i).getVideo_from_src());
			webViewBtn.setBackgroundResource(R.drawable.bilibili_list_bg);
			webViewBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String url = avVideoInfo.getUrlList().get(0);
					Intent intent = new Intent(Intent.ACTION_VIEW);
					try {

						URLConnection conn = new URL(url).openConnection();
						conn.getContent();

						intent.setDataAndType(
								Uri.parse(conn.getURL().toString()),
								"video/flv");

						startActivity(intent);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					/*
					 * if (!avVideoInfo.getOffsite().equals("")) {
					 * System.out.println("offsite"+avVideoInfo.getOffsite());
					 * Uri uri = Uri.parse(avVideoInfo.getOffsite()); Intent
					 * intent = new Intent(Intent.ACTION_VIEW, uri);
					 * 
					 * startActivity(intent); }
					 */
				}
			});
			avVideoInfoLL.addView(button, layoutParams);
			avVideoInfoLL.addView(webViewBtn, layoutParams);
			avVideoInfoLL.addView(faPlayerBtn, layoutParams);

		}
	}

	private void initAvInfoDetail() {
		// TODO Auto-generated method stub
		avTitle.setText(avInfo.getTitle());
		author.setText(avInfo.getAuthor());
		postTime.setText(avInfo.getPost_time());
		playCount.setText(avInfo.getPlay_count() + "");
		reviewCount.setText(avInfo.getReview_count() + "");
		videoReviewCount.setText(avInfo.getVideo_review_count() + "");
		favorites.setText(avInfo.getFavorites() + "");
		pageCount.setText(avInfo.getPage_count() + "");
		tags.setText(avInfo.getTag());
		avDesc.setText(avInfo.getAv_desc());
		if (DrawableCacheList.drawableCache.get(avInfo.getAid()) != null) {
			avCoverIv.setImageDrawable(DrawableCacheList.drawableCache
					.get(avInfo.getAid()));
		} else {

			avCoverIv.setImageResource(R.drawable.icon);
		}
	}

	private void initService() {
		// TODO Auto-generated method stub
		avInfoService = new AvInfoService(AvInfoActivity.this);
		avVideoService = new AvVideoService(AvInfoActivity.this);

	}

	class DataThread extends Thread {
		private int aid = -1;

		public DataThread(int aid) {
			super();
			this.aid = aid;
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			avInfo = avInfoService.getAvInfo(aid);

			System.out.println(avInfo.toString());

			mainHandler
					.sendEmptyMessage(GlobalConstants.MSG_AVINFO_MAIN_INITDATA);
			if (DrawableCacheList.drawableCache.get(avInfo.getAid()) == null) {
				Drawable drawable = HttpUtil.getDrawableFromWeb(avInfo
						.getCover_pic_url());
				if (drawable != null) {
					DrawableCacheList.drawableCache.put(avInfo.getAid(),
							drawable);
					mainHandler
							.sendEmptyMessage(GlobalConstants.MSG_AVINFO_MAIN_INITDATA);
				}
			}
			for (int i = 0; i < avInfo.getPage_count(); i++) {
				AvVideoInfo avVideoInfo = avVideoService.getAvVideoInfo(
						avInfo.getAid(), i + 1);
				System.out.println(avVideoInfo.toString());
				avVideoInfos.add(avVideoInfo);
				avVideoInfo.setUrlList(VideoRealAddressConvertionUtil
						.getVideoRealAddressByVid(
								avVideoInfo.getVideo_from_src(),
								avVideoInfo.getVid()));
			}

			mainHandler
					.sendEmptyMessage(GlobalConstants.MSG_AVINFO_MAIN_INIT_PART);

			super.run();
		}

	}

}

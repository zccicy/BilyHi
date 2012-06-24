package com.zccicy.activity;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.zccicy.R;
import com.zccicy.adapter.AvCommentAdapter;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.common.util.HttpUtil;
import com.zccicy.common.util.MessageHelper;
import com.zccicy.model.AvComment;
import com.zccicy.model.AvInfo;
import com.zccicy.service.AvCommentService;
import com.zccicy.thread.AsynPicLoader;
import com.zccicy.thread.AsynPicLoader.PicCompleteListener;
import com.zccicy.tools.DrawableCacheList;

public class AvCommentActivity extends Activity {

	private AvCommentAdapter commentAdapter;
	private AvCommentService avCommentService;
	private HandlerThread ht;
	private Handler mainHandler;
	private Handler threadHandler;
	private ListView commentListView;
	private int pageSize = 10;
	private int pageNum = 1;
	private int aid = 0;
	private ProgressDialog progressDialog;
	private boolean loadingDataFlag = false;
	private View footView;
	private boolean dataFinish=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_comment);
		initViews();
		initService();
		initData();
		initHandler();
		progressDialog.show();
		threadHandler
				.sendEmptyMessage(GlobalConstants.MSG_AVCOMMENT_THREAD_INIT_DATA);

	}

	private void initHandler() {
		// TODO Auto-generated method stub
		ht = new HandlerThread("comment_ht");
		ht.start();
		threadHandler = new Handler(ht.getLooper()) {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case GlobalConstants.MSG_AVCOMMENT_THREAD_INIT_DATA:
					initCommentData();
					 
					break;
				case GlobalConstants.MSG_AVCOMMENT_THREAD_INIT_COVER:
					initCommentIcon((List<AvComment>) msg.obj);
					break;
				case GlobalConstants.MSG_AVCOMMENT_THREAD_LIST_UPDATE:

					break;

				default:
					break;
				}
			}

		};
		mainHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub

				super.handleMessage(msg);
				switch (msg.what) {
				case GlobalConstants.MSG_AVCOMMENT_MAIN_INIT_DATA:
					List<AvComment> tmpList=(List<AvComment>)msg.obj;
					for (int i = 0; i < tmpList.size(); i++) {
						commentAdapter.getList().add(tmpList.get(i));
					}
					if (progressDialog.isShowing())
						progressDialog.dismiss();
					footView.setVisibility(View.GONE);
					initAvInfoComment();
					loadingDataFlag = false;
					break;
				case GlobalConstants.MSG_AVCOMMENT_MAIN_INIT_COVER:
					initAvInfoComment();
					break;
				case GlobalConstants.MSG_AVCOMMENT_MAIN_UPDATE_LIST:
					break;
				default:
					break;
				}
			}

		};
	}

	private void initData() {
		// TODO Auto-generated method stub
		commentAdapter = new AvCommentAdapter(AvCommentActivity.this);
		commentListView.setAdapter(commentAdapter);
		aid = getIntent().getIntExtra("comment_aid", 0);
	}

	private void initService() {
		// TODO Auto-generated method stub
		avCommentService = new AvCommentService(getApplicationContext());
	}

	private void initViews() {
		// TODO Auto-generated method stub
		commentListView = (ListView) findViewById(R.id.list_comment);
		progressDialog = new ProgressDialog(AvCommentActivity.this);
		progressDialog.setMessage("读取评论中");

		commentListView.setOnScrollListener(listScrollListener);
		footView = LayoutInflater.from(this).inflate(R.layout.list_foot, null);

		commentListView.addFooterView(footView, null, false);

		footView.setVisibility(View.GONE);

	}

	protected void initAvInfoComment() {
		commentAdapter.notifyDataSetChanged();
		// TODO Auto-generated method stub

	}

	private void initCommentIcon(List<AvComment> picList) {
		// TODO Auto-generated method stub

		for (int i = 0; i < picList.size(); i++) {
			final AvComment avComment = picList.get(i);

			AsynPicLoader loader = new AsynPicLoader(
					avComment.getUser_face_icon(), new PicCompleteListener() {

						@Override
						public void onDrawLoadComplete(Drawable drawable) {
							// TODO Auto-generated method stub

							DrawableCacheList.commentDrawableCache.put(
									avComment.getUser_id(), drawable);
							mainHandler
									.sendEmptyMessage(GlobalConstants.MSG_AVCOMMENT_MAIN_INIT_COVER);
						}
					});
			loader.start();
		}

	}

	public void initCommentData() {
		// TODO Auto-generated method stub
		if (aid <= 0)
			return;
		List<AvComment> list = avCommentService.getCommnetList(aid, pageSize,
				pageNum);
		if (list==null||list.size()<=0)
			dataFinish=true;
		try {

			mainHandler.sendMessage(MessageHelper.obtainCustomMessage(
					GlobalConstants.MSG_AVCOMMENT_MAIN_INIT_DATA, list));
			threadHandler.sendMessage(MessageHelper.obtainCustomMessage(
					GlobalConstants.MSG_AVCOMMENT_THREAD_INIT_COVER, list));
		} catch (Exception e) {
			// TODO: handle exception
		}

	};

	OnScrollListener listScrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub
			// if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
			if ((firstVisibleItem + visibleItemCount == totalItemCount)
					&& totalItemCount != 0 && !loadingDataFlag
					&& totalItemCount > 1&&!dataFinish) {
				loadingDataFlag = true;
				footView.setVisibility(View.VISIBLE);

				pageNum = pageNum + 1;

				threadHandler
						.sendEmptyMessage(GlobalConstants.MSG_AVCOMMENT_THREAD_INIT_DATA);
			}

		}
	};

}

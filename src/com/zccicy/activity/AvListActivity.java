package com.zccicy.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Messenger;
import android.provider.ContactsContract.Contacts.Data;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.zccicy.R;
import com.zccicy.adapter.AvListAdapter;
import com.zccicy.common.util.DialogUtil;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.common.util.MessageHelper;
import com.zccicy.model.AvInfo;
import com.zccicy.service.AvListService;
import com.zccicy.thread.AsynPicLoader;
import com.zccicy.thread.AsynPicLoader.PicCompleteListener;
import com.zccicy.tools.DrawableCacheList;

public class AvListActivity extends Activity {

	private Handler mainHandler;
	private AvListService avListService;
	private AvListAdapter adapter;
	private ListView listView;
	private ProgressDialog progressDialog;
	private View footView;
	private int pageNum = 1;
	private int pageSize = 10;
	private boolean loadingDataFlag = false;
	private String orderType = GlobalConstants.TYPE_ORDER_NEW;
	private boolean resetFlag = false;
	private int tid = -1;
	private HandlerThread ht;
	private Handler threadHandler;
	private boolean dataFinish=false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_avlist);
		initService();
		initHandler();
		initViews();
		refreshViews();
		initData();

	}

	private void refreshViews() {
		// TODO Auto-generated method stub
		if (progressDialog==null||!progressDialog.isShowing())
		{
			progressDialog = new ProgressDialog(AvListActivity.this);
			 progressDialog.setMessage("列表加载中");
			
			progressDialog.show();
		}
		footView.setVisibility(View.GONE);
		adapter = new AvListAdapter (AvListActivity.this);
		listView.setAdapter(adapter);

	}

	private void initViews() {
		
		listView = (ListView) findViewById(R.id.list_avlist);

		listView.setOnScrollListener(listScrollListener);
		footView = LayoutInflater.from(this).inflate(R.layout.list_foot, null);

		listView.addFooterView(footView, null, false);

		footView.setVisibility(View.GONE);

		listView.setOnItemClickListener(avListItemClickListener);
	}

	private void initService() {
		// TODO Auto-generated method stub
		avListService = new AvListService(AvListActivity.this);
	}

	private void initData() {
		// TODO Auto-generated method stub
		tid = getIntent().getIntExtra("tid", -1);

		threadHandler
				.sendEmptyMessage(GlobalConstants.MSG_AVCOMMENT_THREAD_INIT_DATA);

	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (progressDialog!=null)
		{
			progressDialog.dismiss();
		}
		super.onDestroy();
	}

	private void initHandler() {
		// TODO Auto-generated method stub
		ht = new HandlerThread("list_thread");
		ht.start();
		threadHandler = new Handler(ht.getLooper()) {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case GlobalConstants.MSG_AVLIST_THREAD_INIT_DATA:
					if (tid != -1) {
						List<AvInfo> list = avListService.getAvInfoList(tid,
								orderType, pageSize, pageNum);
						adapter.setList(list);

						mainHandler
								.sendEmptyMessage(GlobalConstants.MSG_AVLIST_MAIN_INIT_DATA);
						threadHandler
								.sendMessage(MessageHelper
										.obtainCustomMessage(
												GlobalConstants.MSG_AVLIST_THREAD_INIT_COVER,
												list));
					}
					break;
				case GlobalConstants.MSG_AVLIST_THREAD_LIST_UPDATE:
					List<AvInfo> list = avListService.getAvInfoList(tid,
							orderType, pageSize, pageNum);
					if (list==null||list.size()<=0)
						dataFinish=true;
					
					mainHandler
							.sendMessage(MessageHelper.obtainCustomMessage(GlobalConstants.MSG_AVLIST_MAIN_UPDATE_LIST,list));
					threadHandler
							.sendMessage(MessageHelper
									.obtainCustomMessage(
											GlobalConstants.MSG_AVLIST_THREAD_INIT_COVER,
											list));
					break;
				case GlobalConstants.MSG_AVLIST_THREAD_INIT_COVER:
					List<AvInfo> picList = (List<AvInfo>) msg.obj;
					for (int i = 0; i < picList.size(); i++) {
						final AvInfo avInfo = picList.get(i);

						AsynPicLoader loader = new AsynPicLoader(
								avInfo.getCover_pic_url(),
								new PicCompleteListener() {

									@Override
									public void onDrawLoadComplete(
											Drawable drawable) {
										// TODO Auto-generated method stub
//										System.out.println("avName"
//												+ avInfo.getTitle() + "avNo"
//												+ avInfo.getAid());
										DrawableCacheList.drawableCache.put(
												avInfo.getAid(), drawable);
										mainHandler
												.sendEmptyMessage(GlobalConstants.MSG_AVLIST_MAIN_INIT_COVER);
									}
								});
						loader.start();
					}

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
				switch (msg.what) {
				case GlobalConstants.MSG_AVLIST_MAIN_INIT_DATA:
					if (progressDialog.isShowing())
						progressDialog.dismiss();

					adapter.notifyDataSetChanged();
					loadingDataFlag=false;

					break;
				case GlobalConstants.MSG_AVLIST_MAIN_INIT_COVER:
					adapter.notifyDataSetChanged();
					break;
				case GlobalConstants.MSG_AVLIST_MAIN_UPDATE_LIST:
					List<AvInfo> tmpList=(List<AvInfo>)msg.obj;
					if (tmpList != null && tmpList.size() > 0) {
						for (int i = 0; i < tmpList.size(); i++)
							adapter.getList().add(tmpList.get(i));
					}
					footView.setVisibility(View.GONE);
					loadingDataFlag = false;
					adapter.notifyDataSetChanged();
					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}

		};
	}

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
					&& totalItemCount > 1 && !loadingDataFlag&&!dataFinish) {
				footView.setVisibility(View.VISIBLE);
				loadingDataFlag = true;

				pageNum = pageNum + 1;
				threadHandler
						.sendEmptyMessage(GlobalConstants.MSG_AVLIST_THREAD_LIST_UPDATE);

			}

		}
	};

	OnItemClickListener avListItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent(AvListActivity.this,
					AvInfoActivity.class);
			intent.putExtra(GlobalConstants.INTENT_EXTRA_NAME_AVINFO_AID,
					adapter.getList().get(arg2).getAid());
			startActivity(intent);
			// TODO Auto-generated method stub

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = new MenuInflater(getApplicationContext());
		inflater.inflate(R.menu.list_menu, menu);

		return true;
	}

	// Option �˵� ˢ��
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.order_type:
			DialogUtil.createListOrderMenu(AvListActivity.this,
					listOrderListener, GlobalConstants.LIST_ORDER_ITEMS).show();
			return true;

		}
		return false;
	}

	private android.content.DialogInterface.OnClickListener listOrderListener = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub

			switch (which) {
			case GlobalConstants.TYPE_ORDER_NEW_ITEM:
				orderType = GlobalConstants.TYPE_ORDER_NEW;
				break;
			case GlobalConstants.TYPE_ORDER_REVIEW_ITEM:
				orderType = GlobalConstants.TYPE_ORDER_REVIEW;
				break;
			case GlobalConstants.TYPE_ORDER_HOT_ITEM:
				orderType = GlobalConstants.TYPE_ORDER_HOT;
				break;
			case GlobalConstants.TYPE_ORDER_DAMKU_ITEM:
				orderType = GlobalConstants.TYPE_ORDER_DAMKU;
				break;
			case GlobalConstants.TYPE_ORDER_COMMENT_ITEM:
				orderType = GlobalConstants.TYPE_ORDER_COMMENT;
				break;
			case GlobalConstants.TYPE_ORDER_PROMOTE_ITEM:
				orderType = GlobalConstants.TYPE_ORDER_PROMOTE;
				break;

			default:
				break;
			}
			pageNum = 1;
			// resetFlag=true;
			refreshViews();
			threadHandler
					.sendEmptyMessage(GlobalConstants.MSG_AVLIST_THREAD_INIT_DATA);

		}
	};

}

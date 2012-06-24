package com.zccicy.activity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;

import com.zccicy.R;
 import com.zccicy.adapter.AvListAdapter;
import com.zccicy.common.util.CommonUtil;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.common.util.MessageHelper;
import com.zccicy.model.AvInfo;
import com.zccicy.model.AvList;
import com.zccicy.service.SearchService;
import com.zccicy.thread.AsynPicLoader;
import com.zccicy.thread.AsynPicLoader.PicCompleteListener;
import com.zccicy.tools.DrawableCacheList;

public class SearchActivity extends Activity {
	private ListView listView;
	private Spinner searchTypeSpinner;
	private Spinner searchOrderSpinner;
	private AutoCompleteTextView searchContent;
	private Button searchBtn;
	private AvListAdapter searchAdapter;
	private HandlerThread ht;
	private Handler threadHandler;
	private Handler mainHandler;
	private ProgressDialog progressDialog;
	private SearchService searchService;
	private String keyword = "";
	private String orderBy = "";
	private String type = "";
	private int pageNum = 1;
	private int pageSize = 10;
	private View footView;
	private boolean loadingDataFlag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_search);
		initViews();
		initSpinner();
		initService();
		initHandler();

		initData();
	}

	private void initService() {
		// TODO Auto-generated method stub
		searchService = new SearchService(getApplicationContext());
	}

	private void initHandler() {
		// TODO Auto-generated method stub
		ht = new HandlerThread("search_ht");
		ht.start();
		threadHandler = new Handler(ht.getLooper()) {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case GlobalConstants.MSG_SEARCH_THREAD_INIT_DATA:
					pageNum = 1;
					keyword = searchContent.getText().toString();
					orderBy = searchOrderSpinner.getSelectedItem().toString();
					type = searchTypeSpinner.getSelectedItem().toString();
					searchAdapter = new AvListAdapter(getApplicationContext());
					List<AvInfo> list = searchService.searchContent(keyword,
							orderBy, type, pageNum, pageSize);
					for (int i = 0; i < list.size(); i++) {
						searchAdapter.getList().add(list.get(i));
					}
					threadHandler.sendMessage(MessageHelper
							.obtainCustomMessage(
									GlobalConstants.MSG_SEARCH_THREAD_LOAD_PIC,
									list));
					mainHandler
							.sendEmptyMessage(GlobalConstants.MSG_SEARCH_MAIN_INIT_DATA);
					break;
				case GlobalConstants.MSG_SEARCH_THREAD_UPDATE_LIST:
					pageNum = pageNum + 1;
					List<AvInfo> tmpList = searchService.searchContent(keyword,
							orderBy, type, pageNum, pageSize);
					for (int i = 0; i < tmpList.size(); i++) {
						searchAdapter.getList().add(tmpList.get(i));
					}
					threadHandler.sendMessage(MessageHelper
							.obtainCustomMessage(
									GlobalConstants.MSG_SEARCH_THREAD_LOAD_PIC,
									tmpList));
					mainHandler
							.sendEmptyMessage(GlobalConstants.MSG_SEARCH_MAIN_UPDATE_LIST);
					break;
				case GlobalConstants.MSG_SEARCH_THREAD_LOAD_PIC:
					List<AvInfo> iconList=(List<AvInfo>)msg.obj;
					for (int i = 0; i < iconList.size(); i++) {
						final AvInfo avInfo = iconList.get(i);
						System.out.println("intoloop" + avInfo.getTitle());
						AsynPicLoader loader = new AsynPicLoader(
								avInfo.getCover_pic_url(),
								new PicCompleteListener() {

									@Override
									public void onDrawLoadComplete(Drawable drawable) {
										// TODO Auto-generated method stub
										System.out.println("avName"
												+ avInfo.getTitle() + "avNo"
												+ avInfo.getAid());
										DrawableCacheList.drawableCache.put(
												avInfo.getAid(), drawable);
										mainHandler
												.sendEmptyMessage(GlobalConstants.MSG_SEARCH_MAIN_REFRESH_PIC);
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
				super.handleMessage(msg);
				switch (msg.what) {
				case GlobalConstants.MSG_SEARCH_MAIN_INIT_DATA:
					footView.setVisibility(View.GONE);
					listView.setAdapter(searchAdapter);
					searchAdapter.notifyDataSetChanged();
					if (progressDialog.isShowing())
						progressDialog.dismiss();
					break;
				case GlobalConstants.MSG_SEARCH_MAIN_UPDATE_LIST:
					footView.setVisibility(View.GONE);
					searchAdapter.notifyDataSetChanged();
					loadingDataFlag = false;
					break;
				case GlobalConstants.MSG_SEARCH_MAIN_REFRESH_PIC:
					searchAdapter.notifyDataSetChanged();
					break;

				default:
					break;
				}
			}

		};
	}

	private void initData() {
		// TODO Auto-generated method stub
		searchBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!progressDialog.isShowing())
					progressDialog.show();

				threadHandler
						.sendEmptyMessage(GlobalConstants.MSG_SEARCH_THREAD_INIT_DATA);
			}
		});
	}

	private void initViews() {
		// TODO Auto-generated method stub
		listView = (ListView) findViewById(R.id.list_search);
		searchTypeSpinner = (Spinner) findViewById(R.id.search_type);
		searchContent = (AutoCompleteTextView) findViewById(R.id.search_content_atv);
		searchBtn = (Button) findViewById(R.id.search_btn);
		searchOrderSpinner = (Spinner) findViewById(R.id.search_order);
		searchAdapter = new AvListAdapter(getApplicationContext());
		listView.setOnScrollListener(listScrollListener);
		footView = LayoutInflater.from(this).inflate(R.layout.list_foot, null);

		listView.addFooterView(footView, null, false);

		footView.setVisibility(View.GONE);

		listView.setOnItemClickListener(avListItemClickListener);
		listView.setAdapter(searchAdapter);

		progressDialog = new ProgressDialog(SearchActivity.this);
		progressDialog.setMessage("搜索中" );

	}

	private void initSpinner() {
		// TODO Auto-generated method stub
		ArrayAdapter<String> searchOrderAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item);
		searchOrderAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		HashMap<String, String> searchOrder = CommonUtil.getSearchOrderType();
		Iterator iter = null;
		iter = searchOrder.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			searchOrderAdapter.add((String) entry.getKey());
		}
		searchOrderSpinner.setAdapter(searchOrderAdapter);

		ArrayAdapter<String> searchTypeAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		searchTypeAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		List<AvList> list = CommonUtil.getChildCategory(
				getApplicationContext(), 0);
		for (int i = 0; i < list.size(); i++) {
			searchTypeAdapter.add(list.get(i).getType_name());
		}
		searchTypeSpinner.setAdapter(searchTypeAdapter);

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
					&& totalItemCount > 1 && !loadingDataFlag) {
				loadingDataFlag = true;
				footView.setVisibility(View.VISIBLE);
				threadHandler
						.sendEmptyMessage(GlobalConstants.MSG_SEARCH_THREAD_UPDATE_LIST);
			}

		}
	};

	OnItemClickListener avListItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Intent intent = new Intent(SearchActivity.this,
					AvInfoActivity.class);
			if (!searchAdapter.getList().get(position).getAid().equals(0)) {
				intent.putExtra(GlobalConstants.INTENT_EXTRA_NAME_AVINFO_AID,
						searchAdapter.getList().get(position).getAid());
				startActivity(intent);
				// TODO Auto-generated method stub
			}
		}
	};
}

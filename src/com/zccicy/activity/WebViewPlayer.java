package com.zccicy.activity;

 
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;

import com.zccicy.R;

public class WebViewPlayer extends Activity{
	private String url="";
	private WebView playerContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_webview_player);
		playerContainer=(WebView)findViewById(R.id.wb_player);
		
		url=getIntent().getStringExtra("flashurl");
		System.out.println("offset-----"+url);
		playerContainer.getSettings().setJavaScriptEnabled(true);  
		//mWebView.getSettings().setPluginsEnabled(true);
		playerContainer.getSettings().setPluginState(PluginState.ON);
//		playerContainer.getSettings().
//		play
		playerContainer.setWebChromeClient(new WebChromeClient()); 
		playerContainer.loadUrl(url);
	}
	

}

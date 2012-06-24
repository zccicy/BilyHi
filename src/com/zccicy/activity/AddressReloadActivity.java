package com.zccicy.activity;

import android.app.Activity;
import android.os.Bundle;

import com.zccicy.R;

public class AddressReloadActivity extends Activity {

	private String vid = "55963472";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_test);
//		 
		
//		String url = VideoRealAddressConvertionUtil.getVideoRealAddressByVid(
//				"sina", vid);
//		Intent intent=new Intent(Intent.ACTION_VIEW);
//		intent.setDataAndType(Uri.parse(url), "video/hlv");
//		startActivity(intent);
		
		
		// String address=GlobalConstants.PATH_API_YOUKU+vid;
		// List<String> nameList=new ArrayList<String>();
		// nameList.add("controller");
		//
		// HttpGet httpGet = HttpUtil.getHttpGet(address);
		// String abc="";
		// List<JSONObject> list = new ArrayList<JSONObject>();
		// try {
		// HttpResponse httpResponse = HttpUtil.getHttpResponse(httpGet);
		// abc=EntityUtils.toString(httpResponse.getEntity());
		// System.out.println(abc);
		// int seed=0;
		// String key1="";
		// String key2="";
		// JSONObject jsonObject = new JSONObject(abc);
		//
		// JSONArray jsonArray = jsonObject.getJSONArray("data");
		//
		// for(int i=0;i<jsonArray.length();i++){
		// JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
		// seed=jsonObject2.getInt("seed");
		// key1=jsonObject2.getString("key1");
		// key2=jsonObject2.getString("key2");
		//
		// }
		// System.out.println("seed"+seed+"---"+"key1"+key1+"------key2"+key2);
		//
		//
		//
		// // System.out.println("tag"+jsonObject2.getString("tags"));
		//
		//
		// } catch (Exception e) {
		// // TODO: handle exception
		// e.printStackTrace();
		// }

	}
}

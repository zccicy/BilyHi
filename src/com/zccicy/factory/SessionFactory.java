package com.zccicy.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zccicy.common.util.CommonUtil;
import com.zccicy.model.AvList;

import android.app.Activity;
import android.content.Context;

public class SessionFactory {
	// 类型信息

	public static HashMap<Integer, AvList> categoryMap= new HashMap<Integer, AvList>();
	public static List<Activity> appActivityList;
	 
	public static void initFactory(Context context) {
		getChannelInfo(context);
		getAppActivityList();
 
	}

 

 

	public  static List<Activity> getAppActivityList() {
		// TODO Auto-generated method stub
		if (appActivityList==null||appActivityList.size()<=0)
		{
			appActivityList=new ArrayList<Activity>();
		}
		return appActivityList;
	}





	public static HashMap<Integer, AvList> getChannelInfo(Context context) {
		if (categoryMap==null||categoryMap.size()<=0)
		categoryMap=CommonUtil.initCategory(context);
		return categoryMap;
	}

	 

}

package com.zccicy.common.util;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.util.DisplayMetrics;

import com.zccicy.factory.SessionFactory;
import com.zccicy.tools.DrawableCacheList;

public class PackageUtil {
	private static PackageManager pm;

	public static PackageManager getPm(Context context) {
		if (pm != null)
			return pm;

		pm = context.getPackageManager();
		return pm;
	}
  

	public static void removePackages(Context context, String packageName) {
		Intent intent = new Intent(Intent.ACTION_DELETE);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Uri data = Uri.fromParts("package", packageName, null);
		intent.setData(data);
		context.startActivity(intent);
	}

	 

	public static PackageInfo getPackageInfoByName(Context context,
			String packageName) {
		if (pm == null)
			getPm(context);

		try {
			return pm.getPackageInfo(packageName, 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

 
 
	/**
	 * 获得可以启动的全部应用程序
	 * */
	public static List<ResolveInfo> getAllApps(PackageManager pm) {
		List<ResolveInfo> mApps;
		Intent localIntent = new Intent("android.intent.action.MAIN", null);
		localIntent.addCategory("android.intent.category.LAUNCHER");
		mApps = pm.queryIntentActivities(localIntent, 0);
		return mApps;
	}

	public static void closeApp(Context context) {

		 
		 

		List<Activity> list = SessionFactory.appActivityList;
		for (int i = 0; i < list.size(); i++) {
			if (null != list.get(i)) {
				list.get(i).finish();
			}
		}
		((NotificationManager) context.getSystemService("notification"))
				.cancelAll();
		// System.out.println("app quit");
 
 
		((NotificationManager) context.getSystemService("notification"))
				.cancelAll();
		System.exit(0);

	}

	private static List<String> allApkList;

	 

	public static void SearchAllApkInFolder(File file) {
		File[] files = file.listFiles();
		if (files == null || files.length <= 0)
			return;
		for (int i = 0; i < files.length; i++) {
			if (!files[i].exists())
				continue;
			if (files[i].isDirectory()) {
				SearchAllApkInFolder(files[i]);
			} else {
				String filePath = files[i].getAbsolutePath();
				if (filePath.lastIndexOf(".") < 0)
					continue;
				if (filePath.substring(filePath.lastIndexOf("."))
						.equals(".apk"))
					allApkList.add(filePath);
			}

		}

	}

	 

}

package com.zccicy.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.res.XmlResourceParser;

import com.zccicy.R;
import com.zccicy.factory.SessionFactory;
import com.zccicy.model.AvList;

public class CommonUtil {

	/**
	 * @return
	 */

	private static HashMap<String, String> searchOrderType;

	public static HashMap<String, String> getSearchOrderType() {
		if (searchOrderType == null) {
			searchOrderType = new HashMap<String, String>();
			searchOrderType.put("综合", "all");
			searchOrderType.put("排名", "ranklevel");
			searchOrderType.put("发布日期", "senddate");
			searchOrderType.put("通过日期", "pubdate");
			searchOrderType.put("点击率", "click");
			searchOrderType.put("得分", "score");
			searchOrderType.put("弹幕数", "dm");
			searchOrderType.put("？？？", "stow");

		}
		return searchOrderType;
	}

	public static void initDataPath()
	{
		File file=new File(GlobalConstants.PATH_DM_XML);
		
		if (!file.exists())
		{
			file.mkdirs();
		}
	}
	public static HashMap<Integer, AvList> initCategory(Context context) {
		XmlResourceParser parser = context.getResources().getXml(
				R.xml.av_category_data);
		HashMap<Integer, AvList> categoryHashMap = new HashMap<Integer, AvList>();
		AvList avList = null;

		try {
			while (parser.getEventType() != XmlResourceParser.END_DOCUMENT) {
				if (parser.getEventType() == XmlResourceParser.START_TAG) {

					if (parser.getName().equals("category")) {
						avList = new AvList();
						avList.setTid(Integer.parseInt(parser
								.getAttributeValue(0)));
						avList.setParent_id(Integer.parseInt(parser
								.getAttributeValue(1)));
						avList.setType_name(parser.getAttributeValue(2));
//						System.out.println(avList.getTid() + "-"
//								+ avList.getParent_id() + "-"
//								+ avList.getType_name());
						categoryHashMap.put(avList.getTid(), avList);
					}
				}
				parser.next();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return categoryHashMap;

		 

	}

	public static List<AvList> getChildCategory(Context context, int parentId) {
		HashMap<Integer, AvList> hashMap = SessionFactory
				.getChannelInfo(context);
		List<AvList> list = new ArrayList<AvList>();
		Iterator iter = null;
		iter = hashMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			if (((AvList) entry.getValue()).getParent_id().equals(parentId))
				list.add((AvList) entry.getValue());

		}

		return list;

	}
}

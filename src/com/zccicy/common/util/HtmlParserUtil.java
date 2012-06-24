package com.zccicy.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import android.content.Context;

import com.zccicy.model.AvInfo;
import com.zccicy.service.AvInfoService;

public class HtmlParserUtil {
	public static List<AvInfo> biliSearchParser(Context context, String url) {
		AvInfoService avInfoService = new AvInfoService(context);
		List<AvInfo> list = new ArrayList<AvInfo>();
		try {
			// HttpGet httpGet=HttpUtil.getHttpGet(url);
			// httpGet.set
			String result = HttpUtil.getHtml(url, "UTF-8");
			;
			System.out.println(result);
			Pattern pattern = Pattern.compile("<h3><a href=\"(.*?)\"");
			Pattern patternForList = Pattern
					.compile("http://www.bilibili.tv/mylist(.*?)");
			Pattern patternForItem = Pattern
					.compile("http://www.bilibili.tv/video/av(.*?)/");
			Matcher matcher = pattern.matcher(result);
			while (matcher.find()) {
				String itemUrl = matcher.group(1).trim();
				Matcher matcherForList = patternForList.matcher(itemUrl);
				Matcher matcherForItem = patternForItem.matcher(itemUrl);
				if (matcherForList.matches()) {
					// matcherForList.find();
					AvInfo avInfo = new AvInfo();
					avInfo.setTitle("合集" + matcherForList.group(1).trim()
							+ "---现版本不支持解析");
					// list.add(avInfo);
				} else if (matcherForItem.matches()) {
					// matcherForList.find();
					Integer aid = Integer.parseInt(matcherForItem.group(1)
							.trim());
					AvInfo avInfo = avInfoService.getAvInfo(aid);
					if (avInfo == null) {
						avInfo = new AvInfo();
						avInfo.setTitle("未知视频");
					}
					list.add(avInfo);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

}

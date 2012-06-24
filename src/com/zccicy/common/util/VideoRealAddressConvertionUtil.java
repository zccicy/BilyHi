package com.zccicy.common.util;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.zccicy.model.SinaData;
import com.zccicy.model.SinaUrlData;
import com.zccicy.model.TudouData;
import com.zccicy.xml.parser.TudouDataParser;

public class VideoRealAddressConvertionUtil {
	public static List<String> getVideoRealAddressByVid(String videoSrcType,
			String vid) {
		String url = "";
		if (videoSrcType.equals("youku")) {
			url = "http://v.youku.com/v_show/id_" + vid + ".html";
		} else if (videoSrcType.equalsIgnoreCase("tudou")) {
			// url = "http://www.tudou.com/programs/view/" + vid + "/";
			return getTudouAddress(vid);
		} else if (videoSrcType.equalsIgnoreCase("sina")) {
			return getSinaAddress(vid);
		} else if (videoSrcType.equalsIgnoreCase("qq")) {
			url = "http://boke.qq.com/play.html?v=" + vid;
		}
		return getVideoFromFLVCD(url);
	}

	private static List<String> getVideoFromFLVCD(String url) {
		// TODO Auto-generated method stub
		List<String> urlList = new ArrayList<String>();

		// try {
		// url=URLEncoder.encode(url, "UTF-8");
		// } catch (UnsupportedEncodingException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		url = "http://www.flvcd.com/parse.php?flag=&format=&kw=" + url;

		System.out.println("addressRequestUrl"+url);

		HttpGet httpGet = HttpUtil.getHttpGet(url);
		try {
			HttpResponse httpResponse = HttpUtil.getHttpResponse(httpGet);
			String result = EntityUtils.toString(httpResponse.getEntity());

			String reg = "<U>(.*?)\n";
			Pattern pattern = Pattern.compile(reg);

			Matcher matcher = pattern.matcher(result);

			while (matcher.find()) {
				String realUrl = matcher.group(1).trim();

				realUrl = realUrl.replace("&amp;", "&");
				System.out.println("url+++" + realUrl + "");
				urlList.add(realUrl);
			}
			;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return urlList;
	}

	private static List<String> getSinaAddress(String vid) {
		// TODO Auto-generated method stub
		List<SinaData> list = SinaUtil.getDataByVid(vid);
		List<String> stringList = new ArrayList<String>();
		try {
			List<SinaUrlData> urlList = list.get(0).getUrlData();
			for (int i = 0; i < urlList.size(); i++) {
				stringList.add(urlList.get(i).getUrl());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return stringList;
	}

	private static List<String> getTudouAddress(String vid) {
		// TODO Auto-generated method stub
		List<String> resultList = new ArrayList<String>();

		String iid = TudouUtil.getIidFromVid(vid);
		String url = GlobalConstants.PATH_API_TUDOU + iid;
		TudouDataParser parser = new TudouDataParser(url, iid);
		List<TudouData> list = parser.parse();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String address = parser.parse().get(i).getAddress();
				try {
					URLConnection urlConnection = new URL(address)
							.openConnection();
					urlConnection.getContent();
					
					resultList.add(urlConnection.getURL().toString());
					System.out.println(urlConnection.getURL().toString());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return resultList;
	}

	public static String getYoukuAddress(String vid, String type) {
		// TODO Auto-generated method stub
		String url = GlobalConstants.PATH_API_YOUKU + vid;
		String result = "";

		try {
			result = EntityUtils.toString(HttpUtil.getHttpResponse(
					HttpUtil.getHttpGet(url)).getEntity());
			JSONArray jsonArray = new JSONObject(result).getJSONArray("data");
			JSONObject jsonObject = (JSONObject) jsonArray.opt(0);
			int seed = jsonObject.getInt("seed");
			String key1 = jsonObject.getString("key1");
			String key2 = jsonObject.getString("key2");
			String fileIds = jsonObject.getJSONObject("streamfileids")
					.getString(type);
			String sid = YoukuUtil.genSid();
			String fileId = YoukuUtil.getFileID(fileIds, seed);
			String key = YoukuUtil.genKey(key1, key2);
			String realAddress = GlobalConstants.PATH_TEMPLETE_YOUKU;
			realAddress = realAddress.replace("_{sid_param}_", sid);
			realAddress = realAddress.replace("_{type_param}_", type);
			realAddress = realAddress.replace("_{fileid_param}_", fileId);
			realAddress = realAddress.replace("_{key_param}_", key);
			return realAddress;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}

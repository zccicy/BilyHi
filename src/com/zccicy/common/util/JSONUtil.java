package com.zccicy.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

public class JSONUtil {

	public static List<JSONObject> getJSONObjectByName(List<String> name,
			String url) {
		HttpGet httpGet = HttpUtil.getHttpGet(url);
		StringBuilder builder=new StringBuilder();
		builder.setLength(0);
		List<JSONObject> list = new ArrayList<JSONObject>();
		try {
			HttpResponse httpResponse = HttpUtil.getHttpResponse(httpGet);
			InputStream inputStream = httpResponse.getEntity().getContent();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(inputStream));
			for (String s = bufferedReader.readLine(); s != null; s = bufferedReader
					.readLine()) {
				builder.append(s);
			}

			JSONObject jsonObject = new JSONObject(builder.toString());
			for (int i = 0; i < name.size(); i++) {

				String object = jsonObject.getString(name.get(i));
				JSONObject jsonObject2=new JSONObject(object);
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

}

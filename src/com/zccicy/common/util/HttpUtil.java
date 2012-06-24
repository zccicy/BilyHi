package com.zccicy.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

//�趨Ϊ����ģʽ
public class HttpUtil {
	public static HttpGet getHttpGet(String url) {
		HttpGet request = new HttpGet(url);
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params,
				GlobalConstants.CONNECTION_TIME_OUT_VALUE);
		// params.setParameter(
		// CoreProtocolPNames.USER_AGENT,
		// "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; Media Center PC 4.0; SLCC1; .NET CLR 3.0.04320)");
		// request.setParams(params);
		System.out.println("ua"+request.getParams().getParameter(
				CoreProtocolPNames.USER_AGENT));
		return request;

	}

	public static HttpPost getHttpPost(String url) {
		HttpPost request = new HttpPost(url);
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params,
				GlobalConstants.CONNECTION_TIME_OUT_VALUE);
		request.setParams(params);
		return request;

	}

	public static HttpResponse getHttpResponse(HttpGet request)
			throws ClientProtocolException, IOException {
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;

	}

	public static HttpResponse getHttpResponse(HttpPost request)
			throws ClientProtocolException, IOException {
		HttpResponse response = new DefaultHttpClient().execute(request);
		return response;
	}

	public static String getHttpTextContentFromResponse(HttpResponse response)
			throws Exception {

		if (response.getStatusLine().getStatusCode() == 200) {
			String result = null;
			try {
				result = EntityUtils.toString(response.getEntity());

			} catch (Exception e) {
				e.printStackTrace();
			}

			return result;

		}

		else {
			return null;
		}

	}

	public static boolean getFileFromUrl(String localFile, String remoteUrl) {
		// StringBuffer sb = new StringBuffer();
		try {
			File file = new File(localFile);
			if (!file.exists())
			{
				file.createNewFile();
			}
			URL url = new URL(remoteUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStream is = conn.getInputStream();

			FileOutputStream fos = new FileOutputStream(file);
			byte[] buf = new byte[65535];
			if (conn.getResponseCode() >= 400) {
				return false;
			} else {
				while (1 == 1) {
					if (is != null) {
						int numRead = is.read(buf);
						if (numRead <= 0) {
							break;
						} else {

							fos.write(buf, 0, numRead);

						}

					} else {
						break;
					}

				}
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}

	private static final int BUFFER_IO_SIZE = 8000;

	public static Drawable getDrawableFromWeb(final String url) {
		try {
			// Addresses bug in SDK :
			// http://groups.google.com/group/android-developers/browse_thread/thread/4ed17d7e48899b26/
			BufferedInputStream bis = new BufferedInputStream(
					new URL(url).openStream(), BUFFER_IO_SIZE);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(baos,
					BUFFER_IO_SIZE);
			copy(bis, bos);
			bos.flush();
			Bitmap bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(),
					0, baos.size());
			return new BitmapDrawable(bitmap);
		} catch (IOException e) {
			// handle it properly

			return null;
		}
	}

	private static void copy(final InputStream bis, final OutputStream baos)
			throws IOException {
		byte[] buf = new byte[256];
		int l;
		while ((l = bis.read(buf)) >= 0)
			baos.write(buf, 0, l);
	}

	public static Drawable loadImageFromUrl(String url) {
		URL m;
		InputStream is = null;
		try {
			m = new URL(url);
			is = (InputStream) m.getContent();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Drawable d = Drawable.createFromStream(is, "src");
		return d;
	}

	public static int getFileSize(String urlPath) {
		URL url;
		int fileSize = 0;
		try {
			url = new URL(urlPath);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(GlobalConstants.CONNECTION_TIME_OUT_VALUE);
			fileSize = conn.getContentLength();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return fileSize;

	}

	public static String getResultFromUrlByPost(String url,
			List<NameValuePair> params) {
		try {
			HttpPost request = HttpUtil.getHttpPost(url);
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse hr;
			hr = HttpUtil.getHttpResponse(request);
			String result = EntityUtils.toString(hr.getEntity());
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			return null;
		}

	}

	public static String getResultFromUrlByGet(String url, String charset)

	{
		try {
			HttpGet request = getHttpGet(url);
			HttpResponse response = getHttpResponse(request);
			String result = EntityUtils.toString(response.getEntity(), charset);
			return result;
		} catch (Exception e) {
			// TODO: handle exception

			return null;
		}
	}

	/** * ��ȡ��� * ������ * * */
	public static byte[] readStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outstream.write(buffer, 0, len);
		}
		outstream.close();
		inStream.close();
		return outstream.toByteArray();
	}

	public static String getHtml(String path, String encoding) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(6 * 1000);
		// 网页回应码
		System.out.println("responsecode"+conn.getResponseCode()+"encoding"+encoding);

		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			byte[] data = readStream(inputStream);
			return new String(data, encoding);
		}
		return null;
	}

}

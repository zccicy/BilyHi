package com.zccicy.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.HttpPost;

public class TudouUtil {
	public static String getIidFromVid(String vid)
	{
		String str2 = ""; 
		try
		{
		String url=GlobalConstants.PATH_SITE_TUDOU+vid+"/";
		
//		URLConnection conn=new URL(url).openConnection();
		 
//		String result=HttpUtil.getResultFromUrlByGet(url,"ISO-8859-1");
//		System.out.println("result"+result);
//		HttpPost httpPost=HttpUtil.getHttpPost(url);
//		 BufferedReader bufferdReader = new BufferedReader( 
//                 new InputStreamReader(HttpUtil.getHttpResponse(httpPost).getEntity().getContent(),"ISO-8859-1")); 
         String result=HttpUtil.getHtml(url, "GBK");
         Pattern pattern=Pattern.compile(",iid = (.*?)\n");
         Matcher matcher=pattern.matcher(result);
         while (matcher.find()) {
        	 str2 = matcher.group(1).trim();
         }
//         System.out.println(result);
//         for (String s = bufferdReader.readLine(); s != null; s = bufferdReader 
//                 .readLine()) { 
//        	 s=new String(s.getBytes("ISO-8859-1"),"UTF-8");
//    
//             if (s.contains(",iid = "))
//             {
//            	 str2=s.substring(s.indexOf(",iid = ")+6).trim();
//            	 break;
//             }
//         } 
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return str2;

	}

}

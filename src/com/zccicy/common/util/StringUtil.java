package com.zccicy.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	
	public static String getWhereFromPk(String[] pkName,String[] pkValue)
	{
		String whereParam = "";
		for (int i=0;i<pkValue.length;i++)
		{
		whereParam = "and " + pkName[i] + " = '" + pkValue[i] + "'   ";
		}
		if (whereParam.trim() != "") {
			whereParam = whereParam.trim().substring(3);
		}
		return whereParam;
	}

	public static String WebFlowStringFilter(String source) {
		source = source.trim();
		source = source.replaceAll("&nbsp;", "  ");
		return source;
	}

	// Convert Unix timestamp to normal date style
	public static String TimeStamp2Date(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new java.text.SimpleDateFormat("yyyy-MM-dd")
				.format(new java.util.Date(timestamp));
		return date;
	}

	public static String Md5(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (plainText==null)
				plainText="unknown device";
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}

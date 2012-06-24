package com.zccicy.common.util;

import java.util.List;

import com.zccicy.model.SinaData;
import com.zccicy.xml.parser.SinaDataParser;

public class SinaUtil {
	public static List<SinaData> getDataByVid(String vid)
	{
		String url=GlobalConstants.PATH_API_SINA+vid;
		SinaDataParser parser=new SinaDataParser(url, vid);
		return parser.parse();
	}

}

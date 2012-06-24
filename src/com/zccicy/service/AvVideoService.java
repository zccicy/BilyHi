package com.zccicy.service;

import java.util.List;

import android.content.Context;

import com.zccicy.common.service.DefaultService;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.model.AvVideoInfo;
import com.zccicy.xml.parser.AvListParser;
import com.zccicy.xml.parser.AvVideoInfoParser;

public class AvVideoService extends DefaultService {

	public AvVideoService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public AvVideoInfo getAvVideoInfo(int aid,int pageNum)
	{
		String url = GlobalConstants.PATH_API_ROOT
		+ GlobalConstants.PATH_FOR_DETAIL
		+ GlobalConstants.PARAM_FOR_VIEW_AID + aid + "&"
		+ GlobalConstants.PARAM_FOR_XML+"&"+GlobalConstants.PARAM_FOR_PAGENUM+pageNum;
		AvVideoInfoParser parser=new AvVideoInfoParser(url,aid,pageNum,context);
		List<AvVideoInfo> list=parser.parse();
		if (list==null||list.size()<=0)
		{
			return null;
		}
		else
		{
		return parser.parse().get(0);
		}
	}
}

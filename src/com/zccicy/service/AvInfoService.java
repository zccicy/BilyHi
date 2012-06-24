package com.zccicy.service;

import android.content.Context;

import com.zccicy.common.service.DefaultService;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.model.AvInfo;
import com.zccicy.xml.parser.AvInfoParser;
import com.zccicy.xml.parser.AvListParser;

public class AvInfoService extends DefaultService {

	public AvInfoService(Context context) {
		super(context);

		// TODO Auto-generated constructor stub
	}

	public AvInfo getAvInfo(int aid) {
		String url = GlobalConstants.PATH_API_ROOT
				+ GlobalConstants.PATH_FOR_DETAIL
				+ GlobalConstants.PARAM_FOR_VIEW_AID + aid + "&"
				+ GlobalConstants.PARAM_FOR_XML;
		AvInfoParser parser = new AvInfoParser(url,aid,context);
		try {
			return parser.parse().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}

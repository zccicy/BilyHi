package com.zccicy.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.zccicy.common.service.DefaultService;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.model.AvInfo;
import com.zccicy.xml.parser.AvListParser;

public class AvListService extends DefaultService {

	public AvListService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public List<AvInfo> getAvInfoList(int tid, String order, int pageSize,
			int pageNum) {
		String url = GlobalConstants.PATH_API_ROOT
				+ GlobalConstants.PATH_FOR_LIST + GlobalConstants.PARAM_FOR_TID
				+ tid + "&" + GlobalConstants.PARAM_FOR_PAGESIZE + pageSize
				+ "&" + GlobalConstants.PARAM_FOR_ORDER + order + "&"
				+ GlobalConstants.PARAM_FOR_XML + "&"
				+ GlobalConstants.PARAM_FOR_PAGENUM + pageNum;
		AvListParser parser = new AvListParser(context, url, pageNum, pageSize);
		try {
			parser.parse();
			return parser.getAvInfoList();
		} catch (Exception e) {
			return new ArrayList<AvInfo>();
			// TODO: handle exception
		}
	}

}

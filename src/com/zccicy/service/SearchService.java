package com.zccicy.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.zccicy.common.service.DefaultService;
import com.zccicy.common.util.CommonUtil;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.common.util.HtmlParserUtil;
import com.zccicy.model.AvInfo;

public class SearchService extends DefaultService {

	public SearchService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public List<AvInfo> searchContent(String keyword, String order,
			String type, int pageNum, int pageSize) {
		List<AvInfo> list = new ArrayList<AvInfo>();

		
		try {
			String kw=keyword.trim()
			+ "@" + type ;
 			kw = URLEncoder.encode(kw, "UTF-8");
			String url = GlobalConstants.PARAM_FOR_SEARCH_KEYWORD +kw + "&" + GlobalConstants.PARAM_FOR_SEARCH_ORDERBY
			+ CommonUtil.getSearchOrderType().get(order) + "&"
			+ GlobalConstants.PARAM_FOR_SEARCH_PAGENUM + pageNum + "&"
			+ GlobalConstants.PARAM_FOR_PAGESIZE + pageSize;
			
			url = GlobalConstants.PATH_FOR_SEARCH + url;
			System.out.println("searchurl"+url);
			list = HtmlParserUtil.biliSearchParser(context,url);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return list;
	}

}

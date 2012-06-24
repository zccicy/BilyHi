package com.zccicy.service;

import java.util.List;

import android.content.Context;

import com.zccicy.common.service.DefaultService;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.model.AvComment;
import com.zccicy.xml.parser.AvCommentParser;

public class AvCommentService extends DefaultService {

	public AvCommentService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public List<AvComment> getCommnetList(int aid, int pageSize, int pageNum) {
		String url = GlobalConstants.PATH_API_ROOT
				+ GlobalConstants.PATH_FOR_COMMENT
				+ GlobalConstants.PARAM_FOR_COMMENT_AID + aid + "&"
				+ GlobalConstants.PARAM_FOR_PAGENUM + pageNum + "&"
				+ GlobalConstants.PARAM_FOR_PAGESIZE + pageSize + "&"
				+ GlobalConstants.PARAM_FOR_XML;
		AvCommentParser parser = new AvCommentParser(url);
		return parser.parse();
	}

}

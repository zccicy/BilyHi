package com.zccicy.dao;

import android.content.Context;

import com.zccicy.common.dao.DefaultDao;
import com.zccicy.model.AvVideoInfo;

public class AvVideoInfoDao extends DefaultDao<AvVideoInfo> {

	public AvVideoInfoDao(Context context) {
		super(AvVideoInfo.getTableName(), AvVideoInfo.class, context,
				AvVideoInfo.getPrimaryKey());
		// TODO Auto-generated constructor stub
	}

	public boolean update(AvVideoInfo avVideoInfo) {
		return update(avVideoInfo, new String[] { avVideoInfo.getAid() + "",
				avVideoInfo.getPage() + "" }, "aid='" + avVideoInfo.getAid()
				+ "' and page='" + avVideoInfo.getPage() + "'", null);
	}
}

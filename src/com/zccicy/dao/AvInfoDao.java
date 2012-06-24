package com.zccicy.dao;

import android.content.Context;

import com.zccicy.common.dao.DefaultDao;
import com.zccicy.model.AvInfo;
import com.zccicy.model.AvList;

public class AvInfoDao extends DefaultDao<AvInfo> {

	public AvInfoDao(Context context) {
		super(AvInfo.getTableName(), AvInfo.class, context, AvInfo
				.getPrimaryKey());
		// TODO Auto-generated constructor stub
	}

	public boolean update(AvInfo avInfo) {
		return update(avInfo, new String[] { avInfo.getAid() + "" }, "aid='"
				+ avInfo.getAid() + "'", null);
	}
}

package com.zccicy.dao;

import android.content.Context;

import com.zccicy.common.dao.DefaultDao;
import com.zccicy.model.AvList;

public class AvListDao extends DefaultDao<AvList> {

	public AvListDao(Context context) {
		super(AvList.getTableName(), AvList.class, context, AvList
				.getPrimaryKey());
		// TODO Auto-generated constructor stub
	}

	public boolean update(AvList avList) {
		return update(avList,
				new String[] { avList.getTid() + "", avList.getType() + "" },
				"tid='" + avList.getTid() + "' and type='" + avList.getType()
						+ "'", null);
	}
}

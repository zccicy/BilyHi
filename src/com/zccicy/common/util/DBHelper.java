package com.zccicy.common.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zccicy.model.AvInfo;
import com.zccicy.model.AvList;
import com.zccicy.model.AvVideoInfo;

public class DBHelper extends SQLiteOpenHelper {

	private Context context;

	public DBHelper(Context context) {
		super(context, GlobalConstants.DB_NAME, null,
				GlobalConstants.MARKET_VERSION_CODE);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	 
		String createAvInfoTbQuery = "Create table " + AvInfo.getTableName()
				+ " (aid integer primary key," + " play_count integer ,"
				+ " author text," + " av_desc text," + " review_count integer,"
				+ " video_review_count integer," + " favorites integer,"
				+ " title text," + " tag text," + " cover_pic_url text,"
				+ " page_count integer," + " post_time text,"
				+ " comment_count integer," + " last_update_time integer);";
		db.execSQL(createAvInfoTbQuery);
 
		String createAvListTableQuery = "Create table " + AvList.getTableName()
				+ "	(tid integer ," + "type integer," + " 	parent_id integer,"
				+ "	type_name text," + " aid_list text,"
				+ " primary key (tid,type));";

		db.execSQL(createAvListTableQuery);
 
		String createAvVideoTableQuery = "Create table "
				+ AvVideoInfo.getTableName() + "(aid integer ,"
				+ " page integer," + " video_from_src text," + " vid text,"
				+ " offsite text," + " primary key (aid,page));";

		db.execSQL(createAvVideoTableQuery);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		db.execSQL("DROP TABLE IF EXISTS " + AvInfo.getTableName());
		db.execSQL("DROP TABLE IF EXISTS " + AvList.getTableName());
		db.execSQL("DROP TABLE IF EXISTS " + AvVideoInfo.getTableName());

		onCreate(db);

	}

}

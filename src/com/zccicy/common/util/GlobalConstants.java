package com.zccicy.common.util;

public class GlobalConstants {

	public static final String PATH_API_YOUKU = "http://v.youku.com/player/getPlayList/VideoIDS/";
	public static final String PATH_TEMPLETE_YOUKU = "http://f.youku.com/player/getFlvPath/sid/_{sid_param}_/st/_{type_param}_/fileid/_{fileid_param}_?K=_{key_param}_";
	public static final String PATH_SITE_TUDOU = "http://www.tudou.com/programs/view/";
	public static final String PATH_API_ROOT = "http://api.bilibili.tv/";
	public static final String PATH_API_SINA = "http://v.iask.com/v_play.php?vid=";
	public static final String PATH_API_TUDOU = "http://v2.tudou.com/v?vn=02&st=1%2C2&it=";
	public static final String PATH_FOR_LIST = "list?";
	public static final String PATH_FOR_DETAIL = "view?";
	public static final String PATH_FOR_COMMENT = "feedback?";
	public static final String PATH_FOR_SEARCH = "http://www.bilibili.tv/search?";
	public static final String PATH_FOR_DM="http://www.bilibili.tv/dm,";
	public static final String PARAM_FOR_SEARCH_KEYWORD = "keyword=";
	public static final String PARAM_FOR_SEARCH_ORDERBY = "orderby=";
	public static final String PARAM_FOR_SEARCH_PAGENUM = "PageNo=";
	public static final Integer DM_SPEED=10;
	
	public static final String PARAM_FOR_XML = "type=xml";
	public static final String PARAM_FOR_PAGENUM = "page=";
	public static final String PARAM_FOR_VIEW_AID = "id=";
	public static final String PARAM_FOR_COMMENT_AID = "aid=";
	public static final String PARAM_FOR_ORDER = "order=";
	public static final String PARAM_FOR_PAGESIZE = "pagesize=";
	public static final String PARAM_FOR_TID = "tid=";

	public static final String XML_NAME_SINA_DATA = "video";
	public static final String XML_NAME_SINA_DATA_RESULT = "result";
	public static final String XML_NAME_SINA_DATA_TIMELENGTH = "timelength";
	public static final String XML_NAME_SINA_DATA_FRAMECOUNT = "framecount";
	public static final String XML_NAME_SINA_DATA_VNAME = "vname";
	public static final String XML_NAME_SINA_DATA_DURL = "durl";
	public static final String XML_NAME_SINA_DATA_ORDER = "order";
	public static final String XML_NAME_SINA_DATA_LENGTH = "length";
	public static final String XML_NAME_SINA_DATA_URL = "url";

	public static final String XML_NAME_AV_DATA = "data";
	public static final String XML_NAME_AV_INFO_DATA = "info";
	public static final String XML_NAME_TUDOU_DATA = "f";
	public static final String XML_NAME_AV_COMMENT_USERID = "mid";
	public static final String XML_NAME_AV_COMMENT_USERNAME = "nick";
	public static final String XML_NAME_AV_COMMENT_ICON_URL = "face";
	public static final String XML_NAME_AV_COMMENT_CONTENT = "msg";
	public static final String XML_NAME_AV_COMMENT_RANK = "rank";

	public static final String XML_NAME_AV_INFO_PLAY_COUNT = "play";
	public static final String XML_NAME_AV_INFO_REVIEW_COUNT = "review";
	public static final String XML_NAME_AV_INFO_VIDEO_REVIEW_COUNT = "video_review";
	public static final String XML_NAME_AV_INFO_FAVORITES = "favorites";
	public static final String XML_NAME_AV_INFO_TITLE = "title";
	public static final String XML_NAME_AV_INFO_DESCRIPTION = "description";
	public static final String XML_NAME_AV_INFO_TAG = "tag";
	public static final String XML_NAME_AV_INFO_COVER_PIC = "pic";
	public static final String XML_NAME_AV_INFO_PAGE_COUNT = "pages";

	public static final String XML_NAME_AV_VIDEO_INFO_FROM_SRC = "from";
	public static final String XML_NAME_AV_VIDEO_INFO_VID = "vid";
	public static final String XML_NAME_AV_VIDEO_INFO_OFFSITE = "offsite";

	public static final String XML_NAME_AV_LIST_AID = "aid";
	public static final String XML_NAME_AV_LIST_TITLE = "title";
	public static final String XML_NAME_AV_LIST_REVIEW = "review";
	public static final String XML_NAME_AV_LIST_PLAY_COUNT = "play";
	public static final String XML_NAME_AV_LIST_VIDEO_REVIEW = "video_review";
	public static final String XML_NAME_AV_LIST_FAVORITES = "favorites";
	public static final String XML_NAME_AV_LIST_AUTHOR = "author";
	public static final String XML_NAME_AV_LIST_DESCRIPTION = "description";
	public static final String XML_NAME_AV_LIST_POST_TIME = "create";
	public static final String XML_NAME_AV_LIST_COVER_PIC = "pic";
	
	
	public static final String XML_NAME_AV_DM_ITEM="d";
	
	
	public static final String DB_NAME = "db_bilibili";
	public static final int MARKET_VERSION_CODE = 2;
	public static final String API_KEY = "5e34bba76e89138f";
	public static final String TYPE_ORDER_NEW = "new"; // ����Ͷ������
	public static final String TYPE_ORDER_REVIEW = "review"; // ������������
	public static final String TYPE_ORDER_HOT = "hot";// �����ų̶�����
	public static final String TYPE_ORDER_DAMKU = "damku";// ����Ļ������
	public static final String TYPE_ORDER_COMMENT = "comment";// ���Ƽ�������
	public static final String TYPE_ORDER_PROMOTE = "promote";// ����������(Ӳ��)

	public static final int TYPE_ORDER_NEW_ITEM = 0;
	public static final int TYPE_ORDER_REVIEW_ITEM = 1;
	public static final int TYPE_ORDER_HOT_ITEM = 2;
	public static final int TYPE_ORDER_DAMKU_ITEM = 3;
	public static final int TYPE_ORDER_COMMENT_ITEM = 4;
	public static final int TYPE_ORDER_PROMOTE_ITEM = 5;

	public static final String INTENT_EXTRA_NAME_AVINFO_AID = "avinfo_aid";

	public static final int MSG_AVLIST_THREAD_INIT_DATA = 1;
	public static final int MSG_AVLIST_THREAD_INIT_COVER = 2;
	public static final int MSG_AVLIST_THREAD_LIST_UPDATE = 3;
	public static final int MSG_AVLIST_MAIN_INIT_DATA = 1;
	public static final int MSG_AVLIST_MAIN_INIT_COVER = 2;
	public static final int MSG_AVLIST_MAIN_UPDATE_LIST = 3;

	public static final int MSG_AVINFO_MAIN_INITDATA = 1;
	public static final int MSG_AVINFO_MAIN_INIT_PART = 2;

	public static final int MSG_AVCOMMENT_THREAD_INIT_DATA = 1;
	public static final int MSG_AVCOMMENT_THREAD_INIT_COVER = 2;
	public static final int MSG_AVCOMMENT_THREAD_LIST_UPDATE = 3;
	public static final int MSG_AVCOMMENT_MAIN_INIT_DATA = 1;
	public static final int MSG_AVCOMMENT_MAIN_INIT_COVER = 2;
	public static final int MSG_AVCOMMENT_MAIN_UPDATE_LIST = 3;
	public static final int CONNECTION_TIME_OUT_VALUE = 100000;

	public static final String[] LIST_ORDER_ITEMS = { "��Ͷ��", "������", "���ų̶�",
			"��Ļ", "������", "����" };

	public static final int MSG_SEARCH_THREAD_INIT_DATA = 1;
	public static final int MSG_SEARCH_THREAD_UPDATE_LIST = 2;
	public static final int MSG_SEARCH_THREAD_LOAD_PIC = 3;
	public static final int MSG_SEARCH_MAIN_INIT_DATA = 1;
	public static final int MSG_SEARCH_MAIN_UPDATE_LIST = 2;
	public static final int MSG_SEARCH_MAIN_REFRESH_PIC = 3;
	
	public static final int MSG_YOOPLAYER_MAIN_INIT_PLAYER=1;
	public static final int MSG_YOOPLAYER_UPDATE_DM=2;
	
	public static final String APP_PACKAGE_NAME="com.zccicy";
	public static final String PATH_DM_XML="/data/data/"+APP_PACKAGE_NAME+"/"+"dm_path/";
	
	// public static final int CATEGORYID_ANIME= 1;
	// public static final int CATEGORYID_ANIME_MAD_AMV = 24;
	// public static final int CATEGORYID_ANIME_MMD_3D = 25;
	// public static final int CATEGORYID_ANIME_2D_KICHIKU = 26;
	// public static final int CATEGORYID_ANIME_JOURNAL=27;
	// public static final int CATEGORYID_MUSIC=3;
	// public static final int CATEGORYID_MUSIC_VIDEO=28;
	// public static final int CATEGORYID_MUSIC_NORMAL=29;
	// public static final int CATEGORYID_MUSIC_VOCALOID=30;
	// public static final int CATEGORYID_MUSIC_COVER=31;
	// public static final int CATEGORYID_GAME=4;
	// public static final int CATEGORYID_GAME_FLASH=16;
	// public static final int CATEGORYID_GAME_VIDEO=17;
	// public static final int CATEGORYID_GAME_COMMENTARY=18;
	// public static final int CATEGORYID_GAME_MUGEN=19;
	// public static final int CATEGORYID_ENTERTAINMENT=5;
	// public static final int CATEGORYID_ENTERTAINMENT_DANCE=20;
	// public static final int CATEGORYID_ENTERTAINMENT_LIFE=21;
	// public static final int CATEGORYID_ENTERTAINMENT_KICHIKU=22;
	// public static final int CATEGORYID_ENTERTAINMENT_TELIVISION=23;
	// public static final int CATEGORYID_ALBUM=11;
	// public static final int CATEGORYID_ALBUM_3D=15;
	// public static final int CATEGORYID_ALBUM_2D=32;
	// public static final int CATEGORYID_NEW=13;
	// public static final int CATEGORYID_NEW_2D=33;
	// public static final int CATEGORYID_NEW_3D=34;
	//
	//

}

package com.zccicy.xml.datahandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.content.Context;

import com.zccicy.common.util.BaseDataHandler;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.dao.AvVideoInfoDao;
import com.zccicy.model.AvVideoInfo;

public class AvVideoInfoDataHandler extends BaseDataHandler<AvVideoInfo> {

	
	private Context context;
	private int aid;
	private int pageNum;
	private AvVideoInfoDao avVideoInfoDao;
	
	public AvVideoInfoDataHandler(Context context,int aid,int pageNum) {
		super();
		this.context=context;
		this.aid=aid;
		this.pageNum=pageNum;
//		this.avVideoInfoDao=new AvVideoInfoDao(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		String tempString=builder.toString();
		tempString=tempString.replace('\n', ' ');
		tempString=tempString.trim();
		if (this.currentPost != null) {
			if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_VIDEO_INFO_FROM_SRC)) {
				currentPost.setVideo_from_src(tempString);
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_VIDEO_INFO_VID)) {
					currentPost.setVid(tempString);
				 
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_VIDEO_INFO_OFFSITE)) {
				currentPost.setOffsite(tempString);
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_DATA)) {
				posts.add(currentPost);
//				avVideoInfoDao.update(currentPost);
			}
			builder.setLength(0);
		}
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if (localName
				.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_DATA)) {
			this.currentPost = new AvVideoInfo();
			this.currentPost.setAid(aid);
			this.currentPost.setPage(pageNum);
		}
	}

}

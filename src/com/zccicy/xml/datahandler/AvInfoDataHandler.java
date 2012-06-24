package com.zccicy.xml.datahandler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.content.Context;

import com.zccicy.common.util.BaseDataHandler;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.dao.AvInfoDao;
import com.zccicy.model.AvInfo;

public class AvInfoDataHandler extends BaseDataHandler<AvInfo> {
	private int aid=-1;
	private Context context;
	private AvInfoDao avInfoDao;
	
	
	
	public AvInfoDataHandler(int aid,Context context) {
		super();
		this.aid=aid;
		this.context=context;
		avInfoDao=new AvInfoDao(context);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		String tempString=builder.toString();
		tempString=tempString.replace("\n", "");
		tempString=tempString.trim();
		
		if (this.currentPost != null) {
			if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_PLAY_COUNT)) {
				this.currentPost=avInfoDao.getModelByPk(aid+"");
				if (this.currentPost==null)
				{
					this.currentPost=new AvInfo();
					this.currentPost.setAid(aid);
				}
				
				try {
					currentPost.setPlay_count(Integer.parseInt(tempString));
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setPlay_count(-1);
					
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_VIDEO_REVIEW_COUNT)) {
				try {
					currentPost.setVideo_review_count(Integer.parseInt(tempString));
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setVideo_review_count(-1);
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_REVIEW_COUNT)) {
				try {
					currentPost.setReview_count(Integer.parseInt(tempString));
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setReview_count(-1);
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_FAVORITES)) {
				try {
					currentPost.setFavorites(Integer.parseInt(tempString));
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setFavorites(-1);
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_TITLE)) {
				currentPost.setTitle(tempString);
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_DESCRIPTION)) {
				currentPost.setAv_desc(tempString);
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_TAG)) {
				currentPost.setTag(tempString);
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_COVER_PIC)) {
				currentPost.setCover_pic_url(tempString);
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_PAGE_COUNT)) {
				try {
					currentPost.setPage_count(Integer.parseInt(tempString));
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setPage_count(-1);
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_DATA)) {
				posts.add(currentPost);
				avInfoDao.update(this.currentPost);
			}
			builder.setLength(0);
		}
	}

	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if (localName
				.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_INFO_DATA)) {
			this.currentPost = new AvInfo();
			this.currentPost.setAid(aid);
			
		}
	}

}

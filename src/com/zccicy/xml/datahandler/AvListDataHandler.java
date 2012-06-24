package com.zccicy.xml.datahandler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.content.Context;

import com.zccicy.common.util.BaseDataHandler;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.common.util.StringArray;
import com.zccicy.dao.AvInfoDao;
import com.zccicy.model.AvInfo;
import com.zccicy.model.AvList;
import com.zccicy.model.AvVideoInfo;

public class AvListDataHandler extends BaseDataHandler<AvList> {

	private List<AvInfo> avInfos;
	private AvInfo avInfo;
	private Integer pageNum;
 
	private Integer pageSize;
	private AvInfoDao avInfoDao;
	
	public AvListDataHandler(Context context,int pageNum,int pageSize) {
		super();
		avInfoDao=new AvInfoDao(context);
		// TODO Auto-generated constructor stub
		this.pageNum=pageNum;
		this.pageSize=pageSize;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		
		if (this.currentPost != null) {
			String tempString=builder.toString();
			tempString.replace('\n', ' ');
			if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_LIST_AID)) {
				try {
					avInfo=null;
					avInfo=avInfoDao.getModelByPk(Integer.parseInt(tempString.trim())+"");
					if (avInfo==null)
					{
						avInfo=new AvInfo();
					}
					avInfo.setAid(Integer.parseInt(tempString.trim()));
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
					avInfo.setAid(-1);
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_LIST_TITLE)) {
				avInfo.setTitle(tempString.trim());

			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_LIST_PLAY_COUNT)) {
				try {
				 
					avInfo.setPlay_count(Integer.parseInt(tempString.trim()));
				} catch (Exception e) {
					// TODO: handle exception
					avInfo.setPlay_count(-1);
				}
			} 
			
			else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_LIST_REVIEW)) {
				try {
					 
					avInfo.setReview_count(Integer.parseInt(tempString.trim()));
				} catch (Exception e) {
					// TODO: handle exception
					avInfo.setReview_count(-1);
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_LIST_VIDEO_REVIEW)) {
				try {
					 
					avInfo.setVideo_review_count(Integer.parseInt(tempString.trim()));
				} catch (Exception e) {
					// TODO: handle exception
					avInfo.setVideo_review_count(-1);
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_LIST_FAVORITES)) {
				try {
					 
					avInfo.setFavorites(Integer.parseInt(tempString.trim()));
				} catch (Exception e) {
					// TODO: handle exception
					avInfo.setFavorites(-1);
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_LIST_AUTHOR)) {
				avInfo.setAuthor(tempString.trim());
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_LIST_DESCRIPTION)) {
				avInfo.setAv_desc(tempString.trim());
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_LIST_POST_TIME)) {
				avInfo.setPost_time(tempString.trim());
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_LIST_COVER_PIC)) {
				avInfo.setCover_pic_url(tempString.trim());
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_DATA)) {
				avInfos.add(avInfo);
				avInfoDao.update(avInfo);
			}
			builder.setLength(0);
		}
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		this.currentPost = new AvList();
		avInfos = new ArrayList<AvInfo>();
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		StringArray tList=currentPost.getAid_list();
 
		//≤π∆ÎList
		if (tList.getStringArray().size()<pageNum*pageSize)
		{
			for (int i=tList.getStringArray().size();i<pageNum*pageSize;i++)
			{
				tList.getStringArray().add("0");
			}
		}
		//≤πΩ¯»•
		try {
			for (int i = 0; i < avInfos.size(); i++) {
 				tList.getStringArray().add((pageNum-1*pageSize)+i, avInfos.get(i).getAid()+"");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if (localName.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_DATA)) {

			avInfo = new AvInfo();
		}
	}

	public List<AvInfo> getAvInfos() {
		return avInfos;
	}

	public void setAvInfos(List<AvInfo> avInfos) {
		this.avInfos = avInfos;
	}
	
	

}

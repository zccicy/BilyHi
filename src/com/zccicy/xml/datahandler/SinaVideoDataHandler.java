package com.zccicy.xml.datahandler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.zccicy.common.util.BaseDataHandler;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.model.AvComment;
import com.zccicy.model.SinaData;
import com.zccicy.model.SinaUrlData;
import com.zccicy.model.TudouData;

import dalvik.system.TemporaryDirectory;

public class SinaVideoDataHandler extends BaseDataHandler<SinaData> {

	private String vid = "";
	private SinaUrlData sinaUrlData = null;
	private List<SinaUrlData> urlList=null;

	public SinaVideoDataHandler(String vid) {
		super();
		this.vid = vid;
		urlList=new ArrayList<SinaUrlData>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub

		super.endElement(uri, localName, qName);
		if (this.currentPost != null) {
			String tmpString = builder.toString();
			tmpString.replace('\n', ' ');
			tmpString = tmpString.trim();
			if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_SINA_DATA_RESULT)) {
				try {
					currentPost.setResult(tmpString);
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setResult("");
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_SINA_DATA_TIMELENGTH)) {
				try {
					currentPost.setTime_length(Integer.parseInt(tmpString));
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setTime_length(0);
				}
			} 
			else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_SINA_DATA_FRAMECOUNT)) {
				try {
					currentPost.setFrame_count(Integer.parseInt(tmpString));
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setFrame_count(0);
				}
			}
			else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_SINA_DATA_VNAME)) {
				try {
					currentPost.setV_name(tmpString);
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setV_name("");
				}
			}
			else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_SINA_DATA_ORDER)) {
				try {
					sinaUrlData.setOrder(Integer.parseInt(tmpString));
				} catch (Exception e) {
					// TODO: handle exception
					sinaUrlData.setOrder(0);
				}
			}
			else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_SINA_DATA_LENGTH)) {
				try {
					sinaUrlData.setLength(Integer.parseInt(tmpString));
				} catch (Exception e) {
					// TODO: handle exception
					sinaUrlData.setLength(0);
				}
			}
			else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_SINA_DATA_URL)) {
				try {
					sinaUrlData.setUrl(tmpString);
				} catch (Exception e) {
					// TODO: handle exception
					sinaUrlData.setUrl("");
				}
			}
			else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_SINA_DATA_DURL)) {
				this.urlList.add(sinaUrlData);
			}
			 
			else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_SINA_DATA)) {
				try {
					this.currentPost.setUrlData(this.urlList);
					this.posts.add(currentPost);
				} catch (Exception e) {
					// TODO: handle exception
				 
				}
			}
			builder.setLength(0);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if (localName.equalsIgnoreCase(GlobalConstants.XML_NAME_SINA_DATA)) {
			this.currentPost = new SinaData();
			this.currentPost.setVid(vid);
		}
		if (localName.equalsIgnoreCase(GlobalConstants.XML_NAME_SINA_DATA_DURL)) {
			sinaUrlData = new SinaUrlData();
		}
	}

}

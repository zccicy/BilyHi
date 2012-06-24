package com.zccicy.xml.datahandler;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.zccicy.common.util.BaseDataHandler;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.model.AvComment;
import com.zccicy.model.TudouData;

public class TudouVideoDataHandler extends BaseDataHandler<TudouData> {

	
 
	private String iid="";
	
	 

	public TudouVideoDataHandler(String iid) {
		super();
		this.iid=iid;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub

		super.endElement(uri, localName, qName);
		if (this.currentPost != null) {
			if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_TUDOU_DATA)) {
				try {
					String tempString=builder.toString().trim();
					currentPost.setAddress(tempString);
					this.posts.add(currentPost);
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setAddress("");
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
		if (localName
				.equalsIgnoreCase(GlobalConstants.XML_NAME_TUDOU_DATA)) {
			this.currentPost = new TudouData();
			this.currentPost.setIid(iid);
		}
	}

}

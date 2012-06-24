package com.zccicy.xml.datahandler;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.zccicy.common.util.BaseDataHandler;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.model.AvComment;

public class AvCommentDataHandler extends BaseDataHandler<AvComment> {

	

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub

		super.endElement(uri, localName, qName);
		String tmpString=builder.toString();
		tmpString=tmpString.replace('\n', ' ');
		tmpString=tmpString.trim();
		if (this.currentPost != null) {
			if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_COMMENT_USERID)) {
				try {
					currentPost
							.setUser_id(Integer.parseInt(tmpString));
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setUser_id(-1);
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_COMMENT_USERNAME)) {
				currentPost.setUser_name(tmpString);
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_COMMENT_ICON_URL)) {
				currentPost.setUser_face_icon(tmpString);
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_COMMENT_CONTENT)) {
				currentPost.setComment_content(tmpString);
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_COMMENT_RANK)) {
				try {
					currentPost.setRank(Integer.parseInt(tmpString));
				} catch (Exception e) {
					// TODO: handle exception
					currentPost.setRank(-1);
				}
			} else if (localName
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_DATA)) {
				posts.add(currentPost);
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
				.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_DATA)) {
			this.currentPost = new AvComment();
		}
	}

}

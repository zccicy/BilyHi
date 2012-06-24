package com.zccicy.xml.datahandler;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.net.http.SslCertificate.DName;

import com.zccicy.common.util.BaseDataHandler;
import com.zccicy.common.util.GlobalConstants;
import com.zccicy.model.AvComment;
import com.zccicy.model.AvDmItem;

public class AvDmItemDataHandler extends BaseDataHandler<AvDmItem> {
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
					.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_DM_ITEM)) {
				currentPost.setDm_content(tmpString);
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
				.equalsIgnoreCase(GlobalConstants.XML_NAME_AV_DM_ITEM)) {
			this.currentPost = new AvDmItem();
			String dmInfo=attributes.getValue(0);
			String[] dmInfos=dmInfo.split(",");
			this.currentPost.setSend_time(Float.parseFloat(dmInfos[0]));
			this.currentPost.setDm_mode(Integer.parseInt(dmInfos[1]));
			this.currentPost.setFont_size(Integer.parseInt(dmInfos[2]));
			this.currentPost.setFont_color(Integer.parseInt(dmInfos[3]));
			this.currentPost.setPost_time(Integer.parseInt(dmInfos[4]));
			this.currentPost.setIs_spec(Integer.parseInt(dmInfos[5]));
			this.currentPost.setSend_people(dmInfos[6]);
			this.currentPost.setDm_id(Integer.parseInt(dmInfos[7]));
		}
	}

}

package com.zccicy.xml.parser;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.zccicy.common.util.BaseFeedParser;
import com.zccicy.model.AvComment;
import com.zccicy.model.AvInfo;
import com.zccicy.model.SinaData;
import com.zccicy.model.TudouData;
import com.zccicy.xml.datahandler.AvCommentDataHandler;
import com.zccicy.xml.datahandler.AvInfoDataHandler;
import com.zccicy.xml.datahandler.SinaVideoDataHandler;
import com.zccicy.xml.datahandler.TudouVideoDataHandler;

public class SinaDataParser extends BaseFeedParser<SinaData> {

	private String url;
	private String vid;
	public  SinaDataParser(String feedUrl,String vid) {
		super(feedUrl);
		this.url=feedUrl;
		this.vid=vid;
		// TODO Auto-generated constructor stub
	}

 
	 public List<SinaData> parse() {
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        try {
	            SAXParser parser = factory.newSAXParser();
	            SinaVideoDataHandler handler = new SinaVideoDataHandler(vid);
	            parser.parse(this.getInputStream(), handler);
	            return handler.getPostMessages();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        } 
	    }
}

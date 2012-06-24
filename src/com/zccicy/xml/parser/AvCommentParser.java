package com.zccicy.xml.parser;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.zccicy.common.util.BaseFeedParser;
import com.zccicy.model.AvComment;
import com.zccicy.model.AvInfo;
import com.zccicy.xml.datahandler.AvCommentDataHandler;
import com.zccicy.xml.datahandler.AvInfoDataHandler;

public class AvCommentParser extends BaseFeedParser<AvComment> {

	public  AvCommentParser(String feedUrl) {
		super(feedUrl);
		// TODO Auto-generated constructor stub
	}

 
	 public List<AvComment> parse() {
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        try {
	            SAXParser parser = factory.newSAXParser();
	            AvCommentDataHandler handler = new AvCommentDataHandler();
	            parser.parse(this.getInputStream(), handler);
	            return handler.getPostMessages();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        } 
	    }
}

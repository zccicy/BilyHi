package com.zccicy.xml.parser;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.zccicy.common.util.BaseFeedParser;
import com.zccicy.model.TudouData;
import com.zccicy.xml.datahandler.TudouVideoDataHandler;

public class TudouDataParser extends BaseFeedParser<TudouData> {

	private String url;
	private String iid;
	public  TudouDataParser(String feedUrl,String iid) {
		super(feedUrl);
		this.url=feedUrl;
		this.iid=iid;
		// TODO Auto-generated constructor stub
	}

 
	 public List<TudouData> parse() {
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        try {
	            SAXParser parser = factory.newSAXParser();
	            TudouVideoDataHandler handler = new TudouVideoDataHandler(iid);
	            parser.parse(this.getInputStream(), handler);
	            return handler.getPostMessages();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        } 
	    }
}

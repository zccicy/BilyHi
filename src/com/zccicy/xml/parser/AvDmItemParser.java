package com.zccicy.xml.parser;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.zccicy.common.util.BaseFeedParser;
import com.zccicy.model.AvDmItem;
import com.zccicy.xml.datahandler.AvDmItemDataHandler;

public class AvDmItemParser extends BaseFeedParser<AvDmItem> {

	public  AvDmItemParser(String feedUrl) {
	
		super(feedUrl);
		System.out.println("dmurl"+feedUrl);
		// TODO Auto-generated constructor stub
	}

 
	 public List<AvDmItem> parse() {
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        try {
	            SAXParser parser = factory.newSAXParser();
	            AvDmItemDataHandler handler = new AvDmItemDataHandler();
	            parser.parse(this.getInputStream(), handler);
	            return handler.getPostMessages();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        } 
	    }
}

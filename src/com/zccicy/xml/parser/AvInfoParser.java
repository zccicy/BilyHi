package com.zccicy.xml.parser;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.content.Context;

import com.zccicy.common.util.BaseDataHandler;
import com.zccicy.common.util.BaseFeedParser;
import com.zccicy.model.AvInfo;
import com.zccicy.xml.datahandler.AvInfoDataHandler;

public class AvInfoParser extends BaseFeedParser<AvInfo> {

	private Context context;
	private int aid;
	public  AvInfoParser(String feedUrl,int aid,Context context) {
		super(feedUrl);
		this.aid=aid;
		this.context=context;
		// TODO Auto-generated constructor stub
	}
    public List<AvInfo> parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            AvInfoDataHandler handler = new AvInfoDataHandler(aid,context);
            parser.parse(this.getInputStream(), handler);
            return handler.getPostMessages();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
    }
    
 
	
}

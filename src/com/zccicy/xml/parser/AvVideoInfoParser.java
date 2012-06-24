package com.zccicy.xml.parser;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.content.Context;

import com.zccicy.common.util.BaseFeedParser;
import com.zccicy.model.AvInfo;
import com.zccicy.model.AvVideoInfo;
import com.zccicy.xml.datahandler.AvVideoInfoDataHandler;

public class AvVideoInfoParser extends BaseFeedParser<AvVideoInfo> {

	private Context context;
	private int aid;
	private int pageNum;
	public  AvVideoInfoParser(String feedUrl,int aid,int pageNum,Context context) {
		super(feedUrl);
		this.aid=aid;
		this.pageNum=pageNum;
		this.context=context;
		// TODO Auto-generated constructor stub
	}
    public List<AvVideoInfo> parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            AvVideoInfoDataHandler handler = new AvVideoInfoDataHandler(context,aid,pageNum);
            parser.parse(this.getInputStream(), handler);
            return handler.getPostMessages();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
    }
    
 
	
}

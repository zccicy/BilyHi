package com.zccicy.xml.parser;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.content.Context;

import com.zccicy.common.util.BaseDataHandler;
import com.zccicy.common.util.BaseFeedParser;
import com.zccicy.model.AvInfo;
import com.zccicy.model.AvList;
import com.zccicy.xml.datahandler.AvInfoDataHandler;
import com.zccicy.xml.datahandler.AvListDataHandler;

public class AvListParser extends BaseFeedParser<AvList> {

	private int pageNum;
	private int pageSize;
	private Context context;
	private List<AvInfo> list;
	
	public  AvListParser(Context context,String feedUrl,int pageNum,int pageSize) {
		super(feedUrl);
		this.pageNum=pageNum;
		this.pageSize=pageSize;
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	
	public List<AvInfo> getAvInfoList()
	{
		return this.list;
	}
	
	 public List<AvList> parse() {
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        try {
	            SAXParser parser = factory.newSAXParser();
	            AvListDataHandler handler = new AvListDataHandler(context,pageNum,pageSize);
	            parser.parse(this.getInputStream(), handler);
	            this.list=handler.getAvInfos();
	            return handler.getPostMessages();
	        } catch (Exception e) {
	        	e.printStackTrace();
 	            throw new RuntimeException(e);
	        } 
	    }
	 

}

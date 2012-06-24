package com.zccicy.model;

import java.io.Serializable;
import java.util.List;

public class AvVideoInfo implements Serializable{

 
	private Integer aid=0;
	private Integer page=0;
	private String video_from_src="";
	private String vid="";
	private String offsite="";
	private List<String> urlList;
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
 		return aid+" - "+page+" - "+video_from_src+" - "+offsite;
//		return "";
	}
	public List<String> getUrlList() {
		return urlList;
	}
	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}
	public static String getTableName()
	{
		return "av_video_info";
	}
	public static String[] getPrimaryKey()
	{
		return new String[]{"aid","page"};
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String getVideo_from_src() {
		return video_from_src;
	}
	public void setVideo_from_src(String video_from_src) {
		this.video_from_src = video_from_src;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getOffsite() {
		return offsite;
	}
	public void setOffsite(String offsite) {
		this.offsite = offsite;
	}
	
	
}

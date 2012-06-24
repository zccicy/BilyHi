package com.zccicy.model;

import java.io.Serializable;

public class SinaUrlData implements Serializable{

	private int order;
	private int length;
	private String url;
	
	
	
	
	public SinaUrlData() {
		super();
		order=0;
		length=0;
		url="";
		// TODO Auto-generated constructor stub
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}

package com.zccicy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SinaData implements Serializable {
	private String vid;
	private String result;
	private int time_length;
	private int frame_count;
	private String v_name;
	private String src;
	private List<SinaUrlData> urlData;
	private String vtags;
	private String vstr;
	private String vip;
	private int vround;

	public SinaData() {
		super();
		vid="";
		result = "";
		time_length = 0;
		frame_count = 0;
		v_name = "";
		src = "";
		urlData = new ArrayList<SinaUrlData>();
		vtags = "";
		vstr = "";
		vip = "";
		vround = 0;
		// TODO Auto-generated constructor stub
	}
	

	public String getVid() {
		return vid;
	}


	public void setVid(String vid) {
		this.vid = vid;
	}


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTime_length() {
		return time_length;
	}

	public void setTime_length(int time_length) {
		this.time_length = time_length;
	}

	public int getFrame_count() {
		return frame_count;
	}

	public void setFrame_count(int frame_count) {
		this.frame_count = frame_count;
	}

	public String getV_name() {
		return v_name;
	}

	public void setV_name(String v_name) {
		this.v_name = v_name;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public List<SinaUrlData> getUrlData() {
		return urlData;
	}

	public void setUrlData(List<SinaUrlData> urlData) {
		this.urlData = urlData;
	}

	public String getVtags() {
		return vtags;
	}

	public void setVtags(String vtags) {
		this.vtags = vtags;
	}

	public String getVstr() {
		return vstr;
	}

	public void setVstr(String vstr) {
		this.vstr = vstr;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public int getVround() {
		return vround;
	}

	public void setVround(int vround) {
		this.vround = vround;
	}

}

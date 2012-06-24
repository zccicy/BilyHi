package com.zccicy.model;

import java.io.Serializable;

public class AvDmItem implements Serializable {

	private int dm_id;
	private Float send_time;
	private int dm_mode;
	private int font_size;
	private long font_color;
	private int is_spec;
	private int post_time;
	private String send_people;
	private String dm_content;
	private int dm_x;
	private int dm_y;
	private boolean channel__lock;
	private int dm_width;
	
	
	public AvDmItem() {
		super();
		dm_id=0;
		send_time=(float) 0;
		dm_mode=0;
		font_size=0;
		font_color=0;
		is_spec=0;
		post_time=0;
		send_people="";
		dm_content="缺省字幕";
		dm_x=0;
		dm_y=-50;
		dm_width=0;
		channel__lock=false;
		// TODO Auto-generated constructor stub
	}
	public int getDm_id() {
		return dm_id;
	}
	public void setDm_id(int dm_id) {
		this.dm_id = dm_id;
	}
  
	public Float getSend_time() {
		return send_time;
	}
	public void setSend_time(Float send_time) {
		this.send_time = send_time;
	}
	public int getDm_mode() {
		return dm_mode;
	}
	public void setDm_mode(int dm_mode) {
		this.dm_mode = dm_mode;
	}
	public int getFont_size() {
		return font_size;
	}
	public void setFont_size(int font_size) {
		this.font_size = font_size;
	}
	public long getFont_color() {
		return font_color;
	}
	public void setFont_color(long font_color) {
		this.font_color = font_color;
	}
 
	public void setIs_spec(int is_spec) {
		this.is_spec = is_spec;
	}
	public int getPost_time() {
		return post_time;
	}
	public void setPost_time(int post_time) {
		this.post_time = post_time;
	}
	public String getSend_people() {
		return send_people;
	}
	public void setSend_people(String send_people) {
		this.send_people = send_people;
	}
	public String getDm_content() {
		return dm_content;
	}
	public void setDm_content(String dm_content) {
		this.dm_content = dm_content;
	}
	public int getIs_spec() {
		return is_spec;
	}
	public int getDm_x() {
		return dm_x;
	}
	public void setDm_x(int dm_x) {
		this.dm_x = dm_x;
	}
	public int getDm_y() {
		return dm_y;
	}
	public void setDm_y(int dm_y) {
		this.dm_y = dm_y;
	}
	public int getDm_width() {
		return dm_width;
	}
	public void setDm_width(int dm_width) {
		this.dm_width = dm_width;
	}
	
	
	
	
	
	public boolean isChannel__lock() {
		return channel__lock;
	}
	public void setChannel__lock(boolean channel__lock) {
		this.channel__lock = channel__lock;
	}
	@Override
	public String toString() {
		return "AvDmItem [dm_id=" + dm_id + ", send_time=" + send_time
				+ ", dm_mode=" + dm_mode + ", font_size=" + font_size
				+ ", font_color=" + font_color + ", is_spec=" + is_spec
				+ ", post_time=" + post_time + ", send_people=" + send_people
				+ ", dm_content=" + dm_content + ", dm_x=" + dm_x + ", dm_y="
				+ dm_y + ", dm_width=" + dm_width + "]";
	}
	
	
}

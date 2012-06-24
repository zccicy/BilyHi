package com.zccicy.model;

import java.io.Serializable;

public class AvInfo implements Serializable {

	private Integer aid = 0;
	private String author = "";
	private Integer play_count = 0;
	private String av_desc = "";
	private Integer review_count = 0;
	private Integer video_review_count = 0;
	private Integer favorites = 0;
	private String title = "";
	private String tag = "";
	private String cover_pic_url = "";
	private Integer page_count = 0;
	private String post_time = "";
	private Integer comment_count = 0;
	private Integer last_update_time = 0;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return aid + "-" + author + "-" + play_count + "-" + av_desc + "-"
				+ review_count + "-" + video_review_count + "-" + favorites
				+ "-" + title + "-" + tag + "-" + cover_pic_url+"-"+page_count+"-"+post_time;
	}

	public static String getTableName() {
		return "av_info";
	}

	public static String[] getPrimaryKey() {
		return new String[] { "aid" };
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPlay_count() {
		return play_count;
	}

	public void setPlay_count(Integer play_count) {
		this.play_count = play_count;
	}

	public Integer getReview_count() {
		return review_count;
	}

	public void setReview_count(Integer review_count) {
		this.review_count = review_count;
	}

	public Integer getVideo_review_count() {
		return video_review_count;
	}

	public void setVideo_review_count(Integer video_review_count) {
		this.video_review_count = video_review_count;
	}

	public Integer getFavorites() {
		return favorites;
	}

	public void setFavorites(Integer favorites) {
		this.favorites = favorites;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAv_desc() {
		return av_desc;
	}

	public void setAv_desc(String av_desc) {
		this.av_desc = av_desc;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getCover_pic_url() {
		return cover_pic_url;
	}

	public void setCover_pic_url(String cover_pic_url) {
		this.cover_pic_url = cover_pic_url;
	}

	public Integer getPage_count() {
		return page_count;
	}

	public void setPage_count(Integer page_count) {
		this.page_count = page_count;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getPost_time() {
		return post_time;
	}

	public void setPost_time(String post_time) {
		this.post_time = post_time;
	}

	public Integer getComment_count() {
		return comment_count;
	}

	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}

	public Integer getLast_update_time() {
		return last_update_time;
	}

	public void setLast_update_time(Integer last_update_time) {
		this.last_update_time = last_update_time;
	}

}

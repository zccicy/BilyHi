package com.zccicy.model;

import java.io.Serializable;

public class AvComment implements Serializable{

		private Integer user_id=-1;
		private String user_name="";
		private String user_face_icon="";
		private String comment_content="";
		private Integer rank=-1;
		public String getTableName()
		{
			return "av_comment";
		}
		public Integer getUser_id() {
			return user_id;
		}
		public void setUser_id(Integer user_id) {
			this.user_id = user_id;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public String getUser_face_icon() {
			return user_face_icon;
		}
		public void setUser_face_icon(String user_face_icon) {
			this.user_face_icon = user_face_icon;
		}
		public String getComment_content() {
			return comment_content;
		}
		public void setComment_content(String comment_content) {
			this.comment_content = comment_content;
		}
		public Integer getRank() {
			return rank;
		}
		public void setRank(Integer rank) {
			this.rank = rank;
		}
		
		
	
}

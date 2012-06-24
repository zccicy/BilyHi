package com.zccicy.model;

import java.io.Serializable;
import java.util.List;

import com.zccicy.common.util.StringArray;

public class AvList implements Serializable {
	private Integer tid=0;
	private Integer type=0;
	private Integer parent_id=0;
	private String type_name="";
	private StringArray aid_list=new StringArray(){};

	public AvList() {
		super();
		// TODO Auto-generated constructor stub
		tid=0;
		parent_id=0;
		type_name="";
		aid_list=new StringArray();
	}
	

	public static String getTableName()
	{
		return "av_list";
	}
	public static String[] getPrimaryKey()
	{
		return new String[]{"tid","type"};
	}
	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	

	
	public StringArray getAid_list() {
		return aid_list;
	}
	 
	public void setAid_list(String aid_list) {
		String[] result=aid_list.split(",");
		this.aid_list=new StringArray();
		for (int i=0;i<result.length;i++)
		{
			this.aid_list.getStringArray().add(result[i]);
		}
 
	}
	public void setAidList(StringArray aidList)
	{
		this.aid_list=aidList;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		 
		return super.toString();

	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}
	

}

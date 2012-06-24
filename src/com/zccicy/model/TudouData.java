package com.zccicy.model;

import java.io.Serializable;

public class TudouData implements Serializable {

	private String iid;
	private String address;
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}

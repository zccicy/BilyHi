package com.zccicy.common.util;

import java.util.ArrayList;
import java.util.List;

public class StringArray {

	private List<String> stringArray;
	

	public StringArray() {
		super();
		stringArray=new ArrayList<String>();
		// TODO Auto-generated constructor stub
	}

	public List<String> getStringArray() {
		return stringArray;
	}

	public void setStringArray( List<String> stringArray) {
		this.stringArray = stringArray;
	}

	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "";
		try {

			for (int i = 0; i < stringArray.size(); i++) {
				result += stringArray.get(i) + ",";

			}
			if (result.length() > 0) {
				result = result.substring(0, result.length() - 1);
				return result;
			}
		} catch (Exception e) {
			// TODO: handle exception

		}
		return "";
	}

}

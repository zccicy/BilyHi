package com.zccicy.common.comparator;

import java.util.Comparator;

import com.zccicy.model.AvDmItem;

public class ComparatorAvDmItem implements Comparator<AvDmItem>{

	@Override
	public int compare(AvDmItem item1, AvDmItem item2) {
		// TODO Auto-generated method stub
		return item1.getSend_time().compareTo(item2.getSend_time());
	}

	
}

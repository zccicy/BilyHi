package com.zccicy.common.util;

import java.util.Collections;
import java.util.List;

import com.zccicy.common.comparator.ComparatorAvDmItem;
import com.zccicy.model.AvDmItem;

public class MathUtil {

	public static void  orderDmList(List<AvDmItem> list)
	{
		ComparatorAvDmItem comparatorAvDmItem=new ComparatorAvDmItem();
		Collections.sort(list, comparatorAvDmItem);

	}
	
}

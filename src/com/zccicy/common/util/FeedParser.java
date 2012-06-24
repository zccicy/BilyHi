package com.zccicy.common.util;
import java.util.List;

public interface FeedParser<T> {
	List<T> parse();
}

package com.merchant.data;
import java.util.HashMap;
import java.util.Map;

public class ItemValuesDictionary {
	private static Map<String, Float> map = new HashMap<>();

	public static void addItemValue(String keyword, float itemValue){
		map.put(keyword , itemValue);
	}

	public static float getItemValue(String keyword){
		Float result = map.get(keyword);
		if (result==null) throw new ItemDoesNotExistException(keyword);
		return result;
	}

}

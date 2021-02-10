package com.merchant.data;

import java.util.HashMap;
import java.util.Map;

import com.merchant.roman.RomanCharacter;

public class TranslationDictionary {
	private static Map<String, RomanCharacter> map = new HashMap<>();

	public static void addTranslation(String keyword, RomanCharacter romanCharacter){
		map.put(keyword , romanCharacter);
	}

	public static RomanCharacter  getTranslation(String keyword){
		RomanCharacter result = map.get(keyword);
		if (result==null) throw new SpaceWordDoesNotExistException(keyword);

		return result;
	}

}

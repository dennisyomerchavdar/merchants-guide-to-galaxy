package com.merchant.input.definition;

import com.merchant.data.TranslationDictionary;
import com.merchant.roman.RomanCharacter;

public class TranslationDefinition implements IDefinition{
	String keyword;
	RomanCharacter correspondingCharacter;

	public TranslationDefinition(String keyword, RomanCharacter correspondingCharacter){
		this.keyword = keyword;
		this.correspondingCharacter = correspondingCharacter;
	}


	@Override
	public void applyDefinition() {
		TranslationDictionary.addTranslation(keyword,correspondingCharacter);
	}
}

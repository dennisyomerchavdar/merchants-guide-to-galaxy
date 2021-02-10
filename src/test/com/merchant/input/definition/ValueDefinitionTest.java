package com.merchant.input.definition;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.merchant.data.ItemValuesDictionary;
import com.merchant.data.TranslationDictionary;
import com.merchant.roman.RomanCharacter;

public class ValueDefinitionTest {

	public ValueDefinitionTest(){
		TranslationDictionary.addTranslation("aaa", RomanCharacter.I);
		TranslationDictionary.addTranslation("bbb", RomanCharacter.X);
	}

	@Test
	public void successfullyStoresInfoInItemValuesDictionary(){
		ValueDefinition translationDefinition = new ValueDefinition("aaa bbb", "bread" , 9);
		translationDefinition.applyDefinition();
		assertEquals(ItemValuesDictionary.getItemValue("bread"), 1, 0.0001);
	}

}

package com.merchant.input.definition;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.merchant.data.SpaceWordDoesNotExistException;
import com.merchant.data.TranslationDictionary;
import com.merchant.input.query.IntergalacticUnitTranslationQuery;
import com.merchant.roman.RomanCharacter;

public class TranslationDefinitionTest {

	@Test
	public void successfullyStoresInfoInDictionary(){
		TranslationDefinition translationDefinition = new TranslationDefinition("aaa", RomanCharacter.C);
		translationDefinition.applyDefinition();
		assertEquals( TranslationDictionary.getTranslation("aaa"), RomanCharacter.C);
	}


}

package com.merchant.input.query;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.merchant.data.SpaceWordDoesNotExistException;
import com.merchant.data.TranslationDictionary;
import com.merchant.roman.RomanCharacter;

public class IntergalacticUnitTranslationQueryTest {

	public IntergalacticUnitTranslationQueryTest(){
		TranslationDictionary.addTranslation("aaa", RomanCharacter.I);
		TranslationDictionary.addTranslation("bbb", RomanCharacter.X);
	}

	@Test (expected = SpaceWordDoesNotExistException.class)
	public void nonexistingValueGivesError(){
		IntergalacticUnitTranslationQuery translationQuery = new IntergalacticUnitTranslationQuery("zzzzzz");
		translationQuery.getAnswerForQuery();
	}

	@Test
	public void correctStringProducesCorrectResult(){
		IntergalacticUnitTranslationQuery translationQuery = new IntergalacticUnitTranslationQuery("aaa bbb");
		assertEquals(translationQuery.getAnswerForQuery() , "aaa bbb is 9");
	}
	
}

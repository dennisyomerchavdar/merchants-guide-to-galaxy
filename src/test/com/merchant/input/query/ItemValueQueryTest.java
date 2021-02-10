package com.merchant.input.query;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.merchant.data.ItemDoesNotExistException;
import com.merchant.data.ItemValuesDictionary;
import com.merchant.data.TranslationDictionary;
import com.merchant.roman.RomanCharacter;

public class ItemValueQueryTest {
	private final static String ITEM_NAME = "Leather";
	private final static int ITEM_COUNT = 3;
	private final static String INTERGALACTIC_WORD_FOR_ROMAN =  "gugu";
	private final static RomanCharacter ROMAN_CHAR =  RomanCharacter.L;

	public ItemValueQueryTest(){
		ItemValuesDictionary.addItemValue("Coconut" , 103);
		ItemValuesDictionary.addItemValue("Mouse" , 33);
		ItemValuesDictionary.addItemValue(ITEM_NAME , ITEM_COUNT);
		TranslationDictionary.addTranslation(INTERGALACTIC_WORD_FOR_ROMAN, ROMAN_CHAR);
	}

	@Test(expected = ItemDoesNotExistException.class)
	public void nonexistingValueGivesError(){
		ItemValueQuery itemValueQuery = new ItemValueQuery(INTERGALACTIC_WORD_FOR_ROMAN, "yaya");
		itemValueQuery.getAnswerForQuery();
	}

	@Test
	public void existingKeyCalculatedCorrectly(){
		ItemValueQuery itemValueQuery = new ItemValueQuery(INTERGALACTIC_WORD_FOR_ROMAN, ITEM_NAME);
		String res = itemValueQuery.getAnswerForQuery();
		res = res.replaceAll("\\D+","");
		assertEquals( Integer.valueOf(ROMAN_CHAR.value * ITEM_COUNT) , Integer.valueOf(res));
	}

}

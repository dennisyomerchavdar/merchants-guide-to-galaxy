package com.merchant.input.query;

import com.merchant.data.ItemValuesDictionary;
import com.merchant.roman.RomanString;

public class ItemValueQuery implements IQuery{
	String galacticRomanText;
	String itemName;
	float value;

	public ItemValueQuery(String galacticRomanText, String itemName){
		this.galacticRomanText = galacticRomanText;
		this.itemName = itemName;
		this.value = RomanString.getValueFromSpaceSeparatedGalacticRomanNumerals(galacticRomanText)* ItemValuesDictionary.getItemValue(itemName);
	}


	@Override
	public String getAnswerForQuery() {
		if (value%1==0){
			return galacticRomanText + " " + itemName+ " is " + (int)value + " Credits";
		}
		else{
			return galacticRomanText + " " + itemName+ " is " + value + " Credits";
		}


	}
}

package com.merchant.input.definition;

import com.merchant.data.ItemValuesDictionary;
import com.merchant.roman.RomanString;

public class ValueDefinition implements IDefinition{
	String galacticRomanText;
	String itemKeyword;
	float creditValue;

	public ValueDefinition(String galacticRomanText, String keyword, float creditValue){
		this.galacticRomanText=galacticRomanText;
		this.itemKeyword = keyword;
		this.creditValue = creditValue;
	}



	@Override
	public void applyDefinition() {
		float romanValue = RomanString.getValueFromSpaceSeparatedGalacticRomanNumerals(galacticRomanText);
		ItemValuesDictionary.addItemValue(itemKeyword,creditValue/romanValue);
	}
}

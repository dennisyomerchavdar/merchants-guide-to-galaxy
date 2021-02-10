package com.merchant.input.query;

import com.merchant.roman.RomanString;

public class IntergalacticUnitTranslationQuery implements IQuery{
	String galacticRomanText;
	int value;

	public IntergalacticUnitTranslationQuery(String galacticRomanText){
		this.galacticRomanText = galacticRomanText;
		this.value = RomanString.getValueFromSpaceSeparatedGalacticRomanNumerals(galacticRomanText);
	}

	@Override
	public String getAnswerForQuery() {
		return galacticRomanText + " is " + value;
	}
}

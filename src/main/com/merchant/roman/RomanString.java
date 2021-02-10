package com.merchant.roman;

import java.util.List;
import java.util.stream.Collectors;

import com.merchant.data.TranslationDictionary;

public class RomanString {
    private List<Integer> romanValueList;
    private int value;
    public RomanString(String romanString){
        try{
            romanValueList = romanString.chars()
                    .mapToObj(romanCharNumber ->  (char)romanCharNumber)
                    .map(romanChar -> RomanCharacter.valueOf(romanChar.toString()).value)
                    .collect(Collectors.toList());
            this.value = calculateValue();
        }
        catch(Exception e){
            new RomanStringException("Roman Numeral String is not valid, given: "+romanString, e);
        }
        RomanStringValidator.validate(romanString,romanValueList);


    }

    public int getValue(){
        return value;
    }


    private int calculateValue(){
        int total=0;

        for (int index = 0; index< romanValueList.size(); index++){
            int currentElementVal = romanValueList.get(index);
            if (index+1 == romanValueList.size()){
                total = total + currentElementVal;
            }
            else{//There is a next element
                int nextElementVal = romanValueList.get(index+1);
                if (currentElementVal < nextElementVal){
                    total = total - currentElementVal;
                }
                else{
                    total = total + currentElementVal;
                }
            }

        }
        return total;
    }


    public static int getValueFromSpaceSeparatedGalacticRomanNumerals(String galacticRomanText){
        StringBuilder romanStringBuilder=new StringBuilder();
        for (String romanNumeric : galacticRomanText.split(" ")){
            romanStringBuilder.append(TranslationDictionary.getTranslation(romanNumeric));
        }
        return new RomanString(romanStringBuilder.toString()).getValue();
    }
}
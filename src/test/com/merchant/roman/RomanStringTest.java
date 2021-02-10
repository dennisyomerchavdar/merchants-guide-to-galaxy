package com.merchant.roman;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RomanStringTest {

    Map<String , Integer> basicRomanStringValuePairs = new HashMap<>();
    Map<String , Integer> doubleRomanStringValuePairs = new HashMap<>();
    Map<String , Integer> complexRomanStringValuePairs = new HashMap<>();

    public RomanStringTest(){
        basicRomanStringValuePairs.put( "I" , 1);
        basicRomanStringValuePairs.put( "V" , 5);
        basicRomanStringValuePairs.put( "X" , 10);
        basicRomanStringValuePairs.put( "L" , 50);
        basicRomanStringValuePairs.put( "C" , 100);
        basicRomanStringValuePairs.put( "D" , 500);
        basicRomanStringValuePairs.put( "M" , 1000);

        doubleRomanStringValuePairs.put( "IV" , 4);
        doubleRomanStringValuePairs.put( "IX" , 9);
        doubleRomanStringValuePairs.put( "MX" , 1010);
        doubleRomanStringValuePairs.put( "MI" , 1001);
        doubleRomanStringValuePairs.put( "LI" , 51);
        doubleRomanStringValuePairs.put( "MD" , 1500);
        doubleRomanStringValuePairs.put( "CD" , 400);
        doubleRomanStringValuePairs.put( "CL" , 150);
        doubleRomanStringValuePairs.put( "DX" , 510);
        doubleRomanStringValuePairs.put( "DV" , 505);
        doubleRomanStringValuePairs.put( "XC" , 90);


        complexRomanStringValuePairs.put( "MCXXIII" , 1123);
        complexRomanStringValuePairs.put( "CMXCIX" , 999);
        complexRomanStringValuePairs.put( "DCCLXVIII" , 768);
        complexRomanStringValuePairs.put( "CCCXXXIII" , 333);
        complexRomanStringValuePairs.put( "DCXCIX" , 699);
        complexRomanStringValuePairs.put( "DCCCXI" , 811);
        complexRomanStringValuePairs.put( "MCCXIX" , 1219);
        complexRomanStringValuePairs.put( "DCCCLXXXVIII" , 888);
        complexRomanStringValuePairs.put( "MDCCXIX" , 1719);


    }



    @Test
    public void basicRomanStringValuesAreCorrect(){
        this.RomanStringValuesAreCorrect(basicRomanStringValuePairs);
    }

    @Test
    public void doubleRomanStringValuesAreCorrect(){
        this.RomanStringValuesAreCorrect(doubleRomanStringValuePairs);
    }

    @Test
    public void complexRomanStringValuesAreCorrect(){
        this.RomanStringValuesAreCorrect(complexRomanStringValuePairs);
    }

    private void RomanStringValuesAreCorrect(Map<String , Integer> romanStringValuePairs )
    {
        for (String romanString :romanStringValuePairs.keySet()){
            RomanString parsedString = new RomanString(romanString);
            assertEquals( Integer.valueOf(parsedString.getValue()),  Integer.valueOf(romanStringValuePairs.get(romanString)));
        }

    }





    @Test(expected = RomanStringException.class)
    public void V_L_D_CannotRepeatMoreThanOnce(){
        RomanString parsedString = new RomanString("CLL"); // should probably be "CC"
    }

    @Test(expected = RomanStringException.class)
    public void sameNumeralCannotRepeatMoreThanThrice(){
        RomanString parsedString = new RomanString("XXXX"); // could probably be "XL"
    }

    @Test(expected = RomanStringException.class)
    public void falseNumeralOrderGivesError(){
        RomanString parsedString = new RomanString("IXCM"); // could probably be "CMIX"
    }

    @Test(expected = RomanStringException.class)
    public void wrongPrecedingBeforeNumberStartingWithFiveGivesError(){
        RomanString parsedString = new RomanString("IM");
    }

    @Test(expected = RomanStringException.class)
    public void wrongPrecedingBeforeNumberStartingWithOneGivesError(){
        RomanString parsedString = new RomanString("IL");
    }

    @Test(expected = RomanStringException.class)
    public void doubleNegationGivesError(){
        RomanString parsedString = new RomanString("IXC");
    }
}

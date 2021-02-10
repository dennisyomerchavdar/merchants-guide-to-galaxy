package com.merchant.roman;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RomanStringValidator {
    // characters that cannot precede bigger values and also cannot repeat more than once
    public static Set<Integer> RomanCharacterValuesStartWithFive =new HashSet<Integer>(Arrays.asList( 5, 50 ,500));
    // characters that cannot repeat more than thrice, these characters can be negatives (substracted preceding characters)
    public static Set<Integer> RomanCharacterValuesStartWithOne =new HashSet<Integer>(Arrays.asList( 1, 10, 100, 1000));

    public static void validate(String romanString, List<Integer> romanValueList){
        if (!valuesThatStartWithFiveDoesRepeatMoreThanOnce(romanValueList)){
            throw new RomanStringException("Roman numbers that their values start with 5 cannot repeat more than once, given: "+romanString);
        }
        if (!noValueRepeatsMoreThanThrice(romanValueList)){
            throw new RomanStringException("A roman character cannot repeat more than three times, given: "+romanString);
        }
        if (!positivesOrderedBigToSmall(romanValueList)){
            throw new RomanStringException("Wrong order, numbers should be written in decreasing order except negations, like CLXVI, given: "+romanString);
        }
        if (!checkPrecedingRules(romanValueList)){
            throw new RomanStringException("Wrong Negation, some values cannot precede others, given: "+romanString);
        }
        if (!noDoubleNegation(romanValueList)){
            throw new RomanStringException("Negation cannot happen twice in a row, given: "+romanString);
        }
    }


    private static boolean valuesThatStartWithFiveDoesRepeatMoreThanOnce(List<Integer> romanValueList){
        int prevnumber = Integer.MAX_VALUE;
        for (int index = 0 ; index< romanValueList.size(); index++){
            int currentElementVal = romanValueList.get(index);
            if (currentElementVal ==prevnumber && RomanCharacterValuesStartWithFive.contains( currentElementVal)){
                return false;
            }
            else{
                prevnumber = currentElementVal;
            }
        }
        return true;
    }

    private static boolean noValueRepeatsMoreThanThrice(List<Integer> romanValueList){
        int prevBeforePrevBeforePrevNumber = Integer.MAX_VALUE;
        int prevBeforePrevNumber = Integer.MAX_VALUE;
        int prevnumber = Integer.MAX_VALUE;
        for (int index = 0 ; index< romanValueList.size(); index++){
            int currentElementVal = romanValueList.get(index);
            if (prevBeforePrevNumber== prevBeforePrevBeforePrevNumber &&  prevnumber == prevBeforePrevNumber && currentElementVal ==prevnumber){
                return false;
            }
            else{
                prevBeforePrevBeforePrevNumber = prevBeforePrevNumber;
                prevBeforePrevNumber = prevnumber;
                prevnumber = currentElementVal;
            }
        }
        return true;
    }

    // Non negated values should be ordered big to small, like "DLXVI"
    private static boolean positivesOrderedBigToSmall(List<Integer> romanValueList){
        int lastPositive = Integer.MAX_VALUE;
        for (int index = 0 ; index< romanValueList.size(); index++){
            int currentElementVal = romanValueList.get(index);

            if (index+1 != romanValueList.size()){
                int nextElementVal = romanValueList.get(index+1);
                if (currentElementVal < nextElementVal){
                    continue;
                }

                else{
                    if (currentElementVal > lastPositive){
                        return false;
                    }
                    else{
                        lastPositive = currentElementVal;
                    }
                }
            }


        }
        return true;
    }

    // An I cannot be substracted from a C,
    // for example, it is not correct to write IC for 99,
    // it is also not correct to write 49 with IL.
    // this check verifies such constraints.
    // Rules are:
    //          1 : Only one in tenth of a {number that start with one} can precede that number for negation.
    //          2 : Only one in fifth of a {number that start with five} can precede that number for negation.
    //          3 : Only {numbers that start with one} can precede for negation
    private static boolean checkPrecedingRules(List<Integer> romanValueList){
        for (int index = 0 ; index< romanValueList.size(); index++){
            int currentElementVal = romanValueList.get(index);

            if (index+1 != romanValueList.size()){
                int nextElementVal = romanValueList.get(index+1);
                if (currentElementVal < nextElementVal){
                    if (!RomanCharacterValuesStartWithOne.contains(currentElementVal)){
                        return false;
                    }
                    if (RomanCharacterValuesStartWithOne.contains(nextElementVal)){
                        if (currentElementVal * 10 != nextElementVal){
                            return false;
                        }
                    }

                    else{
                        if (currentElementVal * 5 != nextElementVal){
                            return false;
                        }

                    }

                }
            }


        }
        return true;
    }

    //negation is , when a smaller numeric precedes bigger one, thus being negated and subtracted. This cannot happen twice in a row, so this has to be validated.
    private static boolean noDoubleNegation(List<Integer> romanValueList){
        boolean firstOccurance = false; // this flag is used for tracking the first negation of double negation.
        for (int index = 0 ; index< romanValueList.size(); index++){
            int currentElementVal = romanValueList.get(index);

            if (index+1 != romanValueList.size()){
                int nextElementVal = romanValueList.get(index+1);
                if (currentElementVal < nextElementVal){
                    if (!firstOccurance){
                        firstOccurance = true;
                    }
                    else{
                        return false;
                    }

                }
                else{
                    firstOccurance = false;
                }
            }



        }
        return true;
    }

}

package org.service.util;

public class StringUtility {

    public static int tryParse(String input,int defaultValue){
        int result;
        try {
            result = Integer.parseInt(input);
        }catch (NumberFormatException nfe){
            return defaultValue;
        }
        return result;
    }
}

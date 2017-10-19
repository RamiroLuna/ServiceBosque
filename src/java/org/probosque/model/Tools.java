package org.probosque.model;

/**
 *
 * @author admin
 */
public class Tools {

    public static String completeString(String value, int length) {
        if (value.length() < length) {
            String zeros = "";
            for (int i = 0; i < length - value.length(); i++) {
                zeros += "0";
            }
            return zeros + value;
        }
        else{
            return value;
        }
    }
}
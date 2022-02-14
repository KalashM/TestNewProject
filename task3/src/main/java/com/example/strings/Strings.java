package com.example.strings;

public class Strings {

    public static String stringReverseUsingBuildInMethod(String s) {

        StringBuilder input = new StringBuilder();
        input.append(s);
        return input.reverse().toString();

    }

    public static String stringReverse(String s) {
        if (s == null) {
            throw new NullPointerException("The input string is empty");
        } else {
            char[] temp = s.toCharArray();
            int first, last;
            last = temp.length - 1;

            for (first = 0; first < last; first++, last--) {
                char tempChar = temp[first];
                temp[first] = temp[last];
                temp[last] = tempChar;
            }
            String newString  = String.valueOf(temp);
            return newString;
        }
    }

}

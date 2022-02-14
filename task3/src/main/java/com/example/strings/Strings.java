package com.example.strings;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

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

    public static char[] findCommonSymbols(String[] s) {
        int len;
        int min = s[0].length();
        int indexOfShortestElement = 0;
        for (int i = 1; i < s.length; i++) {
            if (s[i].length() < min) {
                min = s[i].length();
                indexOfShortestElement = i;
            }
        }

        String shortestString = s[indexOfShortestElement];

        char[] chars = shortestString.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder resultString = new StringBuilder();

        for (Character character : charSet) {
            int count = 0;
            for (String s1: s) {
                if (s1.contains(Character.toString(character))) {
                    count++;
                }
            }
            if (count == s.length) {
                resultString.append(character);
            }
        }

        return resultString.toString().toCharArray();

    }

}

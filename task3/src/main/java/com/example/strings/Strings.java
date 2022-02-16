package com.example.strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
/**Class <strong>Strings</strong> implements methods to work with Strings.
 * @author - Marina Panchenko
 * @version - 1.0
 */
public class Strings {

    /**
     * Method reverse a string using StringBuilder class.
     * @param s the input string that should be reversed.
     * @return a reversed string.
     */
    public static String stringReverseUsingBuildInMethod(String s) {

        StringBuilder input = new StringBuilder();
        input.append(s);
        return input.reverse().toString();

    }

    /**
     * Method reverse a string using own implementation.
     * @param s the input string that should be reversed.
     * @return a reversed string.
     */
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

    /**
     * Method finds common characters in a given strings.
     * @param s the input array of strings.
     * @return an array of unique sorted common characters presented in all given strings.
     */
    public static char[] findCommonSymbols(String[] s) {

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
        Arrays.sort(chars);
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

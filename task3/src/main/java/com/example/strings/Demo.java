package com.example.strings;

import static com.example.strings.Strings.stringReverse;

public class Demo {

    public static void main(String[] args) {
        String s = "Marina Kalashnyk";
        String newString1 = stringReverse(s);
        String newString2 = Strings.stringReverseUsingBuildInMethod(s);

        System.out.println("Old string is " + s);
        System.out.println("New string1 is " + newString1);
        System.out.println("New string2 is " + newString2);

        String s2 = stringReverse(null);
    }
}

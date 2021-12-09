package com.example.arrays;

public class Demo {

    public static void main(String[] args) {
        int[] arrayCopyFrom = new int[20];
        int[] arrayCopyTo = new int[20];
        arrayCopyFrom[0] = 0;
        for (int i = 1; i < arrayCopyFrom.length; i++) {
            arrayCopyFrom[i] = arrayCopyFrom[i-1] + 5;
        }
        arrayCopyTo[0] = 3;
        for (int i = 1; i < arrayCopyTo.length; i++) {
            arrayCopyTo[i] = arrayCopyTo[i-1] + 3;
        }

        ArrayBlockCopy.arrayBlockCopy(arrayCopyFrom, arrayCopyTo, 6, 10);

        ArrayBlockCopy.arrayBlockCopyIntoBeginning(arrayCopyTo, arrayCopyFrom, 18,19);

        for (int i = 0; i < arrayCopyTo.length; i++) {
            System.out.println(arrayCopyTo[i]);
        }

        for (int i = 0; i < arrayCopyFrom.length; i++) {
            System.out.println(arrayCopyFrom[i]);
        }
    }
}

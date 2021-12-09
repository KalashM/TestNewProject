package com.example.arrays;

public class ArrayBlockCopy {

    public static void arrayBlockCopy(int[] arrayFrom, int[] arrayTo, int indexFrom, int indexTo) {

        if ((indexFrom < arrayFrom.length) && (indexTo < arrayFrom.length)) {
            for (int i = indexFrom; i <= indexTo; i++) {
                int value = arrayFrom[i];
                arrayTo[i] = value;
            }
        }
    }

    public static void arrayBlockCopyIntoBeginning(int[] arrayFrom, int[] arrayTo, int indexFrom, int indexTo) {

        int j = 0;
        if ((indexFrom < arrayFrom.length) && (indexTo < arrayFrom.length)) {
            for (int i = indexFrom; i <= indexTo; i++) {
                int value = arrayFrom[i];
                arrayTo[j] = value;
                j++;
            }
        }
    }
}

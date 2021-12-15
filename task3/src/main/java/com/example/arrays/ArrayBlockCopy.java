package com.example.arrays;

public class ArrayBlockCopy {

    public static int[] arrayBlockCopy(int[] arrayFrom, int from, int to) {

        if ((from < 0) || (from > arrayFrom.length)) {
            throw new ArrayIndexOutOfBoundsException("Specify the correct from index");
        } else if (from > to) {
            throw new IllegalArgumentException("To index should be greater than From index");
        } else {
            int[] arrayTo = new int[to - from];
            int j = 0;
            for (int i = from; i < to; i++) {
                int value = arrayFrom[i];
                arrayTo[j] = value;
                j++;
            }
            return arrayTo;
        }
    }

    public static void arrayBlockCopy(int[] arrayFrom, int[] arrayTo, int from, int to) {

        if ((from < 0) || (from > arrayFrom.length)) {
            throw new ArrayIndexOutOfBoundsException("Specify the correct from index");
        } else if ((from > to) || ((arrayTo.length < (to - from)))) {
            throw new IllegalArgumentException("To index should be greater than From index OR arrayTo length is less than index range");
        } else {
            int j = 0;
            for (int i = from; i < to; i++) {
                int value = arrayFrom[i];
                arrayTo[j] = value;
                j++;
            }
        }
    }

    public static void arrayBlockCopy(int[] arrayFrom, int[] arrayTo, int from, int to, int inFrom) {

        if ((from < 0) || (from > arrayFrom.length))  {
            throw new ArrayIndexOutOfBoundsException("Specify the correct from index");
        } else if ((inFrom < 0) || (inFrom > arrayTo.length)) {
            throw new ArrayIndexOutOfBoundsException("Specify the correct inFrom index");
        } else if ((from > to) || ((arrayTo.length < (to - from)))) {
            throw new IllegalArgumentException("To index should be greater than From index OR arrayTo length is less than index range");
        } else {
            int j = inFrom;
            for (int i = from; i < to; i++) {
                int value = arrayFrom[i];
                arrayTo[j] = value;
                j++;
            }
        }
    }
}

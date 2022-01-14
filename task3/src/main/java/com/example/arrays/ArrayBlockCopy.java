package com.example.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**Class <strong>ArrayBlockCopy</strong> implements methods for copy a block from an Array.
 * @author - Marina Panchenko
 * @version - 1.0
 */
public class ArrayBlockCopy {
    /**
     * Method copies a block from a given array to a new array.
     * The initial index of the block (from) must lie between zero and arrayFrom.length, inclusive.
     * The final index of the range (to), which must be greater than or equal to from, may be greater than arrayFrom.length.
     * @param arrayFrom the array from which a block is to be copied
     * @param from the initial index of the block to be copied, inclusive
     * @param to the final index of the block to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing a specified block of the values from the arrayFrom array.
     * @throws ArrayIndexOutOfBoundsException if from &lt; 0 or from &gt; arrayFrom.length
     * @throws IllegalArgumentException if from &gt; to
     */
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
    /**
     * Method copies a block from a given array to a new array using copyOfRange method from java.util.Arrays.
     * The initial index of the block (from) must lie between zero and arrayFrom.length, inclusive.
     * The final index of the range (to), which must be greater than or equal to from, may be greater than arrayFrom.length.
     * @param arrayFrom the array from which a block is to be copied
     * @param from the initial index of the block to be copied, inclusive
     * @param to the final index of the block to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing a specified block of the values from the arrayFrom array.
     * @throws ArrayIndexOutOfBoundsException if from &lt; 0 or from &gt; arrayFrom.length
     * @throws IllegalArgumentException if from &gt; to
     */

    public static int[] arrayBlockCopyUsingArray(int[] arrayFrom, int from, int to) {

        if ((from < 0) || (from > arrayFrom.length)) {
            throw new ArrayIndexOutOfBoundsException("Specify the correct from index");
        } else if (from > to)  {
            throw new IllegalArgumentException("To index should be greater than From index");
        } else {
            int[] arrayTo = Arrays.copyOfRange(arrayFrom, from,to);
            return arrayTo;
        }
    }
    /**
     * Method copies a block from a given array to a new array using subList method from java.util.List.
     * The initial index of the block (from) must lie between zero and arrayFrom.length, inclusive.
     * The final index of the range (to), which must be greater than or equal to from, may be greater than arrayFrom.length.
     * @param arrayFrom the array from which a block is to be copied
     * @param from the initial index of the block to be copied, inclusive
     * @param to the final index of the block to be copied, exclusive. (This index may lie outside the array.)
     * @return a new array containing a specified block of the values from the arrayFrom array.
     * @throws ArrayIndexOutOfBoundsException if from &lt; 0 or from &gt; arrayFrom.length
     * @throws IllegalArgumentException if from &gt; to
     */

    public static int[] arrayBlockCopyUsingList(int[] arrayFrom, int from, int to) {

        if ((from < 0) || (from > arrayFrom.length))  {
            throw new ArrayIndexOutOfBoundsException("Specify the correct from index");
        } else if (from > to)  {
            throw new IllegalArgumentException("To index should be greater than From index");
        } else {
            List<Integer> listArrayTo = Arrays.stream(arrayFrom).boxed().collect(Collectors.toList()).subList(from, to);
            int[] arrayTo = new int[listArrayTo.size()];
            for (int i = 0; i < listArrayTo.size(); i++) {
                arrayTo[i] = listArrayTo.get(i);
            }
            return arrayTo;
        }
    }
}

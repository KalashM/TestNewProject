package com.example.collections;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        List<Integer[]> testInputs = new ArrayList<>();

        testInputs.add(new Integer[]{1, 2, 3, 3, 4, 4});
        testInputs.add(new Integer[]{1, 1, 5, 8, 5, 4});
        testInputs.add(new Integer[]{1, 0, 0, 1});
        for (Integer[] integers: testInputs) {
            System.out.println(Arrays.asList(integers).toString());
        }

        for (Integer[] integers: Collections.removeDuplicates(testInputs)) {
            System.out.println(Arrays.asList(integers).toString());
        }
    }
}

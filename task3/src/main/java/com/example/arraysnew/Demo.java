package com.example.arraysnew;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {

    private static Logger LOGGER = LoggerFactory.getLogger(com.example.arrays.Demo.class);

    public static void main(String[] args) {

        int[] initial = new int[20];

        initial[0] = 0;
        for (int i = 1; i < initial.length; i++) {
            initial[i] = initial[i - 1] + 5;
        }

        int[] arrayTo = new int[15];
        int j = 0;
        for (int i = 5; i < 20; i++) {
            int value = initial[i];
            arrayTo[j] = value;
            j++;
        }

        int[] arrayToCopied = Arrays.copyOfRange(initial, 5,20);

        List<Integer> listArrayTo = Arrays.stream(initial).boxed().collect(Collectors.toList()).subList(5, initial.length);

        LOGGER.info("ArrayTo values:");
        for (int i = 0; i < arrayTo.length; i++) {
            LOGGER.info(String.valueOf(arrayTo[i]));
        }

        LOGGER.info("ArrayToCopied values:");
        for (int i = 0; i < arrayToCopied.length; i++) {
            LOGGER.info(String.valueOf(arrayToCopied[i]));
        }
        LOGGER.info("listArrayTo values:");

        listArrayTo.forEach(s -> LOGGER.info(String.valueOf(s)));
    }
}

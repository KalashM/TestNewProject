package com.example.arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo {

    private static Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {

        int[] arrayCopyFrom = new int[20];
        int[] arrayCopyTo = new int[20];
        arrayCopyFrom[0] = 0;
        for (int i = 1; i < arrayCopyFrom.length; i++) {
            arrayCopyFrom[i] = arrayCopyFrom[i - 1] + 5;
        }
        arrayCopyTo[0] = 3;
        for (int i = 1; i < arrayCopyTo.length; i++) {
            arrayCopyTo[i] = arrayCopyTo[i - 1] + 3;
        }

        LOGGER.debug("ArrayTo initial values:");
        for (int i = 0; i < arrayCopyTo.length; i++) {
            LOGGER.debug(String.valueOf(arrayCopyTo[i]));
        }
        LOGGER.debug("ArrayFrom initial values:");
        for (int i = 0; i < arrayCopyFrom.length; i++) {
            LOGGER.debug(String.valueOf(arrayCopyFrom[i]));
        }
        LOGGER.info("Copying values range from arrayFrom to arrayTo into the beginning started.");
        try {
            ArrayBlockCopy.arrayBlockCopy(arrayCopyFrom, arrayCopyTo, 6, 10);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("Copying values range from arrayTo to arrayFrom into the specified position started.");
        try {
            ArrayBlockCopy.arrayBlockCopy(arrayCopyTo, arrayCopyFrom, 18,19, 5);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("Copying values range from arrayFrom into a new array started.");
        int[] arrayNew;
        try {
            arrayNew = ArrayBlockCopy.arrayBlockCopy(arrayCopyFrom, 5,10);
            LOGGER.info("New array values:");
            for (int i = 0; i < arrayNew.length; i++) {
                LOGGER.info(String.valueOf(arrayNew[i]));
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("ArrayFrom values after modifications:");
        for (int i = 0; i < arrayCopyFrom.length; i++) {
            LOGGER.info(String.valueOf(arrayCopyFrom[i]));
        }

        LOGGER.info("ArrayTo values after modifications:");
        for (int i = 0; i < arrayCopyTo.length; i++) {
            LOGGER.info(String.valueOf(arrayCopyTo[i]));
        }
    }
}

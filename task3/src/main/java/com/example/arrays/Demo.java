package com.example.arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo {

    private static Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) {

        int[] arrayCopyFrom = new int[20];
        arrayCopyFrom[0] = 0;
        for (int i = 1; i < arrayCopyFrom.length; i++) {
            arrayCopyFrom[i] = arrayCopyFrom[i - 1] + 5;
        }

        LOGGER.debug("ArrayFrom initial values:");
        for (int i = 0; i < arrayCopyFrom.length; i++) {
            LOGGER.debug(String.valueOf(arrayCopyFrom[i]));
        }
        LOGGER.info("Copying values range from arrayFrom into a new array using own implementation.");
        int[] arrayNew1;
        try {
            arrayNew1 = ArrayBlockCopy.arrayBlockCopy(arrayCopyFrom, 6, 10);
            LOGGER.info("New array values:");
            for (int i = 0; i < arrayNew1.length; i++) {
                LOGGER.info(String.valueOf(arrayNew1[i]));
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("Copying values range from arrayFrom into a new array using Array methods.");
        int[] arrayNew2;
        try {
            arrayNew2 = ArrayBlockCopy.arrayBlockCopyUsingArray(arrayCopyFrom, 5,10);
            LOGGER.info("New array values:");
            for (int i = 0; i < arrayNew2.length; i++) {
                LOGGER.info(String.valueOf(arrayNew2[i]));
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("Copying values range from arrayFrom into a new array using List methods.");
        int[] arrayNew3;
        try {
            arrayNew3 = ArrayBlockCopy.arrayBlockCopyUsingList(arrayCopyFrom, 5,10);
            LOGGER.info("New array values:");
            for (int i = 0; i < arrayNew3.length; i++) {
                LOGGER.info(String.valueOf(arrayNew3[i]));
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
    }
}

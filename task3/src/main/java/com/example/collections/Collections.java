package com.example.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Collections {

    private static Logger LOGGER = LoggerFactory.getLogger(Collections.class);
    /**
     * Method removes duplicates from the list of arrays of Integers.
     * @param i the list of Integer arrays
     * @return a new List containing all Integer Arrays without duplicates.
     */

    public static List<Integer[]> removeDuplicates(List<Integer[]> i) {
        List<Integer[]> listWithoutDuplicates = new ArrayList<Integer[]>();

        LOGGER.debug("---------- Input list: ------------");
        for (Integer[] integers: i) {
            LOGGER.debug(Arrays.asList(integers).toString());
        }
        for (Integer[] integers : i) {
            Integer[] integerArray = Arrays.stream(Arrays.stream(integers).distinct().sorted().toArray())
                    .map(o -> (Integer) o)
                    .toArray(Integer[]::new);
            listWithoutDuplicates.add(integerArray);
        }
        LOGGER.debug("---------- Input list without duplicates: ------------");
        for (Integer[] integers: listWithoutDuplicates) {
            LOGGER.debug(Arrays.asList(integers).toString());
        }
        return listWithoutDuplicates;
    }
}

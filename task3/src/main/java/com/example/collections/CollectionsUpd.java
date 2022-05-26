package com.example.collections;

import com.example.arrays.ArrayBlockCopy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Pattern;

public class CollectionsUpd {

    private static Logger LOGGER = LoggerFactory.getLogger(CollectionsUpd.class);
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

    public static Integer[] arraysUnion(List<Integer[]> i) {

        Set<Integer> resultSet = new HashSet<Integer>();

        for (Integer[] integers : i) {
            Collections.addAll(resultSet, integers);
        }

        Integer[] resultArray = Arrays.stream(resultSet.toArray()).map(o -> (Integer) o).toArray(Integer[]::new);

        return resultArray;
    }

    public static Integer[] arraysIntersection(List<Integer[]> listOfIntegerArrays) {

        Integer[] firstArray = listOfIntegerArrays.get(0);
        Set<Integer> intersectionSet = new HashSet<>(Arrays.asList(firstArray));

        for (int i = 1; i < listOfIntegerArrays.size(); i++) {

            Set<Integer> set = new HashSet<>(Arrays.asList(listOfIntegerArrays.get(i)));

            intersectionSet.retainAll(set);

        }
        Integer[] resultArray = Arrays.stream(intersectionSet.toArray()).map(o -> (Integer) o).toArray(Integer[]::new);

        return resultArray;
    }

    public static Integer[] arraysDifference(Integer[] arrayA, Integer[] arrayB) {

        Set<Integer> setA = new HashSet<Integer>(Arrays.asList(arrayA));
        Set<Integer> setB = new HashSet<Integer>(Arrays.asList(arrayB));
        setA.removeAll(setB);

        //return Arrays.stream(setA.toArray()).map(o -> (Integer) o).toArray(Integer[]::new);
        return setA.toArray(new Integer[0]);
    }

    public static Map<Character, Long> frequencyDictionary(String s) {

        LOGGER.info("----------- Input Sting: -------------");
        LOGGER.info(s);

        Set<Character> charSet = new TreeSet<Character>();
        Character[] charArray = s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i].toString().matches("[A-Za-z0-9]+")) {
                charSet.add(charArray[i]);
            }
        }
        LOGGER.debug("---------------------------------------------------------");
        LOGGER.debug("List of latin characters or digits from the input string:");
        LOGGER.debug(charSet.toString());
        LOGGER.debug("---------------------------------------------------------");

        Map<Character, Long> resultMap = new TreeMap<Character, Long>();

        for (Character c: charSet) {
            Long charCount = s.chars().filter(ch -> ch == c).count();
            resultMap.put(c, charCount);
        }

        LOGGER.info("---------------------------------------------------------");
        LOGGER.info("Result of frequency vocabulary:");
        LOGGER.info(resultMap.toString());

        return resultMap;
    }
}

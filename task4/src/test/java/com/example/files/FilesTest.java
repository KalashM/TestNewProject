package com.example.files;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FilesTest {

    @ParameterizedTest
    @MethodSource("filesByPattern")
    void findFilesByPatternTest(String[] actual, String[] expected) {
        File[] actualFiles = Files.findFilesByPattern(actual[0], actual[1]);
        List<String> actualFileNames = new ArrayList<String>();
        for (int i = 0; i < actualFiles.length; i++) {
            actualFileNames.add(actualFiles[i].getName());
        }
        assertArrayEquals(expected, actualFileNames.toArray());
    }

    public static Stream<Arguments> filesByPattern() {
        return Stream.of(
                Arguments.of(new String[]{"D:\\", "(.*)fo(.*)"}, new String[]{"foto.7z"}),
                Arguments.of(new String[]{"D:\\Java\\testFolder", "(.*).xml"}, new String[]{"test2.xml", "test3.xml"})
        );
    }
}
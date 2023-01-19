package com.example.files;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyFilesTest {

    @ParameterizedTest
    @MethodSource("filesByPattern")
    void findFilesByPatternTest(String actualFile, String pattern, String[] expected) throws IOException {
        if (actualFile.equals("src\\test\\resources")) {
            createFiles();
        }
        Path dir = Paths.get(actualFile).toAbsolutePath();
        File[] actualFiles = MyFiles.findFilesByPattern(dir, pattern);
        List<String> actualFileNames = new ArrayList<String>();
        for (int i = 0; i < actualFiles.length; i++) {
            actualFileNames.add(actualFiles[i].getName());
        }
        assertArrayEquals(expected, actualFileNames.toArray());
    }

    public static Stream<Arguments> filesByPattern() {
        return Stream.of(
                Arguments.of("src\\test\\java\\com\\example\\files", "(.*)My(.*)", new String[]{"MyFilesTest.java"}),
                Arguments.of("src\\test\\resources", "(.*).txt", new String[]{"test1.txt", "test2.txt", "test3.txt"})
        );
    }

    public static void createFiles() throws IOException {

        String[] fileNames = new String[]{"test1.txt", "test2.txt", "test3.txt"};

        for (String fileName: fileNames) {
            Path filePath = Paths.get("src\\test\\resources" + "\\" + fileName).toAbsolutePath();
            if (!Files.exists(filePath)) {
                System.out.println(filePath);
                Files.createFile(filePath);
            }
        }
    }
}
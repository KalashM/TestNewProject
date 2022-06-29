package com.example.files;

import java.io.File;

public class Demo {
    public static void main(String[] args) {

        File[] matchedFiles = Files.findFilesByPattern("D:\\", "(.*)fo(.*)");
        for (File matchedFile : matchedFiles) {
            System.out.println(matchedFile.getName());
        }
    }
}

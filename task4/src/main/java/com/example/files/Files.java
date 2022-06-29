package com.example.files;

import java.io.File;
import java.io.FileFilter;

public class Files {

    public static File[] findFilesByPattern(String directory, String pattern) {
        File dir = new File(directory);
        FileFilter fileFilter = file -> !file.isDirectory() && file.getName().matches(pattern);
        File[] list = dir.listFiles(fileFilter);
        return list;
    }
}

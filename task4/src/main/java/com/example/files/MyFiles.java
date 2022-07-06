package com.example.files;

import java.io.*;
/*import java.io.FileFilter;
import java.io.InputStream;
import java.io.OutputStream;*/

public class MyFiles {

    public static File[] findFilesByPattern(String directory, String pattern) {
        File dir = new File(directory);
        FileFilter fileFilter = file -> !file.isDirectory() && file.getName().matches(pattern);
        File[] list = dir.listFiles(fileFilter);
        return list;
    }

    public static void copyFileUsingStream(File original, File copied) throws IOException {
        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            inStream = new FileInputStream(original);
            outStream = new FileOutputStream(copied);
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, lengthRead);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            inStream.close();
            outStream.close();
        }
    }

    public static void copyFileUsinfBufferedStream(File original, File copied) throws IOException {
        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            inStream = new BufferedInputStream(new FileInputStream(original));
            outStream = new BufferedOutputStream(new FileOutputStream(copied));
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, lengthRead);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {
            inStream.close();
            outStream.close();
        }
    }
}

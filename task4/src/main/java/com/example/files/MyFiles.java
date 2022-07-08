package com.example.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MyFiles {
    private static Logger LOGGER = LoggerFactory.getLogger(MyFiles.class);

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
            LOGGER.error(e.getMessage());
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
            LOGGER.error(e.getMessage());
        } finally {
            inStream.close();
            outStream.close();
        }
    }

    public static void copyFileUsingNio(File original, File copied) throws IOException {
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            inChannel = new FileInputStream(original).getChannel();
            outChannel = new FileOutputStream(copied).getChannel();
            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            inChannel.close();
            outChannel.close();
        }
    }

    public static void copyFileUsingJavaFiles(File original, File copied) throws IOException {
        Files.copy(original.toPath(), copied.toPath());
    }

    public static void copyFileFromURLUsingJavaFiles(String originalURL, String copiedPath) throws IOException {
        InputStream in = new URL(originalURL).openStream();
        Files.copy(in, Paths.get(copiedPath), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFileFromURLUsingBufferedReader(String originalURL, String copiedPath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(originalURL).openStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(copiedPath));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }

    public static void copyFileUsingBufferedStreamFromURL(String originalURL, String copiedPath) throws IOException {
        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            inStream = new BufferedInputStream(new URL(originalURL).openStream());
            outStream = new BufferedOutputStream(new FileOutputStream(copiedPath));
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, lengthRead);
            }
        } catch (IOException e) {
            // LOGGER.error(e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            inStream.close();
            outStream.close();
        }
    }
}

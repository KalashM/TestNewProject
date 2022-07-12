package com.example.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class MyFiles {
    private static Logger LOGGER = LoggerFactory.getLogger(MyFiles.class);

    private static String getFileNameFromURL(String urlFrom) throws MalformedURLException {
        URL url = new URL(urlFrom);
        return Paths.get(url.getPath()).getFileName().toString();
    }

    public static File[] findFilesByPattern(String directory, String pattern) {
        File dir = new File(directory);
        FileFilter fileFilter = file -> !file.isDirectory() && file.getName().matches(pattern);
        File[] list = dir.listFiles(fileFilter);
        return list;
    }

    public static void copyFileUsingStream(File original, File copied) throws IOException {

        try (
            InputStream inStream = new FileInputStream(original);
            OutputStream outStream = new FileOutputStream(copied)
        ) {
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, lengthRead);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void copyFileUsingBufferedStream(File original, File copied) throws IOException {
        try (
                InputStream inStream = new BufferedInputStream(new FileInputStream(original));
                OutputStream outStream = new FileOutputStream(copied)
        ) {
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = inStream.read(buffer, 0, buffer.length)) != -1) {
                outStream.write(buffer, 0, lengthRead);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static void copyFileUsingNioChannel(File original, File copied) throws IOException {
        try (
                FileChannel inputChannel = new FileInputStream(original).getChannel();
                FileChannel outputChannel = new FileOutputStream(copied).getChannel();
        ) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }
        }
    }

    public static void copyFileUsingJavaFiles(File original, File copied) throws IOException {
        Files.copy(original.toPath(), copied.toPath());
    }

    public static void copyFileFromURLUsingJavaFiles(String originalURL, String copiedDir) throws IOException {
        InputStream in = new URL(originalURL).openStream();
        String copiedPath = copiedDir + "\\" + getFileNameFromURL(originalURL);
        Files.copy(in, Paths.get(copiedPath), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFileFromURLUsingBufferedReader(String originalURL, String copiedDir) throws IOException {
        String copiedPath = copiedDir + "\\" + getFileNameFromURL(originalURL);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(originalURL).openStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(copiedPath));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }

    public static void copyFileFromURLUsingBufferedStream(String originalURL, String copiedDir) throws IOException {
        String copiedPath = copiedDir + "\\" + getFileNameFromURL(originalURL) + "1";

        try (
                InputStream inStream = new BufferedInputStream(new URL(originalURL).openStream());
                OutputStream outStream = new BufferedOutputStream(new FileOutputStream(copiedPath))
            ) {
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, lengthRead);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}

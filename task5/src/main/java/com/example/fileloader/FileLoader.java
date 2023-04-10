package com.example.fileloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class FileLoader implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(FileLoader.class);

    private static String location = "D:\\Java\\Task5Downloads\\";
    private String url;

    public FileLoader(String url) {
        this.url = url;
    }

    public static void downloadFromURL(String url) {

        String filename = null;
        try {
            filename = Paths.get(new URL(url).getPath()).getFileName().toString();
        } catch (MalformedURLException e) {
            //e.printStackTrace();
            LOGGER.error(e.getMessage());
        }

        String localFilename = location + filename;

        int i = 1;

        while (new File(localFilename).exists()) {
            try {
                filename = "Copy " + "(" + i + ") " + Paths.get(new URL(url).getPath()).getFileName().toString();
            } catch (MalformedURLException e) {
                //e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
            localFilename = location + filename;
            i++;
        }
        if (isBrokenUrl(url)) {
            //System.out.println(getReasonIfBroken(getUrlResponseCode(url)));
            LOGGER.warn("URL (" + url + ") is broken: " + getReasonIfBroken(getUrlResponseCode(url)));
            return;
        }
        //System.out.println("Downloading is started...");
        LOGGER.info("Downloading is started...");
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get(localFilename));
        } catch (IOException e) {
            //e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        //System.out.println("Downloading of " + filename + " is completed....");
        LOGGER.info("Downloading of " + filename + " is completed....");
    }

    public static int getUrlResponseCode(String url)  {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int code = 0;
        try {
            code = connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.disconnect();
        return code;
    }

    public static boolean isBrokenUrl(String url)  {
        if (getUrlResponseCode(url) == 200 || getUrlResponseCode(url) == 302) {
            return false;
        } else {
            return true;
        }
    }

    public static String getReasonIfBroken(int code) {
        if (code == 404) {
            return "File not found";
        } else if (code == 403) {
            return "Access Forbidden by server";
        } else {
            return "Unknown reason";
        }
    }

    @Override
    public void run() {
        downloadFromURL(url);
    }
}

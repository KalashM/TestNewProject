package com.example.fileloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLoader implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(FileLoader.class);

    private String location;
    private String url;

    public FileLoader(String url, String location) {
        this.url = url;
        this.location = location;
    }

    public void downloadFromURL() {

        String filename = null;
        try {
            filename = Paths.get(new URL(url).getPath()).getFileName().toString();
        } catch (MalformedURLException e) {
            LOGGER.error(e.getMessage());
        }

        String localFilename = location + filename;

        int i = 1;

        while (new File(localFilename).exists()) {
            try {
                filename = "Copy " + "(" + i + ") " + Paths.get(new URL(url).getPath()).getFileName().toString();
            } catch (MalformedURLException e) {
                LOGGER.error(e.getMessage());
            }
            localFilename = location + filename;
            i++;
        }
        if (isBrokenUrl()) {
            LOGGER.warn("URL (" + url + ") is broken: " + getReasonIfBroken(getUrlResponseCode()));
            return;
        }
        LOGGER.info("Downloading is started...");
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get(localFilename));
            LOGGER.info("Downloading of " + filename + " is completed.");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("Downloading of " + filename + " is failed.");
        }
    }

    public int getUrlResponseCode()  {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            LOGGER.warn("Cannot connect to the source URL.");
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

    public boolean isBrokenUrl()  {
        if (getUrlResponseCode() == 200 || getUrlResponseCode() == 302) {
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
        downloadFromURL();
    }
}

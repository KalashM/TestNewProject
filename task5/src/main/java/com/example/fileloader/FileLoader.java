package com.example.fileloader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.*;
import java.util.Properties;
import java.util.Set;

public class FileLoader {

    private static String linksToBeDownloadedPath = Thread.currentThread().getContextClassLoader().getResource("linksToDownload.properties").getPath();
    private static String location;

    public static void main(String[] args) throws IOException {

        System.out.println(args.length);

        if (args.length == 0) {
            location = "D:\\Java\\Task5Downloads\\";
        } else {
            File dir = new File(args[0]);
            if (dir.exists() && dir.isDirectory()) {
                location = args[0];
            }
        }

        Properties links = new Properties();
        links.load(new FileInputStream(linksToBeDownloadedPath));

        final Set<String> keys = links.stringPropertyNames();

        for (String key: keys) {
            try {
                downloadFromURL(links.getProperty(key));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void downloadFromURL(String url) throws IOException {

        String filename = Paths.get(new URL(url).getPath()).getFileName().toString();

        String localFilename = location + filename;

        int i = 1;

        while (new File(localFilename).exists()) {
            filename = "Copy " + "(" + i + ") " + Paths.get(new URL(url).getPath()).getFileName().toString();
            localFilename = location + filename;
            i++;
        }
        if (isBrokenUrl(url)) {
            System.out.println(getReasonIfBroken(getUrlResponseCode(url)));
            return;
        } else {
            System.out.println("Link is not broken");
        }

        System.out.println("Downloading is started...");
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get(localFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Downloading of " + filename + " is completed....");

    }

    public static int getUrlResponseCode(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        int code = connection.getResponseCode();
        connection.disconnect();
        return code;
    }

    public static boolean isBrokenUrl(String url) throws IOException {
        if (getUrlResponseCode(url) == 200) {
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
        } else if (code == 302) {
            return "File temporarily moved";
        } else
            return "Unknown reason";

    }
}

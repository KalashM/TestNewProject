package com.example.fileloader;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileLoader implements Runnable {

    private static URI linksToBeDownloadedPath;

    static {
        try {
            linksToBeDownloadedPath = Thread.currentThread().getContextClassLoader().getResource("linksToDownload.properties").toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static List<String> allLinks;

    static {
        try {
            allLinks = Files.readAllLines(Paths.get(linksToBeDownloadedPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String location = "D:\\Java\\Task5Downloads\\";
    private String url;

    public FileLoader(String url) {
        this.url = url;
    }

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(5);
        for (String link: allLinks) {
            System.out.println(link);
            pool.submit(new FileLoader(link));
        }
        pool.shutdown();
    }

    public static void downloadFromURL(String url) {

        String filename = null;
        try {
            filename = Paths.get(new URL(url).getPath()).getFileName().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String localFilename = location + filename;

        int i = 1;

        while (new File(localFilename).exists()) {
            try {
                filename = "Copy " + "(" + i + ") " + Paths.get(new URL(url).getPath()).getFileName().toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            localFilename = location + filename;
            i++;
        }
        if (isBrokenUrl(url)) {
            System.out.println(getReasonIfBroken(getUrlResponseCode(url)));
            return;
        }
        System.out.println("Downloading is started...");
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get(localFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Downloading of " + filename + " is completed....");

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

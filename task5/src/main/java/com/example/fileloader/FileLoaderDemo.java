package com.example.fileloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileLoaderDemo {

    private static Logger LOGGER = LoggerFactory.getLogger(FileLoaderDemo.class);

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

    public static void main(String[] args) {

        LOGGER.info("Log from {}", FileLoaderDemo.class.getSimpleName());

        int nThreads = 5;
        if (args.length > 0) {
            nThreads = Integer.parseInt(args[0]);
        }

        ExecutorService pool = Executors.newFixedThreadPool(nThreads);

        for (String link: allLinks) {
            pool.submit(new FileLoader(link));
        }
        pool.shutdown();
    }
}

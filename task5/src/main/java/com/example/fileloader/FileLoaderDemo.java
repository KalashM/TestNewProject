package com.example.fileloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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
            LOGGER.error(e.getMessage());
        }
    }

    private static final String LOCATION = "D:\\downloads\\Task5Downloads\\";

    public static void main(String[] args) {

        LOGGER.info("Log from {}", FileLoaderDemo.class.getSimpleName());

        int nThreads = 5;
        if (args.length > 0) {
            nThreads = Integer.parseInt(args[0]);
        }

        List<String> allLinks = null;
        try {
            allLinks = getAllLinks(linksToBeDownloadedPath);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        if (!(new File(LOCATION).exists())) {
            try {
                Files.createDirectory(Paths.get(LOCATION));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ExecutorService pool = Executors.newFixedThreadPool(nThreads);

        for (String link: allLinks) {
            pool.submit(new FileLoader(link, LOCATION));
        }
        pool.shutdown();
    }

    public static List<String> getAllLinks(URI linksToBeDownloadedPath) throws IOException {
        return Files.readAllLines(Paths.get(linksToBeDownloadedPath));
    }
}

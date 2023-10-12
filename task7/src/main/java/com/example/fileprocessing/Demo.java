package com.example.fileprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Demo {
    private static Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    private static URI linkToBeDownloadedPath;

    static {
        try {
            linkToBeDownloadedPath = Thread.currentThread().getContextClassLoader().getResource("xmlFilesToDownload.properties").toURI();
        } catch (URISyntaxException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private static final String LOCATION = System.getProperty("user.dir") + "\\Task7Downloads\\";

    public static void main(String[] args) throws IOException {

        LOGGER.info("Log from {}", Demo.class.getSimpleName());

        String link = Files.readAllLines(Paths.get(linkToBeDownloadedPath)).get(0);

        if (!(new File(LOCATION).exists())) {
            Files.createDirectory(Paths.get(LOCATION));
         }

        new FileLoader(link, LOCATION).run();
    }

}

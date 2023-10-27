package com.example.fileprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class XMLReaderDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(XMLReaderDemo.class);

    private static final String LOCATION = System.getProperty("user.dir") + "\\Task7Downloads\\";
    private static String searchProteinName;

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException {
        LOGGER.info("Log from {}", XMLReaderDemo.class.getSimpleName());

        String fileName = new File(getURLToDownload("xmlFilesToDownload.properties")).getName();

        File fileToParse = new File(LOCATION + "//" + fileName);

        if (!(new File(LOCATION).exists())) {
            Files.createDirectory(Paths.get(LOCATION));
        } else if (!fileToParse.exists()) {
            Files.copy(new URL(getURLToDownload("xmlFilesToDownload.properties")).openStream(), fileToParse.toPath());
        } else {
            LOGGER.info("File " + fileName + " already exists.");
        }

        searchProteinName = getProteinName("xmlFilesToDownload.properties");
        List<ProteinEntry> proteinList;
        long start, end, elapsedTime, convert;
        long memoryAfterMb, memoryBeforeMb;

        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "dom":
                    start = System.nanoTime();

                    memoryBeforeMb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;

                    DomXmlReader domXmlReader = new DomXmlReader(fileToParse, searchProteinName);
                    proteinList = domXmlReader.getProteinEntryIdList();

                    memoryAfterMb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
                    LOGGER.info("Memory increased: " + (memoryAfterMb - memoryBeforeMb) + "Mb");

                    end = System.nanoTime();
                    elapsedTime = end - start;
                    convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
                    LOGGER.info("Time taken to parse file and find requested data using DOM = " + convert + " seconds");
                    break;
                case "sax":
                    start = System.nanoTime();

                    memoryBeforeMb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;

                    SaxXmlReader saxXmlReader = new SaxXmlReader(fileToParse);
                    proteinList = saxXmlReader.getProteinEntryIdList();

                    memoryAfterMb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
                    LOGGER.info("Memory increased: " + (memoryAfterMb - memoryBeforeMb) + "Mb");

                    end = System.nanoTime();
                    elapsedTime = end - start;
                    convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
                    LOGGER.info("Time taken to parse file and find requested data using SAX = " + convert + " seconds");
                    break;
                case "stax":
                    start = System.nanoTime();

                    memoryBeforeMb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;

                    StaxXmlReader staxXmlReader = new StaxXmlReader(fileToParse);
                    proteinList = staxXmlReader.getProteinEntryIdList();

                    memoryAfterMb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
                    LOGGER.info("Memory increased: " + (memoryAfterMb - memoryBeforeMb) + "Mb");

                    end = System.nanoTime();
                    elapsedTime = end - start;
                    convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
                    LOGGER.info("Time taken to parse file and find requested data using StAX = " + convert + " seconds");
                    break;
                default:
                    proteinList = null;
                    LOGGER.warn("Not correct parameter entered. Use DOM, SAX or StAX as a parameter to run the program.");
            }
            printList(proteinList);
        } else {
            LOGGER.warn("Not correct parameter entered. Use DOM, SAX or StAX as a parameter to run the program.");
        }
    }

    public static String getURLToDownload(String propertiesFile) throws IOException {
        try (InputStream input = XMLReaderDemo.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            Properties props = new Properties();
            props.load(input);
            return props.getProperty("url");
        }
    }

    public static String getProteinName(String propertiesFile) throws IOException {
        try (InputStream input = XMLReaderDemo.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            Properties props = new Properties();
            props.load(input);
            return props.getProperty("proteinName");
        }
    }

    public static void printList(List<ProteinEntry> list) {
        LOGGER.info("The file contains " + list.size() + " entries.");
        LOGGER.info("ProteinEntry IDs with name \"" + searchProteinName + "\": ");
        String listIDs = null;
        for (ProteinEntry proteinEntryId: list) {
            listIDs = listIDs + ", " + proteinEntryId.getId();
        }
        LOGGER.info(listIDs.substring(6));
    }
}

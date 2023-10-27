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
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class XMLReaderDemo {

    private static Logger LOGGER = LoggerFactory.getLogger(XMLReaderDemo.class);

    private static final String LOCATION = System.getProperty("user.dir") + "\\Task7Downloads\\";

    private static String fileName;

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException {
        LOGGER.info("Log from {}", XMLReaderDemo.class.getSimpleName());

        if (!(new File(LOCATION).exists())) {
            Files.createDirectory(Paths.get(LOCATION));
        }

        Files.copy(getURLStreamToDownload("xmlFilesToDownload.properties"), Paths.get(LOCATION + "//" + fileName));

        File fileToParse = new File(LOCATION + "//" + fileName);

        List<ProteinEntry> proteinList;
        long start, end, elapsedTime, convert;
        long memoryAfterMb, memoryBeforeMb;
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "dom":
                    start = System.nanoTime();

                    memoryBeforeMb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;

                    DomXmlReader domXmlReader = new DomXmlReader(fileToParse);
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

    public static InputStream getURLStreamToDownload(String propertiesFile) throws IOException {
        String link;
        try (InputStream in = XMLReaderDemo.class.getClassLoader().getResourceAsStream(propertiesFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            link = reader.lines().findFirst().get();
        }
        fileName = new File(link).getName();
        return new URL(link).openStream();
    }

    public static void printList(List<ProteinEntry> list) {
        LOGGER.info("The file contains " + list.size() + " entries.");
        LOGGER.info("ProteinEntry IDs with name \"cytochrome c\": ");
        StringBuilder listIDs = null;
        for (ProteinEntry proteinEntryId: list) {
            listIDs.append(", ").append(proteinEntryId.getId());
        }
        LOGGER.info(listIDs.substring(6));
    }
}

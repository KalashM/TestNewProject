package com.example.fileprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class XMLReaderDemo {

    private static Logger LOGGER = LoggerFactory.getLogger(XMLReaderDemo.class);

    private static final String LOCATION = System.getProperty("user.dir") + "\\Task7Downloads\\";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException {
        LOGGER.info("Log from {}", XMLReaderDemo.class.getSimpleName());
        String downloadedFileName = downloadFile();
        File fileToParse = new File(System.getProperty("user.dir") + "\\Task7Downloads\\" + downloadedFileName);
        List<ProteinEntry> proteinList;
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "dom":
                    DomXmlReader domXmlReader = new DomXmlReader(fileToParse);
                    proteinList = domXmlReader.getProteinEntryIdList();
                    break;
                case "sax":
                    SaxXmlReader saxXmlReader = new SaxXmlReader(fileToParse);
                    proteinList = saxXmlReader.getProteinEntryIdList();
                    break;
                case "stax":
                    StaxXmlReader staxXmlReader = new StaxXmlReader(fileToParse);
                    proteinList = staxXmlReader.getProteinEntryIdList();
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

    private static String downloadFile() throws IOException {
        String link;
        try (InputStream in = XMLReaderDemo.class.getClassLoader().getResourceAsStream("xmlFilesToDownload.properties");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            link = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }

        if (!(new File(LOCATION).exists())) {
            Files.createDirectory(Paths.get(LOCATION));
        }

        FileLoader fileLoader = new FileLoader(link, LOCATION);
        String fileName = fileLoader.getFileName();
        fileLoader.run();

        return fileName;
    }

    public static void printList(List<ProteinEntry> list) {
        LOGGER.info("The file contains " + list.size() + " entries.");
        LOGGER.info("ProteinEntry IDs with name \"cytochrome c\": ");
        String listIDs = null;
        for (ProteinEntry proteinEntryId: list) {
            listIDs = listIDs + ", " + proteinEntryId.getId();
        }
        LOGGER.info(listIDs.substring(6));
    }
}

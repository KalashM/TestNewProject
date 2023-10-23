package com.example.fileprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLReaderDemo {

    private static Logger LOGGER = LoggerFactory.getLogger(XMLReaderDemo.class);

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException {
        File fileToParse = new File(System.getProperty("user.dir") + "\\Task7Downloads\\psd7003.xml");
        List<ProteinEntry> proteinList = new ArrayList<>();
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "dom":
                    DomXmlReader domXmlReader = new DomXmlReader(fileToParse);
                    proteinList = domXmlReader.getProteinEntryIdList();
                    //printList(proteinList);
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
                    LOGGER.info("Not correct parameter entered. Use DOM, SAX or SsAX as a parameter to run the program.");
            }
            printList(proteinList);
        } else {
            LOGGER.info("Not correct parameter entered. Use DOM, SAX or SsAX as a parameter to run the program.");
        }
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

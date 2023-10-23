package com.example.fileprocessing;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class StaxXmlReader {

    private static File fileToParse;

    public StaxXmlReader(File file) {
        this.fileToParse = file;
    }

    public List<ProteinEntry> getProteinEntryIdList() throws XMLStreamException, IOException {
        XMLInputFactory factory = XMLInputFactory.newInstance();

        factory.setProperty("javax.xml.stream.supportDTD", false);

        // File fileToParse = new File(System.getProperty("user.dir") + "\\Task7Downloads\\psd7003.xml");

        XMLStreamReader reader =  factory.createXMLStreamReader(Files.newInputStream(this.fileToParse.toPath()));

        List<ProteinEntry> proteinEntryList = new ArrayList<>();
        ProteinEntry proteinEntry = null;
        boolean isProtein = false;
        String tagContent = null;

        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (reader.getLocalName().equalsIgnoreCase("ProteinEntry")) {
                    proteinEntry = new ProteinEntry();
                    proteinEntry.setId(reader.getAttributeValue(0));
                } else if (reader.getLocalName().equalsIgnoreCase("protein")) {
                    isProtein = true;
                }
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                if (reader.getLocalName().equalsIgnoreCase("ProteinEntry")) {
                    if (proteinEntry.getName().equals("cytochrome c")) {
                        proteinEntryList.add(proteinEntry);
                    }
                } else if (reader.getLocalName().equalsIgnoreCase("protein")) {
                    isProtein = false;
                } else if (isProtein && reader.getLocalName().equalsIgnoreCase("name")) {
                    proteinEntry.setName(tagContent);
                }
            } else if (event == XMLStreamConstants.CHARACTERS) {
                tagContent = reader.getText().trim();
            }
        }
        return proteinEntryList;
    }
}

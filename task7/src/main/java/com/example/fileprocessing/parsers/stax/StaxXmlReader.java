package com.example.fileprocessing.parsers.stax;

import com.example.fileprocessing.model.ProteinEntry;

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

    private final File fileToParse;
    private final List<ProteinEntry> proteinEntryList = new ArrayList<>();
    private ProteinEntry proteinEntry = null;
    private final String proteinName;

    public StaxXmlReader(File file, String proteinName) {
        this.fileToParse = file;
        this.proteinName = proteinName;

    }

    public List<ProteinEntry> getProteinEntryIdList() throws XMLStreamException, IOException {
        XMLInputFactory factory = XMLInputFactory.newInstance();

        factory.setProperty("javax.xml.stream.supportDTD", false);

        XMLStreamReader reader =  factory.createXMLStreamReader(Files.newInputStream(this.fileToParse.toPath()));

        boolean isProtein = false;
        String tagContent = null;
        String id = null, name;

        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (reader.getLocalName().equalsIgnoreCase("ProteinEntry")) {
                    id = reader.getAttributeValue(0);
                } else if (reader.getLocalName().equalsIgnoreCase("protein")) {
                    isProtein = true;
                }
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                if (isProtein && reader.getLocalName().equalsIgnoreCase("name")) {
                    //proteinEntry.setName(tagContent);
                    name = tagContent;
                    proteinEntry = new ProteinEntry(id, name);
                } else if (reader.getLocalName().equalsIgnoreCase("protein")) {
                    isProtein = false;
                } else if (reader.getLocalName().equalsIgnoreCase("ProteinEntry")) {
                    if (proteinEntry.name().equals("cytochrome c")) {
                        proteinEntryList.add(proteinEntry);
                    }
                }
            } else if (event == XMLStreamConstants.CHARACTERS) {
                tagContent = reader.getText().trim();
            }
        }
        return proteinEntryList;
    }
}

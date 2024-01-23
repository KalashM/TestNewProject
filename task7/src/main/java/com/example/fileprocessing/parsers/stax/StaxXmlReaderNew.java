package com.example.fileprocessing.parsers.stax;

import com.example.fileprocessing.model.ProteinEntry;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class StaxXmlReaderNew {
    private final File fileToParse;
    private final List<ProteinEntry> proteinEntryList = new ArrayList<>();
    private ProteinEntry proteinEntry;
    private final String proteinName;
   // private
    private boolean isProtein = false;

    public StaxXmlReaderNew(File file, String proteinName) {
        this.fileToParse = file;
        this.proteinName = proteinName;

    }

    public List<ProteinEntry> getProteinEntryIdList() throws IOException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty("javax.xml.stream.supportDTD", false);
        XMLEventReader reader = factory.createXMLEventReader(Files.newInputStream(fileToParse.toPath()));
        String id = null, name;
        while (reader.hasNext()) {

            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                if (startElement.getName().getLocalPart().equals("ProteinEntry")) {
                    Attribute idAttr = startElement.getAttributeByName(new QName("id"));
                    if (idAttr != null) {
                        id = idAttr.getValue();
                    }
                } else if (startElement.getName().getLocalPart().equals("protein")) {
                    isProtein = true;
                } else if (startElement.getName().getLocalPart().equals("name") && isProtein) {
                    event = reader.nextEvent();
                    name = event.asCharacters().getData();
                    if (name.equals(proteinName)) {
                        proteinEntry = new ProteinEntry(id, name);
                        proteinEntryList.add(proteinEntry);
                    }
                }
            }
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                if (endElement.getName().getLocalPart().equals("protein")) {
                    isProtein = false;
                }
            }
        }
        return proteinEntryList;
    }
}

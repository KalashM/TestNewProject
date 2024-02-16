package com.example.fileprocessing.parsers.sax.handlers;

import com.example.fileprocessing.model.ProteinEntry;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ProteinEntryHandler extends DefaultHandler {
    public static final String PROTEINENTRY = "ProteinEntry";
    private String id;
    private String name;
    private List<ProteinEntry> proteinEntryList = new ArrayList<>();
    private ProteinEntry proteinEntry = null;
    private ProteinHandler proteinHandler = new ProteinHandler();

    private boolean isProteinEntry = false;

    public List<ProteinEntry> getProteinEntryList() {
        return proteinEntryList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(PROTEINENTRY)) {
            id = attributes.getValue("id");
            isProteinEntry = true;
        } else if (isProteinEntry) {
            proteinHandler.startElement(uri, localName, qName, attributes);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(PROTEINENTRY)) {
            name = proteinHandler.getProteinName();
            proteinEntry = new ProteinEntry(id, name);
                proteinEntryList.add(proteinEntry);
            isProteinEntry = false;
        } else if (isProteinEntry) {
            proteinHandler.endElement(uri,localName, qName);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (isProteinEntry) {
            proteinHandler.characters(ch, start, length);
        }
    }
}
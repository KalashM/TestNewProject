package com.example.fileprocessing.parsers.sax.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

public class NameHandler extends DefaultHandler {
    public static final String NAME = "name";
    private String name;
    private boolean isName = false;
    private StringBuilder tagContent;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals(NAME)) {
            isName = true;
            tagContent = new StringBuilder();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(NAME)) {
            name = tagContent.toString();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (isName) {
            tagContent.append(new String(ch, start, length));
        }
    }

    public String getName() {
        return name;
    }
}
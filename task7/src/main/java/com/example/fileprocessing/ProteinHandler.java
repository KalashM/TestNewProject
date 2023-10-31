package com.example.fileprocessing;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProteinHandler extends DefaultHandler {

    private static final String PROTEIN = "protein";
    private static boolean isProtein = false;
    private String proteinName = null;
    private NameHandler nameHandler = new NameHandler();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals(PROTEIN)) {
            isProtein = true;
        } else if (isProtein) {
            nameHandler.startElement(uri,localName, qName, attributes);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals(PROTEIN)) {
            isProtein = false;
        } else if (isProtein) {
            nameHandler.endElement(uri,localName, qName);
            proteinName = nameHandler.getName();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (isProtein) {
            nameHandler.characters(ch, start, length);
        }
    }

    public String getProteinName() {
        return proteinName;
    }
}

package com.example.fileprocessing;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxXmlReader {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        SAXParser parser = parserFactory.newSAXParser();
        MySAXHandler handler = new MySAXHandler();
        parser.parse(new File(System.getProperty("user.dir") + "\\Task7Downloads\\psd7003.xml"), handler);
        //parser.parse(new File(System.getProperty("user.dir") + "\\Task7Downloads\\test.xml"), handler);

        for (ProteinEntry protein : handler.getProteinEntryList()) {
            System.out.println(protein);
        }
    }
}

class MySAXHandler extends DefaultHandler {

    private boolean isProtein = false;
    private boolean isName = false;
    private List<ProteinEntry> proteinEntryList = new ArrayList<>();
    private ProteinEntry proteinEntry = null;

    public List<ProteinEntry> getProteinEntryList() {
        return proteinEntryList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("ProteinEntry")) {
            proteinEntry = new ProteinEntry();
            proteinEntry.id = attributes.getValue("id");
        } else if (qName.equals("protein")) {
            isProtein = true;
        } else if (qName.equals("name")) {
            isName = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("ProteinEntry")) {
            if (proteinEntry.name.equals("cytochrome c")) {
                proteinEntryList.add(proteinEntry);
            }
        } else if (qName.equals("protein")) {
            isProtein = false;
        } else if (isProtein && qName.equals("name")) {
            isName = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (isProtein && isName) {
            proteinEntry.name = new String(ch, start, length);
           // System.out.println("Protein Name: " + proteinEntry.name);
        }
    }
}

class ProteinEntry {
    public String id;
    public String name = null;

    public String toString() {
        return id;
    }
}

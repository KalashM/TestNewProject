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

    private File fileToParse;

    public SaxXmlReader(File file) {
        this.fileToParse = file;
    }

    public List<ProteinEntry> getProteinEntryIdList() throws SAXException, ParserConfigurationException, IOException {

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        SAXParser parser = parserFactory.newSAXParser();
        MySAXHandler handler = new MySAXHandler();
        parser.parse(this.fileToParse, handler);

        return handler.getProteinEntryList();
    }

}

class MySAXHandler extends DefaultHandler {

    private boolean isProtein = false;
    private String tagContent = null;

    private List<ProteinEntry> proteinEntryList = new ArrayList<>();
    private ProteinEntry proteinEntry = null;

    public List<ProteinEntry> getProteinEntryList() {
        return proteinEntryList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("ProteinEntry")) {
            proteinEntry = new ProteinEntry();
            proteinEntry.setId(attributes.getValue("id"));
        } else if (qName.equals("protein")) {
            isProtein = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("ProteinEntry")) {
            if (proteinEntry.getName().equals("cytochrome c")) {
                proteinEntryList.add(proteinEntry);
            }
        } else if (qName.equals("protein")) {
            isProtein = false;
        } else if (isProtein && qName.equals("name")) {
            proteinEntry.setName(tagContent);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        tagContent = new String(ch, start, length);
    }
}

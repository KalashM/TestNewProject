package com.example.fileprocessing.parsers.sax;

import com.example.fileprocessing.model.ProteinEntry;
import com.example.fileprocessing.parsers.sax.handlers.ProteinEntryHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxXmlReaderNew {
    private final File fileToParse;
    private final String proteinName;

    public SaxXmlReaderNew(File file, String proteinName) {
        this.fileToParse = file;
        this.proteinName = proteinName;
    }

    public List<ProteinEntry> getProteinEntryIdList() throws SAXException, ParserConfigurationException, IOException {

        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        SAXParser parser = parserFactory.newSAXParser();
        ProteinEntryHandler handler = new ProteinEntryHandler();
        parser.parse(this.fileToParse, handler);

        return handler.getProteinEntryList();
    }

    public List<ProteinEntry> getProteinEntryIdListByName() throws ParserConfigurationException, IOException, SAXException {
        List<ProteinEntry> filteredList = new ArrayList<>();
        for (ProteinEntry proteinEntry: getProteinEntryIdList()) {
            if (proteinEntry.name().equals(proteinName)) {
                filteredList.add(proteinEntry);
            }
        }
        return filteredList;
    }


}

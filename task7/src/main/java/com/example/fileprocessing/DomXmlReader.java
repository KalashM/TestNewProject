package com.example.fileprocessing;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomXmlReader {

    private final File fileToParse;
    private final List<ProteinEntry> proteinEntryList = new ArrayList<>();
    private ProteinEntry proteinEntry = null;

    private final String proteinName;

    public DomXmlReader(File file, String proteinName) {
        this.fileToParse = file;
        this.proteinName = proteinName;
    }

    public List<ProteinEntry> getProteinEntryIdList() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        //Get the DOM Builder Factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        //Get the DOM Builder
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Load and Parse the XML document
        Document document = builder.parse(this.fileToParse);

        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//ProteinEntry[descendant::protein[descendant::name[text()=" + "'" + proteinName + "'" + "]]]";

        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String id = node.getAttributes().getNamedItem("id").getNodeValue();
            proteinEntry = new ProteinEntry(id, proteinName);
            proteinEntryList.add(proteinEntry);
        }

        return proteinEntryList;
    }
}

package com.example.fileprocessing;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomXmlReader {

    private static File fileToParse;

    public DomXmlReader(File file) {
        fileToParse = file;
    }

    public static Document parseDocument() throws ParserConfigurationException, IOException, SAXException {
        //Get the DOM Builder Factory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        //Get the DOM Builder
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Load and Parse the XML document
        Document document = builder.parse(fileToParse);

        return document;
    }

    public static List<String> getProteinEntryIdList(Document document) {
        //Iterating through the nodes and extracting the data.
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        List<String> proteinEntryList = new ArrayList<>();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String proteinEntryId = null;

            if (node instanceof Element && node.getNodeName().equals("ProteinEntry")) {
                NodeList proteinEntryNodeList = node.getChildNodes();

                for (int j = 0; j < proteinEntryNodeList.getLength(); j++) {
                    Node proteinEntryNode = proteinEntryNodeList.item(j);

                    if (proteinEntryNode instanceof Element
                            && proteinEntryNode.getNodeName().equals("protein")
                    ) {
                        NodeList proteinEntryProteinNodeList = proteinEntryNode.getChildNodes();

                        for (int k = 0; k < proteinEntryProteinNodeList.getLength(); k++) {
                            Node proteinEntryProteinNode = proteinEntryProteinNodeList.item(k);
                            if (proteinEntryProteinNode instanceof Element
                                    && proteinEntryProteinNode.getNodeName().equals("name")
                                    && proteinEntryProteinNode.getTextContent().equals("cytochrome c")
                            ) {
                                proteinEntryId = node.getAttributes().getNamedItem("id").getNodeValue();
                                proteinEntryList.add(proteinEntryId);
                            }
                        }
                    }
                }
            }
        }
        return proteinEntryList;
    }
}

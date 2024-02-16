package com.example.fileprocessing;

import com.example.fileprocessing.model.ProteinEntry;
import com.example.fileprocessing.parsers.dom.DomXmlReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DomXmlReaderTest {

    @ParameterizedTest
    @MethodSource("actualProteinEntryList")
    void getProteinEntryIdListSize(List<ProteinEntry> actualList) {
        assertEquals(1, actualList.size());
    }

    @ParameterizedTest
    @MethodSource("actualProteinEntryList")
    void getPoteinEntryIdListEntryID(List<ProteinEntry> actualList) {
        List<String> idList = new ArrayList<>();
        for (ProteinEntry proteinEntry: actualList) {
            idList.add(proteinEntry.id());
        }
        assertTrue(idList.contains("CCHU"));
    }

    public static Stream<Arguments> actualProteinEntryList() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        ClassLoader classLoader = DomXmlReaderTest.class.getClassLoader();
        File file = new File(classLoader.getResource("test.xml").getPath());
        DomXmlReader domXmlReader = new DomXmlReader(file, "CCHU");
        return Stream.of(
                Arguments.of(domXmlReader.getProteinEntryIdList()));
    }
}
package com.example.fileprocessing;

import com.example.fileprocessing.model.ProteinEntry;
import com.example.fileprocessing.parsers.dom.DomXmlReader;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
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
            idList.add(proteinEntry.getId());
        }
        assertTrue(idList.contains("CCHU"));
    }

    public static Stream<Arguments> actualProteinEntryList() throws ParserConfigurationException, IOException, SAXException {
        ClassLoader classLoader = DomXmlReaderTest.class.getClassLoader();
        File file = new File(classLoader.getResource("test.xml").getFile());
        DomXmlReader domXmlReader = new DomXmlReader(file);
        return Stream.of(
                Arguments.of(domXmlReader.getProteinEntryIdList()));
    }
}
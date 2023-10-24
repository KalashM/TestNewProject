package com.example.fileprocessing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
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
        assertEquals("CCHU", actualList.get(0).getId());
    }

    public static Stream<Arguments> actualProteinEntryList() throws ParserConfigurationException, IOException, SAXException {
        ClassLoader classLoader = DomXmlReaderTest.class.getClassLoader();
        File file = new File(classLoader.getResource("test.xml").getFile());
        DomXmlReader domXmlReader = new DomXmlReader(file);
        return Stream.of(
                Arguments.of(domXmlReader.getProteinEntryIdList()));
    }
}
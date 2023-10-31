package com.example.fileprocessing;

import com.example.fileprocessing.model.ProteinEntry;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StaxXmlReaderTest {

    @ParameterizedTest
    @MethodSource("actualProteinEntryList")
    void getProteinEntryIdListSize(List<ProteinEntry> actualList) {
        assertEquals(1, actualList.size());
    }

    /*@ParameterizedTest
    @MethodSource("actualProteinEntryList")
    void getPoteinEntryIdListEntryID(List<ProteinEntry> actualList) {
        List<String> idList = new ArrayList<>();
        for (ProteinEntry proteinEntry: actualList) {
            idList.add(proteinEntry.getId());
        }
        assertTrue(idList.contains("CCHU"));
    }*/

    /*public static Stream<Arguments> actualProteinEntryList() throws IOException, XMLStreamException {
        ClassLoader classLoader = DomXmlReaderTest.class.getClassLoader();
        File file = new File(classLoader.getResource("test.xml").getFile());
        StaxXmlReader staxXmlReader = new StaxXmlReader(file);
        return Stream.of(
                Arguments.of(staxXmlReader.getProteinEntryIdList()));
    }*/
}
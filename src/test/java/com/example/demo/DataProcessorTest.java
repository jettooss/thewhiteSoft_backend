package com.example.demo;
import com.example.demo.DataProcessor.*;
import com.example.demo.Model.Record;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;

public class DataProcessorTest {
    private RecordRepository recordRepository;

    @BeforeEach
    void setUp() {
        recordRepository = new RecordRepository("src/test/java/com/example/demo/resources/test-input.json", "src/test/java/com/example/demo/resources/");
    }

    @Test
    void testLoadRecordsFromJson() {
        recordRepository.loadRecordsFromJson();

        assertFalse(recordRepository.getRecords().isEmpty());

        Record record = recordRepository.getRecordById(1);
        assertNotNull(record);
    }
    @Test
    void testGetRecordById() {
        Record record = recordRepository.getRecordById(2);

        assertNotNull(record);
        assertEquals("A", record.getName());
    }
    @Test
    void testGetRecordByName() {
        // Test getting a record by name
        Record record = recordRepository.getRecordByName("B");

        assertNotNull(record);
        assertEquals(1, record.getId());
    }
    @Test
    void testSaveToJson() {
        // Test saving records to a JSON file
        recordRepository.loadRecordsFromJson();

        // Save the records to a test file
        String testFileName = "test-save";
        try {
            assertTrue(recordRepository.saveToJson(testFileName + ".json"));
        } catch (IOException e) {
            fail("Не удалось сохранить записи в файл JSON.");
        }

    }
}
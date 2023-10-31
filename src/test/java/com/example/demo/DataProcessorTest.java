package com.example.demo;
import com.example.demo.DataProcessor.*;
import com.example.demo.DataProcessor.load;
import com.example.demo.Model.Record;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataProcessorTest {
    private final int id = 1;
    private final String name = "Test";
    private final String description = "Description";
    private final String link = "http://example.com";
    private Record expectedRecord =new Record(id,name,description,link);
    private Map<Integer, Record> records = new HashMap<>();
    @Test
    void testAddRecord() {
        // Act
        Main_Functions.addRecord(records, id, name, description, link);
        Record actualRecord = records.get(1);

        // Assert
        assertNotNull(actualRecord);
        assertEquals(expectedRecord.getId(), actualRecord.getId());
        assertEquals(expectedRecord.getName(), actualRecord.getName());
        assertEquals(expectedRecord.getDescription(), actualRecord.getDescription());
        assertEquals(expectedRecord.getLink(), actualRecord.getLink());
    }
    @Test
    void testSearchRecordsById() {
        // Arrange
        records.put(1, expectedRecord);
        String expected = "Запись найдена:\n" +
                "Идентификатор: " + expectedRecord.getId() + "\n" +
                "Наименование: " + expectedRecord.getName() + "\n" +
                "Описание: " + expectedRecord.getDescription() + "\n" +
                "Ссылка: " + expectedRecord.getLink() + "\n";

        // Act
        String actual = Main_Functions.searchRecordsById(records, 1);

        // Assert
        assertEquals(expected, actual);
    }
    @Test
    void testSearchRecordsByName() {
        // Arrange
        records.put(1, expectedRecord);
        String expected = "Запись найдена:\n" +
                "Идентификатор: " + expectedRecord.getId() + "\n" +
                "Наименование: " + expectedRecord.getName() + "\n" +
                "Описание: " + expectedRecord.getDescription() + "\n" +
                "Ссылка: " + expectedRecord.getLink() + "\n";

        // Act
        String result = Main_Functions.searchRecordsByName(records, expectedRecord.getName());
        
        // Assert
        assertEquals(result, expected);
    }
    @Test
    void testSearchRecordsWithNoRecordName() {
        // Arrange
        records.put(1, new Record(1, "first", "description1", "link1"));
        records.put(2, new Record(2, "second", "description2", "link2"));
        String expected = "Запись не найдена.\n";

        // Act
        String resultName = Main_Functions.searchRecordsByName(records, "third");

        // Assert
        assertEquals(expected, resultName, "Запись с указанным наименованием не найдена.\n");
    }
    @Test
    void testSearchRecordsWithNoRecordID() {
        // Arrange
        records.put(1, new Record(1, "first", "description1", "link1"));
        records.put(2, new Record(2, "second", "description2", "link2"));
        String expected = "Запись не найдена.\n";

        // Act
        String resultId = Main_Functions.searchRecordsById(records, 3);

        // Assert
        assertEquals(expected, resultId, "Запись с указанным идентификатором не найдена.\n");
    }
    @Test
    public void testLoadRecordsFromFile() throws IOException {
        // Arrange
        String filePath = "src/test/java/com/example/demo/resources/test-input.json";

        // Act
        Map<Integer, Record> records = load.loadRecordsFromJson(filePath);

        // Assert
        assertEquals(2, records.size(), "Размер карты записей должен быть равен 2");
    }
    @Test
    public void testPrintMenu() {
        // Arrange
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalPrintStream = System.out;
        System.setOut(printStream);
        String expectedOutput = "Меню:\n" +
                "1 - Вывести запись по id\n" +
                "2 - Найти записи по части наименования\n" +
                "3 - выход\n" +
                "4 - сохранить в json\n" +
                "5 - добавить значение\n" +
                "Выберите пункт меню: ";

        // Act
        Menu.printMenu();
        System.setOut(originalPrintStream);
        String output = outputStream.toString();

        // Assert
        assertEquals(output.trim(), expectedOutput.trim());
    }
}


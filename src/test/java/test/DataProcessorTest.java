package test;
import org.example.DataProcessor;
import org.example.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

class DataProcessorTest {
    private Map<Integer, Record> records;
    private Record expectedRecord;

    @BeforeEach
    void setUp() {
        records = new HashMap<>();
        expectedRecord = getExpectedRecord(1, "Test", "Description", "http://example.com");
    }

    private Record getExpectedRecord(int id, String name, String description, String link) {
        return new Record(id, name, description, link);
    }

    @Test
    void testAddRecord() {
        // Arrange
        int id = 1;
        String name = "Test";
        String description = "Description";
        String link = "http://example.com";

        // Act
        DataProcessor.addRecord(records, id, name, description, link);
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
        String actual = DataProcessor.searchRecordsById(records, 1);

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
        String result = DataProcessor.searchRecordsByName(records, expectedRecord.getName());


        // Assert
        assertEquals(result, expected);
    }
    @Test
    void testSearchRecordsWithNoRecord() {
        // Arrange
        records.put(1, new Record(1, "first", "description1", "link1"));
        records.put(2, new Record(2, "second", "description2", "link2"));
        String expected = "Запись не найдена.\n";


        // Act
        String resultId = DataProcessor.searchRecordsById(records, 3);
        String resultName = DataProcessor.searchRecordsByName(records, "third");

        // Assert
        assertEquals(expected, resultId, "Запись с указанным идентификатором не найдена.\n");
        assertEquals(expected, resultName, "Запись с указанным наименованием не найдена.\n");
    }
    @Test
    public void testLoadRecordsFromFile() throws IOException {
        // Arrange
        String filePath = "src/test/resources/test-input.json";

        // Act
        DataProcessor.loadRecordsFromFile(filePath, records);

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
        DataProcessor.printMenu();
        System.setOut(originalPrintStream);
        String output = outputStream.toString();

        // Assert
        assertEquals(output.trim(), expectedOutput.trim());
    }
}


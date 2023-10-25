package test;
import org.example.DataProcessor;
import org.example.Record;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

class DataProcessorTest {
    //        Этот тест проверяет метод addRecord класса DataProcessor.
    //      В первую очередь, метод создает запись, затем добавляет ее в карту, а после проверяет совпадает ли добавленная запись с ожидаемой.
    @Test
    void testAddRecord() {
        Map<Integer, Record> records = new HashMap<>();
        int id = 1;
        String name = "Test";
        String description = "Description";
        String link = "http://example.com";

        DataProcessor.addRecord(records, id, name, description, link);
        Record expectedRecord = new Record(id, name, description, link);
        Record actualRecord = records.get(1);
        assertNotNull(actualRecord); // проверяем, что запись не null
        assertEquals(expectedRecord.getId(), actualRecord.getId());
        assertEquals(expectedRecord.getName(), actualRecord.getName());
        assertEquals(expectedRecord.getDescription(), actualRecord.getDescription());
        assertEquals(expectedRecord.getLink(), actualRecord.getLink());
    }

    //   Этот тест проверяет, правильно ли метод searchRecords ищет записи по идентификатору и возвращает соответствующую сигнатуру строки.
    @Test
    void testSearchRecordsById() {
        Map<Integer, Record> records = new HashMap<>();
        int id = 1;
        String name = "Test";
        String description = "Description";
        String link = "http://example.com";
        records.put(1, new Record(id, name, description, link));
        String actual = DataProcessor.searchRecordsById(records, 1);
        String expected = "Запись найдена:\n" +
                "Идентификатор: " + id + "\n" +
                "Наименование: " + name + "\n" +
                "Описание: " + description + "\n" +
                "Ссылка: " + link + "\n";

        assertEquals(expected, actual);
    }

    //   Этот тест проверяет, правильно ли метод searchRecords находит записи по имени и возвращает соответствующую информацию.
    @Test
    void testSearchRecordsByName() {
        Map<Integer, Record> records = new HashMap<>();
        int id = 1;
        String name = "Test";
        String description = "Description";
        String link = "http://example.com";
        records.put(1, new Record(id, name, description, link));
        String result = DataProcessor.searchRecordsByName(records, name);
        String expected = "Запись найдена:\n" +
                "Идентификатор: " + id + "\n" +
                "Наименование: " + name + "\n" +
                "Описание: " + description + "\n" +
                "Ссылка: " + link + "\n";
        assertEquals(result, expected);

    }

    //        Этот тест проверяет, правильно ли метод searchRecords обрабатывает запросы поиска записей,
    //          которые не существуют (по идентификатору или имени), и возвращает соответствующее сообщение.
    @Test
    void testSearchRecordsWithNoRecord() {

        Map<Integer, Record> records = new HashMap<>();
        records.put(1, new Record(1, "first", "description1", "link1"));
        records.put(2, new Record(2, "second", "description2", "link2"));
        String resultId = DataProcessor.searchRecordsById(records, 3);
        String resultName = DataProcessor.searchRecordsByName(records, "third");
        String expected = "Запись не найдена.\n";

        assertEquals(expected, resultId, "Запись с указанным идентификатором не найдена.\n");
        assertEquals(expected, resultName, "Запись с указанным наименованием не найдена.\n");
    }

    //   Этот тест проверяет, правильно ли метод loadRecordsFromFile загружает данные из файла и складывает в карту.
    @Test
    public void testLoadRecordsFromFile() throws IOException {
        Map<Integer, Record> records = new HashMap<>();
        String filePath = "src/test/resources/test-input.json";

        DataProcessor.loadRecordsFromFile(filePath, records);
        assertEquals(2, records.size(), "Размер карты записей должен быть равен 2");


    }

    //        Этот тест написан для проверки функции printMenu, которая выводит меню для пользователя.
    //        Здесь используется перенаправление вывода в ByteArrayOutputStream для последующего сравнения результата с ожидаемым.
    @Test
    public void testPrintMenu() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        PrintStream originalPrintStream = System.out;
        System.setOut(printStream);

        DataProcessor.printMenu();

        System.setOut(originalPrintStream);

        String output = outputStream.toString();

        String expectedOutput = "Меню:\n" +
                "1 - Вывести запись по id\n" +
                "2 - Найти записи по части наименования\n" +
                "3 - выход\n" +
                "4 - сохранить в json\n" +
                "5 - добавить значение\n" +
                "Выберите пункт меню: ";

        assertEquals(output.trim(), expectedOutput.trim());
    }
}


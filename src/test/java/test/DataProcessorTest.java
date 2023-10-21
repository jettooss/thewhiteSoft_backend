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
    @Test
    void testAddRecord() {
//        Этот тест проверяет метод addRecord класса DataProcessor.
//                В первую очередь, метод создает запись, затем добавляет ее в карту, а после проверяет совпадает ли добавленная запись с ожидаемой.
        Map<Integer, org.example.Record> records = new HashMap<>();
        DataProcessor.addRecord(records, 1, "Test", "Description", "http://example.com");
        Record expectedRecord = new Record(1, "Test", "Description", "http://example.com");
        Record actualRecord = records.get(1);
        assertNotNull(actualRecord); // проверяем, что запись не null
        assertEquals(expectedRecord.getId(), actualRecord.getId());
        assertEquals(expectedRecord.getName(), actualRecord.getName());
        assertEquals(expectedRecord.getDescription(), actualRecord.getDescription()); // new
        assertEquals(expectedRecord.getLink(), actualRecord.getLink()); // new
    }


    @Test

    void testSearchRecordsById() {
//        Этот тест проверяет, правильно ли метод searchRecords ищет записи по идентификатору и возвращает соответствующую сигнатуру строки.
        Map<Integer, Record> records = new HashMap<>();
        //добавим в Map демонстрационные записи
        records.put(1, new Record(1, "first", "description1", "link1"));
        records.put(2, new Record(2, "second", "description2", "link2"));

        String result = DataProcessor.searchRecords(records, 1);
        String expected = "Запись найдена:\n" +
                "Идентификатор: 1\n" +
                "Наименование: first\n" +
                "Описание: description1\n" +
                "Ссылка: link1\n";
        assertEquals(expected, result, "Тест не пройден: поиск по идентификатору");
    }

    @Test
    void testSearchRecordsByName() {
//        Этот тест проверяет, правильно ли метод searchRecords находит записи по имени и возвращает соответствующую информацию.



        Map<Integer, Record> records = new HashMap<>();
        //добавим в Map демонстрационные записи
        records.put(1, new Record(1, "first", "description1", "link1"));
        records.put(2, new Record(2, "second", "description2", "link2"));

        String result = DataProcessor.searchRecords(records, "second");
        String expected = "Запись найдена:\n" +
                "Идентификатор: 2\n" +
                "Наименование: second\n" +
                "Описание: description2\n" +
                "Ссылка: link2\n";

        assertEquals(expected, result, "Тест не пройден: поиск по имени");
    }

    @Test
    void testSearchRecordsWithNoRecord() {
//        Этот тест проверяет, правильно ли метод searchRecords обрабатывает запросы поиска записей,
//                которые не существуют (по идентификатору или имени), и возвращает соответствующее сообщение.

        Map<Integer, Record> records = new HashMap<>();
        //добавим в Map демонстрационные записи
        records.put(1, new Record(1, "first", "description1", "link1"));
        records.put(2, new Record(2, "second", "description2", "link2"));

        String resultId = DataProcessor.searchRecords(records, 3);
        String resultName = DataProcessor.searchRecords(records, "third");
        System.out.println(resultId);
        System.out.println(resultName);

        String expected = "Запись не найдена.\n";

        assertEquals(expected, resultId, "Запись с указанным идентификатором не найдена.\n");
        assertEquals(expected, resultName, "Запись с указанным наименованием не найдена.\n");
    }

    @Test

    public void testLoadRecordsFromFile() throws IOException {
//        Этот тест проверяет, правильно ли метод loadRecordsFromFile загружает данные из файла и складывает в карту.
        Map<Integer, Record> records = new HashMap<>();
        String filePath = "src/test/resources/test-input.json";

        // Act
        DataProcessor.loadRecordsFromFile(filePath, records);
        assertEquals(2, records.size(), "Размер карты записей должен быть равен 2");

        System.out.println(records);


    }
    @Test
    public void testPrintMenu() {
//        Этот тест написан для проверки функции printMenu, которая выводит меню для пользователя.
//        Здесь используется перенаправление вывода в ByteArrayOutputStream для последующего сравнения результата с ожидаемым.Но он выдает ошибку,хотя
//        expectedOutput ==output, возможно дело в кодировке
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
        System.out.println(output);

        System.out.println(expectedOutput);

        // я не понимаю, в чем тут ошибка
        assertEquals(output, expectedOutput,"?????");

     }
}



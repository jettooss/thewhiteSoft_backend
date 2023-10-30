package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProcessor {
    // Функция загружает записи из JSON файла по переданному пути, преобразует их в объекты Record и хранит в переданной Map.
    public static void loadRecordsFromFile(String filePath, Map<Integer, Record> records) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File(filePath));
        for (JsonNode recordNode : jsonNode) {
            int id = recordNode.get("id").asInt();
            String name = recordNode.get("name").asText();
            String description = recordNode.get("description").asText();
            String link = recordNode.get("link").asText();
            System.out.println("ID: " + id + ", Name: " + name + ", Description: " + description + ", Link: " + link);
            Record record = new Record(id, name, description, link);
            records.put(id, record);
        }
    }
    // Функция выводит в консоль текстовое меню для интерфейса пользователя. Этот метод предназначен для взаимодействия с пользователем.
    public static void printMenu() {
        String expectedOutput = "Меню:\n" +
                "1 - Вывести запись по id\n" +
                "2 - Найти записи по части наименования\n" +
                "3 - выход\n" +
                "4 - сохранить в json\n" +
                "5 - добавить значение\n" +
                "Выберите пункт меню: ";
        System.out.println(expectedOutput);
    }
    public static String searchRecordsById(Map<Integer, Record> records, Integer id) {
        Record record = records.get(id);
        String returnString;
        if (record != null) {
             returnString = "Запись найдена:\n" +
                    "Идентификатор: " + record.getId() + "\n" +
                    "Наименование: " + record.getName() + "\n" +
                    "Описание: " + record.getDescription() + "\n" +
                    "Ссылка: " + record.getLink() + "\n";
        } else {
            System.out.println("Запись не найдена");
            return "Запись не найдена.\n";
         }
        return returnString;
    }
    public static String searchRecordsByName(Map<Integer, Record> records, String name) {
        String returnString = "Запись не найдена.\n";
        for (Record record: records.values()) {
            if (record.getName().equalsIgnoreCase(name)) {
                returnString = "Запись найдена:\n" +
                        "Идентификатор: " + record.getId() + "\n" +
                        "Наименование: " + record.getName() + "\n" +
                        "Описание: " + record.getDescription() + "\n" +
                        "Ссылка: " + record.getLink() + "\n";
                break;
            }
        }
        return returnString;
    }
    // Функция создает новую запись на основе переданных данных (id, name, description, link), сохраняет ее в переданной Map и возвращает созданную запись.
    public static Record addRecord (Map<Integer, Record> records, int id, String name, String description, String link) {
        Record newRecord = new Record(id, name, description, link);
        return records.put(id, newRecord);
    }
    // Функция конвертирует Map в JSON и сохраняет записи в файл. Если файл успешно создан, функция возвращает true.
    // Если в процессе записи произошла ошибка, то выбрасывается исключение.
    public static boolean saveRecordsToJson(String name_file , Map<Integer, Record> records) throws IOException {
        String outputFilePath = "src/main/resources/";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Record> recordList = new ArrayList<>(records.values());
        objectMapper.writeValue(new File(outputFilePath+name_file+".json"), recordList);
        System.out.println("Список записей успешно сохранен в JSON файл: " + name_file);
        return new File(outputFilePath+name_file+".json").exists();
    }
}

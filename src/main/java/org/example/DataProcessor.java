package org.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProcessor {



    public static void loadRecordsFromFile(String filePath, Map<Integer, Record> records) throws IOException {
        // Функция загружает записи из JSON файла по переданному пути, преобразует их в объекты Record и хранит в переданной Map.

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


    public static void printMenu() {
        // Функция выводит в консоль текстовое меню для интерфейса пользователя. Этот метод предназначен для взаимодействия с пользователем.

        String expectedOutput = "Меню:\n" +
                "1 - Вывести запись по id\n" +
                "2 - Найти записи по части наименования\n" +
                "3 - выход\n" +
                "4 - сохранить в json\n" +
                "5 - добавить значение\n" +
                "Выберите пункт меню: ";
        System.out.println(expectedOutput);
    }

    public static String searchRecords(Map<Integer, Record> records, Object searchTerm) {
        // Функция ищет записи в Map на основе поискового запроса (searchTerm), который может быть целым числом или строкой.
        // Если searchTerm - это Integer, находится запись с таким ID. Если нет - возвращается сообщение о том, что запись не найдена.
        // Если searchTerm - это String, ищется запись с таким именем. Если таковая не обнаружена - возвращается сообщение о том, что запись не найдена.

        String returnString = "";

        if (searchTerm instanceof Integer) {
            Record record = records.get((Integer)searchTerm);

            if (record == null) {
                returnString = "Запись не найдена.\n";

            } else {
                returnString = "Запись найдена:\n" +
                        "Идентификатор: " + record.getId() + "\n" +
                        "Наименование: " + record.getName() + "\n" +
                        "Описание: " + record.getDescription() + "\n" +
                        "Ссылка: " + record.getLink() + "\n";
            }

        } else if (searchTerm instanceof String) {
            for (Record record: records.values()) {
                if (record.getName().equalsIgnoreCase((String)searchTerm)) {
                    returnString = "Запись найдена:\n" +
                            "Идентификатор: " + record.getId() + "\n" +
                            "Наименование: " + record.getName() + "\n" +
                            "Описание: " + record.getDescription() + "\n" +
                            "Ссылка: " + record.getLink() + "\n";
                    break;
                }
            }

            if (returnString.equals("")) {
                returnString = "Запись не найдена.\n";
            }

        }

        return returnString;
    }


    public static Record addRecord (Map<Integer, Record> records, int id, String name, String description, String link) {
        // Функция создает новую запись на основе переданных данных (id, name, description, link), сохраняет ее в переданной Map и возвращает созданную запись.

        Record newRecord = new Record(id, name, description, link);
        return records.put(id, newRecord);
    }
    public static boolean saveRecordsToJson(String name_file , Map<Integer, Record> records) throws IOException {
        // Функция конвертирует Map в JSON и сохраняет записи в файл. Если файл успешно создан, функция возвращает true.
        // Если в процессе записи произошла ошибка, то выбрасывается исключение.
        String outputFilePath = "src/main/resources/";

        ObjectMapper objectMapper = new ObjectMapper();
        List<Record> recordList = new ArrayList<>(records.values());
        objectMapper.writeValue(new File(outputFilePath+name_file+".json"), recordList);
        System.out.println("Список записей успешно сохранен в JSON файл: " + name_file);
        return new File(outputFilePath+name_file+".json").exists();

    }

}

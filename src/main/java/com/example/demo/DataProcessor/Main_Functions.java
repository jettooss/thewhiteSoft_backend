package com.example.demo.DataProcessor;
import com.example.demo.Model.Record;
import org.springframework.stereotype.Service;
import java.util.Map;
@Service
public class Main_Functions {
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
}

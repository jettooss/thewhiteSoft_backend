package org.example;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
public class runProcessingLoop {
    public static void run(Scanner scanner, Map<Integer, Record> records) throws IOException {
        while (true) {
            DataProcessor.printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    searchRecordsById(scanner, records);
                    break;
                case 2:
                    searchRecordsByName(scanner, records);
                    break;
                case 4:
                    saveRecordsToJson(scanner, records);
                    break;
                case 5:
                    addRecord(scanner, records);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте ещё раз.");
            }
        }
    }
    private static void searchRecordsById(Scanner scanner, Map<Integer, Record> records) {
        System.out.print("Введите идентификатор записи: ");
        int id = scanner.nextInt();
        String data = DataProcessor.searchRecordsById(records, id);
        System.out.println(data);
    }
    private static void searchRecordsByName(Scanner scanner, Map<Integer, Record> records) {
        System.out.print("Введите часть наименования для поиска: ");
        String searchTerm = scanner.nextLine();
        String data = DataProcessor.searchRecordsByName(records, searchTerm);
        System.out.println(data);
    }
    private static void saveRecordsToJson(Scanner scanner, Map<Integer, Record> records) throws IOException {
        System.out.print("Введите имя файла для сохранения данных: ");
        String fileName = scanner.nextLine();
        DataProcessor.saveRecordsToJson(fileName, records);
    }
    private static void addRecord(Scanner scanner, Map<Integer, Record> records) {
        System.out.print("Введите идентификатор записи: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите наименование записи: ");
        String name = scanner.nextLine();
        System.out.print("Введите описание записи: ");
        String description = scanner.nextLine();
        System.out.print("Введите ссылку на записи: ");
        String link = scanner.nextLine();
        Record newRecord = new Record(id, name, description, link);
        records.put(id, newRecord);
        DataProcessor.addRecord(records, id, name, description, link);
        System.out.println("Запись успешно добавлена в Map.");
    }

}
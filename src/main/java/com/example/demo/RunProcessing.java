package com.example.demo;
import com.example.demo.Model.Record;
import com.example.demo.DataProcessor.*;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class RunProcessing {
    public static void run( Map<Integer, Record> records) throws IOException {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            Menu.printMenu();
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
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте ещё раз.");
            }
        }
    }
    private static void searchRecordsById(Scanner scanner, Map<Integer, Record> records) {
        System.out.print("Введите идентификатор записи: ");
        int id = scanner.nextInt();
        String data = Main_Functions.searchRecordsById(records, id);
        System.out.println(data);
    }
    private static void searchRecordsByName(Scanner scanner, Map<Integer, Record> records) {
        System.out.print("Введите часть наименования для поиска: ");
        String searchTerm = scanner.nextLine();
        String data = Main_Functions.searchRecordsByName(records, searchTerm);
        System.out.println(data);
    }
    private static void saveRecordsToJson(Scanner scanner, Map<Integer, Record> records) throws IOException {
        System.out.print("Введите имя файла для сохранения данных: ");
        String fileName = scanner.nextLine();
        Save.saveToJson(fileName, records);
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
        Main_Functions.addRecord(records, id, name, description, link);
        System.out.println("Запись успешно добавлена в Map.");
    }

}
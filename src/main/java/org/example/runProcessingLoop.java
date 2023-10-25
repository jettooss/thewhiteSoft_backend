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
                    System.out.print("Введите идентификатор записи: ");
                    int id = scanner.nextInt();
                    String data=DataProcessor.searchRecordsById(records, id);
                    System.out.println(data);
                    break;
                case 2:
                    System.out.print("Введите часть наименования для поиска: ");
                    String searchTerm = scanner.nextLine();
                    data =DataProcessor.searchRecordsByName(records, searchTerm);
                    System.out.println(data);

                    break;
                case 4:
                    System.out.print("Введите имя файла для сохранения данных: ");
                    String fileName = scanner.nextLine();
                    DataProcessor.saveRecordsToJson(fileName, records);
                    break;
                case 5:
                    System.out.print("Введите идентификатор записи: ");
                    id = scanner.nextInt();

                    System.out.print("Введите наименование записи: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();

                    System.out.print("Введите описание записи: ");
                    String description = scanner.nextLine();

                    System.out.print("Введите ссылку на записи: ");
                    String link = scanner.nextLine();

                    Record newRecord = new Record(id, name, description, link);
                    records.put(id, newRecord);

                    DataProcessor.addRecord(records, id, name, description, link);
                    System.out.println("Запись успешно добавлена в Map.");
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте ещё раз.");
            }
        }
    }
}
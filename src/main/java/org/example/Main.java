package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


class Main {
    public static void main(String[] args) throws IOException {
        // Создание хранилища для записей в формате Map, где ключ - это ID записи, а значение - сама запись.

        Map<Integer, Record> records = new HashMap<>();
        // Создание объекта сканера для чтения ввода пользователя.

        Scanner scanner = new Scanner(System.in);
        String filePath = "src/main/resources/data.json";

        try {
            DataProcessor.loadRecordsFromFile(filePath, records);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла данных.");
            return;
        }



        while (true) {
            DataProcessor.printMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите идентификатор записи: ");
                    try {
                        int id = scanner.nextInt();
                        String data = DataProcessor.searchRecordsById(records, id);
                        System.out.println(data);
                    } catch (InputMismatchException e) {
                        System.out.println("Ошибка ввода. Пожалуйста, введите целое число");
                    }



                    break;
                case 2:
                    // Поиск записей по части наименования и вывод информации о них.

                    System.out.print("Введите часть наименования для поиска: ");
                    String searchTerm = scanner.nextLine();
                    String data = DataProcessor.searchRecordsByName(records, searchTerm);
                    System.out.println(data);


                    break;
                case 4:
                    // Сохранение текущих записей в JSON файл.
                    System.out.print(" имя файла для сохранения данных: ");
                    String fileName = scanner.nextLine();
                    DataProcessor.saveRecordsToJson(fileName, records);
                     break;
                case   5:
                    // Добавление новой записи. Пользователю предлагается ввести все необходимые детали.
                    int id;
                    System.out.print("Введите идентификатор записи: ");
                    id = scanner.nextInt();

                    System.out.print("Введите наименование записи: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();

                    System.out.print("Введите описание записи: ");
                    String description = scanner.nextLine();

                    System.out.print("Введите ссылку на запись: ");
                    String link = scanner.nextLine();

                    Record newRecord = new Record(id, name, description, link);
                    records.put(id, newRecord);


                    DataProcessor.addRecord(records, id,  name,  description,  link);
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
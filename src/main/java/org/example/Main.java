package org.example;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[]  args) throws IOException {
        Map<Integer, Record> records = new HashMap<>();
        System.out.println("Пожалуйста, укажите путь к файлу в качестве аргумента");

        if (args.length > 0) {
            System.out.println(args[0] );

            DataProcessor.loadRecordsFromFile(args[0], records);
            Scanner scanner = new Scanner(System.in);
            runProcessingLoop.run(scanner, records);
        } else {
            System.out.println("Ошибка");

        }
    }

}
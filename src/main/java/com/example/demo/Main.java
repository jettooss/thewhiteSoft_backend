package com.example.demo;
import com.example.demo.DataProcessor.*;
import com.example.demo.DataProcessor.load;
import com.example.demo.Model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Value("${input.data.path}")
    private String inputDataPath;

    @Autowired
    private Main_Functions mainFunctions;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CommandLineRunner setupData() throws IOException {
        System.out.println(inputDataPath);
        Map<Integer, Record> records = load.loadRecordsFromJson(inputDataPath);
        return args -> {

            RunProcessing.run(records);

        };
    }
}

//        тесты должны проходить через maven (К ПР приложить файл с результатами вызова mvn clean package)






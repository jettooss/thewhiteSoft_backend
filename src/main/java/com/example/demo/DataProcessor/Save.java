package com.example.demo.DataProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.Model.Record;
import org.springframework.beans.factory.annotation.Value;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class Save {
    @Value("${input.test.path}")
    private String inputTestPath;


    public static boolean saveToJson(String name_file , Map<Integer, Record> records) throws IOException {
        String outputFilePath = "src/main/resources/";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Record> recordList = new ArrayList<>(records.values());
        objectMapper.writeValue(new File(outputFilePath+name_file+".json"), recordList);
        System.out.println("Список записей успешно сохранен в JSON файл: " + name_file);
        return new File(outputFilePath+name_file+".json").exists();
    }
}

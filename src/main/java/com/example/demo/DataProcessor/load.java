package com.example.demo.DataProcessor;
import com.example.demo.Model.Record;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class load {
    public static Map<Integer, Record> loadRecordsFromJson(String filePath) throws IOException {
        Map<Integer, Record> records = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File(filePath));
        for (JsonNode recordNode : jsonNode) {
            int id = recordNode.get("id").asInt();
            String name = recordNode.get("name").asText();
            String description = recordNode.get("description").asText();
            String link = recordNode.get("link").asText();

            Record record = new Record(id, name, description, link);
            records.put(id, record);
        }
        return records;
    }
}


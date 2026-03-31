package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDataReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static JsonNode read(String path) {
        try {
            return mapper.readTree(new File(path));
        } catch (IOException e) {
            throw new RuntimeException("Impossible de lire le fichier JSON: " + path, e);
        }
    }
}

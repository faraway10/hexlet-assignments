package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

// BEGIN
public class App {
    static void save(Path filePath, Car car) throws IOException {
        String jsonData = car.serialize();
        Files.writeString(filePath, jsonData);
    }

    static Car extract(Path filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = Files.readString(filePath);
        return mapper.readValue(jsonData, Car.class);
    }
}
// END

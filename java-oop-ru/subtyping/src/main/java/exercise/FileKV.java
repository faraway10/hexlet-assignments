package exercise;

import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    private final String filePath;

    public FileKV(String filePath, Map<String, String> map) {
        this.filePath = filePath;
        writeMapToFile(map);
    }

    public void set(String key, String value) {
        Map<String, String> map = readMapFromFile();
        map.put(key, value);
        writeMapToFile(map);
    }

    public void unset(String key) {
        Map<String, String> map = readMapFromFile();
        map.remove(key);
        writeMapToFile(map);
    }

    public String get(String key, String defaultValue) {
        Map<String, String> map = readMapFromFile();
        return map.getOrDefault(key, defaultValue);
    }

    public Map<String, String> toMap() {
        return readMapFromFile();
    }

    private Map<String, String> readMapFromFile() {
        String data = Utils.readFile(filePath);
        return Utils.unserialize(data);
    }

    private void writeMapToFile(Map<String, String> map) {
        String data = Utils.serialize(map);
        Utils.writeFile(filePath, data);
    }
}
// END

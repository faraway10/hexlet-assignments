package exercise;

import java.util.Map;

// BEGIN
public class App {
    static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> map = storage.toMap();

        for (String key : map.keySet()) {
            storage.unset(key);
        }

        for (String key : map.keySet()) {
            String value = map.get(key);
            storage.set(value, key);
        }
    }
}
// END

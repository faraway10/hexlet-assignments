package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    void fileKVTest() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "10"));
        assertThat(storage.get("key2", "default")).isEqualTo("default");
        assertThat(storage.get("key", "default")).isEqualTo("10");

        storage.set("key2", "value2");
        storage.set("key", "value");

        assertThat(storage.get("key2", "default")).isEqualTo("value2");
        assertThat(storage.get("key", "default")).isEqualTo("value");

        storage.unset("key");
        assertThat(storage.get("key", "def")).isEqualTo("def");
        assertThat(storage.toMap()).isEqualTo(Map.of("key2", "value2"));

    }

    @Test
    void testSwapFileKV() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));
        storage.set("key2", "value2");
        App.swapKeyValue(storage);

        assertThat(storage.get("key3", "default")).isEqualTo("default");
        assertThat(storage.get("value", "")).isEqualTo("key");
        assertThat(storage.get("value2", "")).isEqualTo("key2");
    }

    @Test
    void testSwapFileKV2() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("foo", "bar", "bar", "foo"));
        App.swapKeyValue(storage);
        Map<String, String> expected = Map.of("bar", "foo", "foo", "bar");
        assertThat(storage.toMap()).isEqualTo(expected);
    }
    // END
}

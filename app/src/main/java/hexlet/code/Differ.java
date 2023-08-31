package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(String file1, String file2) throws IOException {
        TypeReference<HashMap<String, Object>> type = new TypeReference<>() { };
        Path p1 = Paths.get(file1).toAbsolutePath();
        Path p2 = Paths.get(file2).toAbsolutePath();

        ObjectMapper mapper = new ObjectMapper();
        final Map<String, Object> before = mapper.readValue(Files.readString(p1), type);
        final Map<String, Object> after = mapper.readValue(Files.readString(p2), type);

        Map<String, Object> combined = new TreeMap<>();
        combined.putAll(before);
        combined.putAll(after);

        StringBuilder result = new StringBuilder();
        result.append("{ \n");
        combined.keySet()
                .forEach(i -> {
                    if (!after.containsKey(i)) {
                        result.append(" - " + i + ": " + before.get(i) + "\n");
                    } else if (after.containsKey(i) && before.containsKey(i)) {
                        if (after.get(i).equals(before.get(i))) {
                            result.append("   " + i + ": " + after.get(i) + "\n");
                        } else {
                            result.append(" - " + i + ": " + before.get(i) + "\n");
                            result.append(" + " + i + ": " + after.get(i) + "\n");
                        }
                    } else if (!before.containsKey(i)) {
                        result.append(" + " + i + ": " + after.get(i) + "\n");
                    }
                });
        result.append("}");

        return result.toString();
    }
}


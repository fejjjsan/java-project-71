package hexlet.code.Utils.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class JsonParser implements Parser {
    @Override
    public Map<String, Object> parse(Path p) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> type = new TypeReference<>() { };
        return mapper.readValue(Files.readString(p), type);
    }
}
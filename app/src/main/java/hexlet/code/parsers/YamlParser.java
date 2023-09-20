package hexlet.code.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class YamlParser implements Parser {

    public Map<String, Object> parse(String str) throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        TypeReference<HashMap<String, Object>> type = new TypeReference<>() { };
        return mapper.readValue(str, type);
    }
}

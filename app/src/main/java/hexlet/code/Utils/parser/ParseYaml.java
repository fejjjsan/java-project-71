package hexlet.code.Utils.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ParseYaml implements Parser {
    @Override
    public Map<String, Object> parse(String path) throws IOException {
        Path p = Paths.get(path).toAbsolutePath();
        ObjectMapper mapper = new YAMLMapper();
        TypeReference<HashMap<String, Object>> type = new TypeReference<>() { };

        return mapper.readValue(Files.readString(p), type);
    }
}

package hexlet.code.Utils.parser;

import java.io.IOException;
import java.util.Map;

public interface Parser {
    Map<String, Object> parse(String path) throws IOException;
}

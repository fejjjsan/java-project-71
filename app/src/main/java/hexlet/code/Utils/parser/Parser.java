package hexlet.code.Utils.parser;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface Parser {
    Map<String, Object> parse(Path p) throws IOException;
}

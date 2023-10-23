package hexlet.code;

import hexlet.code.parsers.Parser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import static hexlet.code.Differ.getExtension;
import static hexlet.code.parsers.ParserFactory.getParser;

public class DataSupplier {
    public static Map<String, Object> getData(String data) throws Exception {
        Path p = Paths.get(data).toAbsolutePath();
        String extension = getExtension(data);
        Parser parser = getParser(extension);
        String content = Files.readString(p);

        return parser.parse(content);
    }
}

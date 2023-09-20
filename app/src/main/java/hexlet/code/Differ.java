package hexlet.code;

import static hexlet.code.DiffBuilder.build;
import static hexlet.code.formatters.FormattersFactory.getFormat;
import static hexlet.code.parsers.ParserFactory.getParser;

import hexlet.code.formatters.DiffFormatter;
import hexlet.code.parsers.Parser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String file1, String file2, String format) throws Exception {
        Path p1 = Paths.get(file1).toAbsolutePath();
        Path p2 = Paths.get(file2).toAbsolutePath();
        String extension = getExtension(file1);
        Parser parser = getParser(extension);

        String jsonString1 = new String(Files.readAllBytes(Paths.get(p1.toString())));
        String jsonString2 = new String(Files.readAllBytes(Paths.get(p2.toString())));
        Map<String, Object> before = parser.parse(jsonString1);
        Map<String, Object> after = parser.parse(jsonString2);

        List<DiffAccumulator> diffs = build(before, after);
        DiffFormatter formatter = getFormat(format);
        return formatter.format(diffs);
    }

    public static String generate(String file1, String file2) throws Exception {
        String format = "stylish";
        return generate(file1, file2, format);
    }

    public static String getExtension(String file) {
        String extension = "";
        int index = file.lastIndexOf(".");
        if (index > 0) {
            extension = file.substring(index + 1);
        }
        return extension;
    }
}


package hexlet.code;


import static hexlet.code.Utils.parser.ParserFactory.getParser;
import hexlet.code.Utils.formatters.DiffFormatter;
import hexlet.code.Utils.formatters.JsonDiffFormatter;
import hexlet.code.Utils.formatters.PlainDiffFormatter;
import hexlet.code.Utils.formatters.StylishDiffFormatter;
import hexlet.code.Utils.parser.Parser;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Objects;

public class Differ {
    public static String generate(String file1, String file2, String format) throws Exception {
        Path p1 = Paths.get(file1).toAbsolutePath();
        Path p2 = Paths.get(file2).toAbsolutePath();

        String extension = getExtension(file1);
        Parser parser = getParser(extension);
        Map<String, Object> before = parser.parse(p1);
        Map<String, Object> after = parser.parse(p2);
        List<DiffAccumulator> diffs = build(before, after);

        DiffFormatter formatter = new StylishDiffFormatter();

        if (format.equals("stylish")) {
            formatter = new StylishDiffFormatter();
        } else if (format.equals("plain")) {
            formatter = new PlainDiffFormatter();
        } else if (format.equals("json")) {
            formatter = new JsonDiffFormatter();
        }

        return formatter.formatDiffs(diffs);
    }

    public static List<DiffAccumulator> build(Map<String, Object> before, Map<String, Object> after) {

        Map<String, Object> combined = new TreeMap<>();
        combined.putAll(before);
        combined.putAll(after);

        List<DiffAccumulator> diffs = new ArrayList<>();
        combined.keySet()
                .forEach(i -> {
                    DiffAccumulator diff;
                    if (!after.containsKey(i)) {
                        diff = new DiffAccumulator(i, "removed", before.get(i));
                        diffs.add(diff);
                    } else if (!before.containsKey(i)) {
                        diff = new DiffAccumulator(i, "added", after.get(i));
                        diffs.add(diff);
                    } else if (after.containsKey(i) && before.containsKey(i)) {
                        if (Objects.equals(after.get(i), before.get(i))) {
                            diff = new DiffAccumulator(i, "unchanged", before.get(i));
                            diffs.add(diff);
                        } else if (!Objects.equals(after.get(i), before.get(i))) {
                            diff = new DiffAccumulator(i, "updated", before.get(i), after.get(i));
                            diffs.add(diff);
                        }
                    }
                });
        return diffs;
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


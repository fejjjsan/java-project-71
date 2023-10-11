package hexlet.code;

import static hexlet.code.DataSupplier.getData;
import static hexlet.code.DiffBuilder.build;
import static hexlet.code.formatters.FormattersFactory.getFormat;

import hexlet.code.formatters.DiffFormatter;

import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(String file1, String file2, String format) throws Exception {
        Map<String, Object> before = getData(file1);
        Map<String, Object> after = getData(file2);

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


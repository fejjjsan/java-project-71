package hexlet.code;

import static hexlet.code.DataSupplier.getData;
import static hexlet.code.DiffBuilder.build;
import static hexlet.code.formatters.FormattersFactory.getFormat;

import hexlet.code.formatters.DiffFormatter;

import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String data1, String data2, String format) throws Exception {
        Map<String, Object> before = getData(data1);
        Map<String, Object> after = getData(data2);

        List<DiffAccumulator> diffs = build(before, after);
        DiffFormatter formatter = getFormat(format);
        return formatter.format(diffs);
    }

    public static String generate(String data1, String data2) throws Exception {
        String format = "stylish";
        return generate(data1, data2, format);
    }

    public static String getExtension(String data) {
        String extension = "";
        int index = data.lastIndexOf(".");
        if (index > 0) {
            extension = data.substring(index + 1);
        }
        return extension;
    }
}


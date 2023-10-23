package hexlet.code.formatters;

import hexlet.code.DiffAccumulator;

import java.util.List;
import java.util.Map;

public final class PlainDiffFormatter implements DiffFormatter {
    public String format(List<DiffAccumulator> diffs) {
        StringBuilder result = new StringBuilder();
        int counter = 0;

        for (DiffAccumulator diff : diffs) {
            switch (diff.getStatus()) {
                case "removed" -> {
                    result.append("Property '");
                    result.append(diff.getKey());
                    result.append("' was ");
                    result.append(diff.getStatus());
                }
                case "added" -> {
                    result.append("Property '");
                    result.append(diff.getKey());
                    result.append("' was ");
                    result.append(diff.getStatus());
                    result.append(" with value: ");
                    result.append(stringify(diff.getValueBefore()));
                }
                case "updated" -> {
                    result.append("Property '");
                    result.append(diff.getKey());
                    result.append("' was ");
                    result.append(diff.getStatus());
                    result.append(".");
                    result.append(" From ");
                    result.append(stringify(diff.getValueBefore()));
                    result.append(" to ");
                    result.append(stringify(diff.getValueAfter()));
                }
                case "unchanged" -> {
                    // do nothing when status is unchanged
                }
                default -> throw new IllegalArgumentException("Unexpected value in switch: " + diff.getStatus());
            }

            if (counter < diffs.size() - 1 && !diff.getStatus().equals("unchanged")) {
                result.append("\n");
            }
            counter++;
        }

        return result.toString();
    }

    public static String stringify(Object value) {

        if (value == null) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }

        return value.toString();
    }
}

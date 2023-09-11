package hexlet.code.Utils.formatters;

import hexlet.code.DiffAccumulator;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatStylish(List<DiffAccumulator> diffs) {
        StringBuilder result = new StringBuilder();

        result.append("{ \n");
        diffs.forEach(i -> {
            if (i.getStatus().equals("removed")) {
                result.append(" - " + i.getKey() + ": " + i.getValue() + "\n");
            } else if (i.getStatus().equals(("unchanged"))) {
                result.append("   " + i.getKey() + ": " + i.getValue() + "\n");
            } else if (i.getStatus().equals("updated")) {
                result.append(" - " + i.getKey() + ": " + i.getValueBefore() + "\n");
                result.append(" + " + i.getKey() + ": " + i.getValueAfter() + "\n");
            } else if (i.getStatus().equals("added")) {
                result.append(" + " + i.getKey() + ": " + i.getValue() + "\n");
            }
        });
        result.append("}");

        return result.toString();
    }

    public static String formatPlain(List<DiffAccumulator> diffs) {
        StringBuilder result = new StringBuilder();

        diffs.forEach(i -> {
            if (i.getStatus().equals("removed")) {
                result.append("Property '");
                result.append(i.getKey());
                result.append("' was ");
                result.append(i.getStatus());
                result.append("\n");
            } else if (i.getStatus().equals("updated")) {
                result.append("Property '");
                result.append(i.getKey());
                result.append("' was ");
                result.append(i.getStatus());
                result.append(".");
                result.append(" From ");
                if (i.getValueBefore() != null && i.getValueBefore() instanceof Collection<?>) {
                    result.append("[complex value]");
                } else if (i.getValueBefore() instanceof String) {
                    result.append("'");
                    result.append(i.getValueBefore());
                    result.append("'");
                } else {
                    result.append(i.getValueBefore());
                }
                result.append(" to ");
                if (i.getValueAfter() != null && i.getValueAfter() instanceof Collection<?>) {
                    result.append("[complex value]");
                } else if (i.getValueAfter() instanceof String) {
                    result.append("'");
                    result.append(i.getValueAfter());
                    result.append("'");
                } else {
                    result.append(i.getValueAfter());
                }
                result.append("\n");
            } else if (i.getStatus().equals("added")) {
                result.append("Property '");
                result.append(i.getKey());
                result.append("' was ");
                result.append(i.getStatus());
                result.append(" with value: ");
                if (i.getValue() != null) {
                    if (i.getValue() instanceof Map<?, ?> || i.getValue() instanceof Collection<?>) {
                        result.append("[complex value]");
                    } else if (i.getValue() instanceof String) {
                        result.append("'");
                        result.append(i.getValue());
                        result.append("'");
                    }
                } else {
                    result.append(i.getValue());
                }
                result.append("\n");
            }
        });

        return result.toString();
    }
}

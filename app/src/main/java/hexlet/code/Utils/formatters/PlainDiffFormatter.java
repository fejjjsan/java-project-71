package hexlet.code.Utils.formatters;

import hexlet.code.DiffAccumulator;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PlainDiffFormatter implements DiffFormatter {
    public String formatDiffs(List<DiffAccumulator> diffs) {
        StringBuilder result = new StringBuilder();

        diffs.forEach(i -> {
            switch (i.getStatus()) {
                case "removed" -> {
                    result.append("Property '");
                    result.append(i.getKey());
                    result.append("' was ");
                    result.append(i.getStatus());
                    result.append("\n");
                }
                case "added" -> {
                    result.append("Property '");
                    result.append(i.getKey());
                    result.append("' was ");
                    result.append(i.getStatus());
                    result.append(" with value: ");
                    if (i.getValue() instanceof List<?> || i.getValue() instanceof Map<?, ?>) {
                        result.append("[complex value]");
                    } else if (i.getValue() instanceof String) {
                        result.append("'");
                        result.append(i.getValue());
                        result.append("'");
                    } else {
                        result.append(i.getValue());
                    }
                    result.append("\n");
                }
                case "updated" -> {
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
                }
                default -> {
                    // skips when status "unchanged";
                }
            }
        });

        return result.toString();
    }
}

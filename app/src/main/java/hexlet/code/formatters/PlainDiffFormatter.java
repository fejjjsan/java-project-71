package hexlet.code.formatters;

import hexlet.code.DiffAccumulator;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class PlainDiffFormatter implements DiffFormatter {
    public String format(List<DiffAccumulator> diffs) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < diffs.size(); i++) {
            var o = diffs.get(i);
            switch (o.getStatus()) {
                case "removed" -> {
                    result.append("Property '");
                    result.append(o.getKey());
                    result.append("' was ");
                    result.append(o.getStatus());
                }
                case "added" -> {
                    result.append("Property '");
                    result.append(o.getKey());
                    result.append("' was ");
                    result.append(o.getStatus());
                    result.append(" with value: ");
                    if (o.getValue() instanceof List<?> || o.getValue() instanceof Map<?, ?>) {
                        result.append("[complex value]");
                    } else if (o.getValue() instanceof String) {
                        result.append("'");
                        result.append(o.getValue());
                        result.append("'");
                    } else {
                        result.append(o.getValue());
                    }
                }
                case "updated" -> {
                    result.append("Property '");
                    result.append(o.getKey());
                    result.append("' was ");
                    result.append(o.getStatus());
                    result.append(".");
                    result.append(" From ");
                    if (o.getValueBefore() != null && o.getValueBefore() instanceof Collection<?>) {
                        result.append("[complex value]");
                    } else if (o.getValueBefore() instanceof String) {
                        result.append("'");
                        result.append(o.getValueBefore());
                        result.append("'");
                    } else {
                        result.append(o.getValueBefore());
                    }
                    result.append(" to ");
                    if (o.getValueAfter() != null && o.getValueAfter() instanceof Collection<?>) {
                        result.append("[complex value]");
                    } else if (o.getValueAfter() instanceof String) {
                        result.append("'");
                        result.append(o.getValueAfter());
                        result.append("'");
                    } else {
                        result.append(o.getValueAfter());
                    }
                }
                case "unchanged" -> {
                    // do nothing when status is unchanged
                }
                default -> throw new IllegalArgumentException("Unexpected value in switch: " + o.getStatus());
            }

            if (i < diffs.size() - 1 && !o.getStatus().equals("unchanged")) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}

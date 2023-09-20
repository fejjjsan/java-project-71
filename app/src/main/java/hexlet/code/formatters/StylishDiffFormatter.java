package hexlet.code.formatters;

import hexlet.code.DiffAccumulator;
import java.util.List;

public final class StylishDiffFormatter implements DiffFormatter {
    public String format(List<DiffAccumulator> diffs) {
        StringBuilder result = new StringBuilder();

        result.append("{\n");
        diffs.forEach(i -> {
            switch (i.getStatus()) {
                case "removed" -> {
                    result.append("  - ");
                    result.append(i.getKey());
                    result.append(": ");
                    result.append(i.getValue());
                    result.append("\n");
                }
                case "added" -> {
                    result.append("  + ");
                    result.append(i.getKey());
                    result.append(": ");
                    result.append(i.getValue());
                    result.append("\n");
                }
                case "updated" -> {
                    result.append("  - ");
                    result.append(i.getKey());
                    result.append(": ");
                    result.append(i.getValueBefore());
                    result.append("\n");
                    result.append("  + ");
                    result.append(i.getKey());
                    result.append(": ");
                    result.append(i.getValueAfter());
                    result.append("\n");
                }
                case "unchanged" -> {
                    result.append("    ");
                    result.append(i.getKey());
                    result.append(": ");
                    result.append(i.getValue());
                    result.append("\n");
                }
                default -> throw new RuntimeException("Unexpected value in switch: " + i.getStatus());
            }
        });
        result.append("}");

        return result.toString();
    }
}

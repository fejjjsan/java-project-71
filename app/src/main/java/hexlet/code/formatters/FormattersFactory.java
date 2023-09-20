package hexlet.code.formatters;

public class FormattersFactory {
    public static DiffFormatter getFormat(String format) {
        return switch (format) {
            case "stylish" -> new StylishDiffFormatter();
            case "plain" -> new PlainDiffFormatter();
            case "json" -> new JsonDiffFormatter();
            default -> throw new RuntimeException("Unexpected value in switch: " + format);
        };
    }
}

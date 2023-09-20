package hexlet.code.parsers;

public class ParserFactory {
    public static Parser getParser(String extension) {
        return switch (extension) {
            case "json" -> new JsonParser();
            case "yml" -> new YamlParser();
            default -> throw new RuntimeException("Error: wrong extension provided " + extension);
        };
    }
}

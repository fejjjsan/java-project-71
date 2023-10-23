package hexlet.code.parsers;

public class ParserFactory {
    public static Parser getParser(String data) {
        return switch (data) {
            case "json" -> new JsonParser();
            case "yml" -> new YmlParser();
            default -> throw new RuntimeException();
        };
    }
}

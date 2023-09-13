package hexlet.code.Utils.parser;

public class ParserFactory {
    public static Parser getParser(String extension) {
        Parser parser = new JsonParser();

        if (extension.equals("json")) {
            return parser;
        } else if (extension.equals("yml")) {
            parser = new YamlParser();
        }
        return parser;
    };

}

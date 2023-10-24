package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;

class AppTest {

    private static String jsonNested1;
    private static String jsonNested2;
    private static String ymlNested1;
    private static String ymlNested2;
    private static Path stylishTest;
    private static Path plainTest;
    private static Path jsonTest;
    private static String plainFormat;
    private static String stylishFormat;
    private static String jsonFormat;

    public static Path getAbsolutPathToFile(String str) {
        Path path = Paths.get("src", "test", "resources", str);
        return path.toAbsolutePath();
    }

    @BeforeAll
    static void beforeAll() {
        jsonNested1 = getAbsolutPathToFile("jsonNested1.json").toString();
        jsonNested2 = getAbsolutPathToFile("jsonNested2.json").toString();
        ymlNested1 = getAbsolutPathToFile("ymlNested1.yml").toString();
        ymlNested2 = getAbsolutPathToFile("ymlNested2.yml").toString();
        stylishTest = getAbsolutPathToFile("stylishTest.txt");
        plainTest = getAbsolutPathToFile("plainTest.txt");
        jsonTest = getAbsolutPathToFile("jsonTest.txt");
        plainFormat = "plain";
        stylishFormat = "stylish";
        jsonFormat = "json";
    }

    @Test
    public void testGenerateDefaultJson() throws Exception {
        String actual = generate(jsonNested1, jsonNested2);
        String expected = Files.readString(stylishTest);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateDefaultForYml() throws Exception {
        String actual = generate(ymlNested1, ymlNested2);
        String expected = Files.readString(stylishTest);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateStylishForJson() throws Exception {
        String actual = generate(jsonNested1, jsonNested2, stylishFormat);
        String expected = Files.readString(stylishTest);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGeneratePlainForJson() throws Exception {
        String actual = generate(jsonNested1, jsonNested2, plainFormat);
        String expected = Files.readString(plainTest);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateStylishForYml() throws Exception {
        String actual = generate(ymlNested1, ymlNested2, stylishFormat);
        String expected = Files.readString(stylishTest);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGeneratePlainForYml() throws Exception {
        String actual = generate(ymlNested1, ymlNested2, plainFormat);
        String expected = Files.readString(plainTest);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateJson() throws Exception {
        String actual = generate(jsonNested1, jsonNested2, jsonFormat);
        String expected = Files.readString(jsonTest);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateJsonForYml() throws Exception {
        String actual = generate(ymlNested1, ymlNested2, jsonFormat);
        String expected = Files.readString(jsonTest);
        assertThat(actual).isEqualTo(expected);
    }
}

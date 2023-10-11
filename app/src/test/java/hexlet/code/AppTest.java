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
    private static String yamlNested1;
    private static String yamlNested2;
    private static Path stylishTest;
    private static Path plainTest;
    private static String plainFormat;

    public static Path getAbsolutPathToFile(String file) {
        Path path = Paths.get("src", "test", "resources", file);
        return path.toAbsolutePath();
    }

    @BeforeAll
    static void beforeAll() {
        jsonNested1 = getAbsolutPathToFile("jsonNested1.json").toString();
        jsonNested2 = getAbsolutPathToFile("jsonNested2.json").toString();
        yamlNested1 = getAbsolutPathToFile("yamlNested1.yml").toString();
        yamlNested2 = getAbsolutPathToFile("yamlNested2.yml").toString();
        stylishTest = getAbsolutPathToFile("stylishTest.txt");
        plainTest = getAbsolutPathToFile("plainTest.txt");
        plainFormat = "plain";
    }

    @Test
    public void testGenerateJsonNestedStylish() throws Exception {
        String actual = generate(jsonNested1, jsonNested2);
        String expected = Files.readString(stylishTest);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateYamlNestedStylish() throws Exception {
        String actual = generate(yamlNested1, yamlNested2);
        String expected = Files.readString(stylishTest);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateJsonNestedPlain() throws Exception {
        String actual = generate(jsonNested1, jsonNested2, plainFormat);
        String expected = Files.readString(plainTest);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateYamlNestedPlain() throws Exception {
        String actual = generate(yamlNested1, yamlNested2, plainFormat);
        String expected = Files.readString(plainTest);
        assertThat(actual).isEqualTo(expected);
    }
}

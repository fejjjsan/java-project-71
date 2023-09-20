package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static hexlet.code.Differ.generate;

class AppTest {
    private String absolutePath;
    @BeforeEach
    void beforeEach() {
        Path path = Paths.get("src", "test", "resources");
        absolutePath = path.toAbsolutePath().toString();
    }

    @Test
    public void testGenerateJsonNestedStylish() throws Exception {
        String filePath1 = absolutePath + "/jsonNested1.json";
        String filePath2 = absolutePath + "/jsonNested2.json";
        String stylishTest = absolutePath + "/stylishTest.txt";
        String actual = generate(filePath1, filePath2);
        String expected = new String(Files.readAllBytes(Paths.get(stylishTest)));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateYamlNestedStylish() throws Exception {
        String filePath1 = absolutePath + "/yamlNested1.yml";
        String filePath2 = absolutePath + "/yamlNested2.yml";
        String stylishTest = absolutePath + "/stylishTest.txt";
        String actual = generate(filePath1, filePath2);
        String expected = new String(Files.readAllBytes(Paths.get(stylishTest)));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateJsonNestedPlain() throws Exception {
        String filePath1 = absolutePath + "/jsonNested1.json";
        String filePath2 = absolutePath + "/jsonNested2.json";
        String plainTest = absolutePath + "/plainTest.txt";
        String format = "plain";
        String actual = generate(filePath1, filePath2, format);
        String expected = new String(Files.readAllBytes(Paths.get(plainTest)));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateYamlNestedPlain() throws Exception {
        String filePath1 = absolutePath + "/yamlNested1.yml";
        String filePath2 = absolutePath + "/yamlNested2.yml";
        String plainTest = absolutePath + "/plainTest.txt";
        String format = "plain";
        String actual = generate(filePath1, filePath2, format);
        String expected = new String(Files.readAllBytes(Paths.get(plainTest)));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateFormatJson() throws Exception {
        String filePath1 = absolutePath + "/file1.json";
        String filePath2 = absolutePath + "/file2.json";
        String format = "json";
        String actual = generate(filePath1, filePath2, format);
        String expected = """
                {
                  "follow" : false,
                  "host" : "hexlet.io",
                  "proxy" : "123.234.53.22",
                  "timeout" : [ 50, 20 ],
                  "verbose" : true
                }""";
        assertThat(actual).isEqualTo(expected);
    }
}

package hexlet.code;

import hexlet.code.Utils.parser.ParseJson;
import hexlet.code.Utils.parser.ParseYaml;
import hexlet.code.Utils.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hexlet.code.Utils.formatters.Formatter.formatPlain;
import static org.assertj.core.api.Assertions.assertThat;
import static hexlet.code.Utils.formatters.Formatter.formatStylish;
import java.io.File;

import static hexlet.code.DiffGenerator.generate;

class AppTest {
    String absolutePath;
    @BeforeEach
    void beforeEach() {
        String path = "src/test/resources";
        File file = new File(path);
        absolutePath = file.getAbsolutePath();
    }

    @Test
    public void testGenerateJsonStylish() throws Exception {
        String filePath1 = absolutePath + "/file1.json";
        String filePath2 = absolutePath + "/file2.json";
        Parser json = new ParseJson();
        String actual = formatStylish(generate(filePath1, filePath2, json));
        String expected = """
                {\s
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                 + verbose: true
                }""";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateYamlStylish() throws Exception {
        String filePath1 = absolutePath + "/file1.yml";
        String filePath2 = absolutePath + "/file2.yml";
        Parser yaml = new ParseYaml();
        String actual = formatStylish(generate(filePath1, filePath2, yaml));
        String expected = """
                {\s
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                 + verbose: true
                }""";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateJsonNestedStylish() throws Exception {
        String filePath1 = absolutePath + "/jsonNested1.json";
        String filePath2 = absolutePath + "/jsonNested2.json";
        Parser json = new ParseJson();
        String actual = formatStylish(generate(filePath1, filePath2, json));
        String expected = """
                {\s
                   chars1: [a, b, c]
                 - chars2: [d, e, f]
                 + chars2: false
                 - checked: false
                 + checked: true
                 - default: null
                 + default: [value1, value2]
                 - id: 45
                 + id: null
                 - key1: value1
                 + key2: value2
                   numbers1: [1, 2, 3, 4]
                 - numbers2: [2, 3, 4, 5]
                 + numbers2: [22, 33, 44, 55]
                 - numbers3: [3, 4, 5]
                 + numbers4: [4, 5, 6]
                 + obj1: {nestedKey=value, isNested=true}
                 - setting1: Some value
                 + setting1: Another value
                 - setting2: 200
                 + setting2: 300
                 - setting3: true
                 + setting3: none
                }""";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateYamlNestedStylish() throws Exception {
        String filePath1 = absolutePath + "/yamlNested1.yml";
        String filePath2 = absolutePath + "/yamlNested2.yml";
        Parser json = new ParseYaml();
        String actual = formatStylish(generate(filePath1, filePath2, json));
        String expected = """
                {\s
                   chars1: [a, b, c]
                 - chars2: [d, e, f]
                 + chars2: false
                 - checked: false
                 + checked: true
                 - default: null
                 + default: [value1, value2]
                 - id: 45
                 + id: null
                 - key1: value1
                 + key2: value2
                   numbers1: [1, 2, 3, 4]
                 - numbers2: [2, 3, 4, 5]
                 + numbers2: [22, 33, 44, 55]
                 - numbers3: [3, 4, 5]
                 + numbers4: [4, 5, 6]
                 + obj1: {nestedKey=value, isNested=true}
                 - setting1: Some value
                 + setting1: Another value
                 - setting2: 200
                 + setting2: 300
                 - setting3: true
                 + setting3: none
                }""";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateJsonNestedPlain() throws Exception {
        String filePath1 = absolutePath + "/jsonNested1.json";
        String filePath2 = absolutePath + "/jsonNested2.json";
        Parser json = new ParseYaml();
        String actual = formatPlain(generate(filePath1, filePath2, json));
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;
        assertThat(actual).isEqualTo(expected);
    }

}

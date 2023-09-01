package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.File;
import static hexlet.code.Differ.generate;

class AppTest {
    String absolutePath;
    @BeforeEach
    void beforeEach() {
        String path = "src/test/resources";
        File file = new File(path);
        absolutePath = file.getAbsolutePath();
    }

    @Test
    public void testGenerateOrder() throws Exception {
        String filePath1 = absolutePath + "/testOrderFile1.json";
        String filePath2 = absolutePath + "/testOrderFile2.json";
        String actual = generate(filePath1, filePath2);
        String expected = """
                {\s
                   follow: false
                   host: hexlet.io
                   proxy: 123.234.53.22
                   timeout: 50
                }""";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateValueChanged() throws Exception {
        String filePath1 = absolutePath + "/testValueChangedFile1.json";
        String filePath2 = absolutePath + "/testValueChangedFile2.json";
        String actual = generate(filePath1, filePath2);
        String expected = """
                {\s
                 - follow: true
                 + follow: false
                 - timeout: 80
                 + timeout: 50
                }""";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateKeyDeleted() throws Exception {
        String filePath1 = absolutePath + "/testKeyDeletedFile1.json";
        String filePath2 = absolutePath + "/testKeyDeletedFile2.json";
        String actual = generate(filePath1, filePath2);
        String expected = """
                {\s
                   follow: false
                 - host: hexlet.io
                   proxy: 123.234.53.22
                   timeout: 50
                }""";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGenerateKeyAdded() throws Exception {
        String filePath1 = absolutePath + "/testKeyAddedFile1.json";
        String filePath2 = absolutePath + "/testKeyAddedFile2.json";
        String actual = generate(filePath1, filePath2);
        String expected = """
                {\s
                   follow: false
                 + host: hexlet.io
                   proxy: 123.234.53.22
                   timeout: 50
                }""";
        assertThat(actual).isEqualTo(expected);
    }

}

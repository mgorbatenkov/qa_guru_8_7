package guru.qa;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesTests {
    @Test
    void txtTest() throws Exception{
        String result;
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("files/sample.txt")){
            result = new String(is.readAllBytes(), "UTF-8");
        }
        assertThat(result).contains("memoria torquentur");
    }
}

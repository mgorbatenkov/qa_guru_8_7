package guru.qa;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesTests {
    @Test
    void txtTest() throws Exception {
        String result;
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("files/sample.txt")) {
            result = new String(is.readAllBytes(), "UTF-8");
        }
        assertThat(result).contains("memoria torquentur");
    }

    @Test
    void pdfTest() throws Exception {
        PDF parsed = new PDF(getClass().getClassLoader().getResource("files/sample.pdf"));
        assertThat(parsed.numberOfPages).isGreaterThan(3);
        System.out.println();
    }
}

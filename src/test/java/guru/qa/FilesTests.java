package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import net.lingala.zip4j.ZipFile;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
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
    }

    @Test
    void xlsxTest() throws Exception {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("files/sample.xlsx")) {
            XLS parsed = new XLS(stream);
            assertThat(parsed.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue())
                    .isEqualTo("Dulce");
        }
    }

    @Test
    void zipWithPasswordTest() throws Exception {
        ZipFile zipFile = new ZipFile("src/test/resources/files/sample.zip", "qwerty".toCharArray());
        try (InputStream is = zipFile.getInputStream(zipFile.getFileHeader("test.txt"))) {
            String result = new String(is.readAllBytes(), "UTF-8");
            assertThat(result).contains("Testing");
        }
    }

    @Test
    void docTest() throws Exception {
        try (FileInputStream fis = new FileInputStream("src/test/resources/files/sample.docx")) {
            XWPFDocument document = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(document);
            assertThat(extractor.getText()).contains("velit vitae libero");
        }
    }
}

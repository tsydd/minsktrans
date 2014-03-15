package by.tsyd.minsktrans.util;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitry Tsydzik
 * @since Date: 27.02.14.
 */
public class ScannerLineIteratorTest {

    @Test
    public void test() throws Exception {
        try (InputStream inputStream = new ByteArrayInputStream("line1\nline2\nline3".getBytes());
             Scanner scanner = new Scanner(inputStream)) {
            assertThat(new ScannerLineIterator(scanner)).containsExactly("line1", "line2", "line3");
        }
    }
}

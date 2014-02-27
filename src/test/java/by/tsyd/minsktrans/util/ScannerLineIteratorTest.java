package by.tsyd.minsktrans.util;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * @author Dmitry Tsydzik
 * @since Date: 27.02.14.
 */
public class ScannerLineIteratorTest {

    @Test
    public void test() throws Exception {
        try (InputStream inputStream = new ByteArrayInputStream("line1\nline2\nline3".getBytes());
             Scanner scanner = new Scanner(inputStream)) {
            Iterator<String> iterator = new ScannerLineIterator(scanner);
            assertEquals("line1", iterator.next());
            assertEquals("line2", iterator.next());
            assertEquals("line3", iterator.next());
            assertFalse(iterator.hasNext());
        }
    }
}

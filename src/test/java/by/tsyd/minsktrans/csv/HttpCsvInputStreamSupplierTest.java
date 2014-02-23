package by.tsyd.minsktrans.csv;

import by.tsyd.minsktrans.MinskTransHttpConstants;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.02.14
 */
public class HttpCsvInputStreamSupplierTest {

    @Test
    public void test() throws Exception {
        Supplier<InputStream> inputStreamSupplier = new HttpCsvInputStreamSupplier(MinskTransHttpConstants.DEFAULT_TIMES_URL);
        try (InputStream is = inputStreamSupplier.get();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}

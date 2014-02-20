package by.tsyd.minsktrans.csv;

import by.tsyd.minsktrans.MinskTransHttpConstants;
import by.tsyd.minsktrans.ResourceProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.02.14
 */
public class MinskTransHttpCsvProviderTest {

    @Test
    public void test() throws Exception {
        ResourceProvider resourceProvider = new MinskTransHttpCsvProvider(MinskTransHttpConstants.DEFAULT_ROUTES_URL);
        try (InputStreamReader isr = resourceProvider.getResource();
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}

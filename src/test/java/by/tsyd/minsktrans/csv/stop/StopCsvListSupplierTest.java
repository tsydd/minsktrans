package by.tsyd.minsktrans.csv.stop;

import by.tsyd.minsktrans.MinskTransHttpConstants;
import by.tsyd.minsktrans.ResourceProvider;
import by.tsyd.minsktrans.StaticProvider;
import by.tsyd.minsktrans.csv.MinskTransHttpCsvProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * @author Dmitry Tsydzik
 * @since Date: 20.02.14.
 */
public class StopCsvListSupplierTest {
    @Test(
            dataProvider = StaticProvider.FILE_STOP_CSV_PROVIDER,
            dataProviderClass = StaticProvider.class
    )
    public void testFileOpenCsv(Supplier<List<StopCsv>> csvStopProvider) throws Exception {
        List<StopCsv> stops = csvStopProvider.get();
        assertEquals(2797, stops.size());

        StopCsv stopCsv = stops.get(1);
        assertEquals("16100", stopCsv.getId());
        assertEquals("0", stopCsv.getCity());
        assertEquals("0", stopCsv.getArea());
        assertEquals("0", stopCsv.getStreet());
        assertEquals("", stopCsv.getInfo());
        assertEquals("13-й км", stopCsv.getName());
        assertEquals("2750898", stopCsv.getLongitude());
        assertEquals("5398767", stopCsv.getLatitude());
        assertEquals("16101,16099", stopCsv.getStops());
        assertEquals("s16100", stopCsv.getStopNum());
    }

    @Test
    public void testHttpOpenCsv() throws Exception {
        ResourceProvider resourceProvider = new MinskTransHttpCsvProvider(MinskTransHttpConstants.DEFAULT_STOPS_URL);
        Supplier<List<StopCsv>> csvStopProvider = new StopCsvListSupplier(resourceProvider);

        List<StopCsv> stops = csvStopProvider.get();
        assertFalse(stops.isEmpty());
    }

}

package by.tsyd.minsktrans.csv.stop;

import by.tsyd.minsktrans.MinskTransHttpConstants;
import by.tsyd.minsktrans.StaticProvider;
import by.tsyd.minsktrans.csv.HttpCsvInputStreamSupplier;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * @author Dmitry Tsydzik
 * @since Date: 20.02.14.
 */
public class StopCsvListSupplierTest {
    @Test
    public void testFileOpenCsv() throws Exception {
        Supplier<List<StopCsv>> csvStopProvider  = StaticProvider.getStopCsvListFromFileSupplier();
        List<StopCsv> stops = csvStopProvider.get();
        assertEquals(2802, stops.size());

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
        Supplier<InputStream> inputStreamSupplier = new HttpCsvInputStreamSupplier(MinskTransHttpConstants.DEFAULT_STOPS_URL);
        Supplier<List<StopCsv>> stopCsvListSupplier = new StopCsvListSupplier(inputStreamSupplier);

        List<StopCsv> stops = stopCsvListSupplier.get();
        assertFalse(stops.isEmpty());
    }

}

package by.tsyd.minsktrans.service.stop;

import by.tsyd.minsktrans.csv.stop.StopCsv;
import by.tsyd.minsktrans.domain.Stop;
import com.google.common.collect.Sets;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toSet;
import static org.testng.Assert.assertEquals;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.02.14.
 */
public class CsvBasedStopListSupplierTest {

    @Test
    public void testConversion() throws Exception {
        StopCsv stopCsv = new StopCsv();
        stopCsv.setId("1");
        stopCsv.setLatitude("1234567");
        stopCsv.setLongitude("7654321");
        stopCsv.setName("name");
        stopCsv.setStops("");

        Supplier<List<Stop>> stopListSupplier = new CsvBasedStopListSupplier(() -> Arrays.asList(stopCsv));
        Stop stop = stopListSupplier.get().get(0);

        assertEquals(1, stop.getId().longValue());
        assertEquals(stopCsv.getName(), stop.getName());
        assertEquals(0, new BigDecimal("12.34567").compareTo(stop.getLatitude()));
        assertEquals(0, new BigDecimal("76.54321").compareTo(stop.getLongitude()));
    }

    @Test
    public void testPropagation() throws Exception {
        StopCsv stopCsv1 = new StopCsv();
        stopCsv1.setId("1");
        stopCsv1.setLatitude("0");
        stopCsv1.setLongitude("0");
        stopCsv1.setName("name");
        stopCsv1.setStops("");

        StopCsv stopCsv2 = new StopCsv();
        stopCsv2.setLatitude("0");
        stopCsv2.setLongitude("0");
        stopCsv2.setId("2");
        stopCsv2.setStops("");

        Supplier<List<Stop>> stopListSupplier = new CsvBasedStopListSupplier(() -> Arrays.asList(stopCsv1, stopCsv2));
        Stop stop2 = stopListSupplier.get().get(1);

        assertEquals(stopCsv1.getName(), stop2.getName());
    }

    @Test
    public void testRelatedStops() throws Exception {
        StopCsv stopCsv1 = new StopCsv();
        stopCsv1.setId("1");
        stopCsv1.setLatitude("0");
        stopCsv1.setLongitude("0");
        stopCsv1.setStops("");

        StopCsv stopCsv2 = new StopCsv();
        stopCsv2.setId("2");
        stopCsv2.setLatitude("0");
        stopCsv2.setLongitude("0");
        stopCsv2.setStops("");

        StopCsv stopCsv3 = new StopCsv();
        stopCsv3.setId("3");
        stopCsv3.setLatitude("0");
        stopCsv3.setLongitude("0");
        stopCsv3.setStops("1,2,3");

        Supplier<List<Stop>> stopListSupplier = new CsvBasedStopListSupplier(() -> Arrays.asList(stopCsv1, stopCsv2, stopCsv3));
        Stop stop3 = stopListSupplier.get().get(2);

        Set<Long> stopIds = stop3.getStops().stream()
                .map(Stop::getId)
                .collect(toSet());
        assertEquals(Sets.newHashSet(1L, 2L, 3L), stopIds);
    }
}

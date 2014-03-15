package by.tsyd.minsktrans.csv.stop;

import by.tsyd.minsktrans.integration.AbstractLocalResourcesIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitry Tsydzik
 * @since Date: 20.02.14.
 */
public class StopCsvListSupplierIntegrationTest extends AbstractLocalResourcesIntegrationTest {

    @Autowired
    private Supplier<List<StopCsv>> stopCsvListSupplier;

    @Test
    public void testFileOpenCsv() throws Exception {
        List<StopCsv> stops = stopCsvListSupplier.get();
        assertThat(stops).hasSize(2802);

        StopCsv stopCsv = stops.get(1);
        assertThat(stopCsv.getId()).isEqualTo("16100");
        assertThat(stopCsv.getCity()).isEqualTo("0");
        assertThat(stopCsv.getArea()).isEqualTo("0");
        assertThat(stopCsv.getStreet()).isEqualTo("0");
        assertThat(stopCsv.getInfo()).isEqualTo("");
        assertThat(stopCsv.getName()).isEqualTo("13-й км");
        assertThat(stopCsv.getLongitude()).isEqualTo("2750898");
        assertThat(stopCsv.getLatitude()).isEqualTo("5398767");
        assertThat(stopCsv.getStops()).isEqualTo("16101,16099");
        assertThat(stopCsv.getStopNum()).isEqualTo("s16100");
    }
}

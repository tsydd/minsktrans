package by.tsyd.minsktrans.csv.route;

import by.tsyd.minsktrans.integration.AbstractLocalResourcesIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.02.14.
 */
public class RouteCsvListSupplierIntegrationTest extends AbstractLocalResourcesIntegrationTest {

    @Autowired
    private Supplier<List<RouteCsv>> routeCsvListSupplier;

    public void test() throws Exception {
        List<RouteCsv> routes = routeCsvListSupplier.get();
        assertThat(routes).hasSize(881);

        RouteCsv routeCsv = routes.get(0);
        assertThat(routeCsv.getRouteNumber()).isEqualTo("д");
        assertThat(routeCsv.getAuthority()).isEqualTo("minsk");
        assertThat(routeCsv.getCity()).isEqualTo("minsk");
        assertThat(routeCsv.getTransport()).isEqualTo("bus");
        assertThat(routeCsv.getOperator()).isEqualTo("7 АП");
        assertThat(routeCsv.getValidityPeriods()).isEqualTo("16041,");
        assertThat(routeCsv.getSpecialDates()).isEqualTo("0");
        assertThat(routeCsv.getRouteTag()).isEqualTo("");
        assertThat(routeCsv.getRouteType()).isEqualTo("A>B");
        assertThat(routeCsv.getCommercial()).isEqualTo("A");
        assertThat(routeCsv.getRouteName()).isEqualTo("Малышок (Уручье-2)");
        assertThat(routeCsv.getWeekDays()).isEqualTo("12345");
        assertThat(routeCsv.getRouteId()).isEqualTo("112532");
        assertThat(routeCsv.getEntry()).isEqualTo("");
        assertThat(routeCsv.getRouteStops()).isEqualTo("14386,14384,14382,14648,14646,14644,14642,14376,14373,14371,14370,14368,69144");
        assertThat(routeCsv.getDateStart()).isEqualTo("d1-d5 - 02.12.2013");
    }
}

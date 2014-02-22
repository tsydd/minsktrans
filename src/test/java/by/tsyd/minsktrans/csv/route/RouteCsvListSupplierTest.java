package by.tsyd.minsktrans.csv.route;

import by.tsyd.minsktrans.StaticProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Supplier;

import static org.testng.Assert.assertEquals;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.02.14.
 */
public class RouteCsvListSupplierTest {

    @Test(
            dataProvider = StaticProvider.FILE_ROUTE_CSV_LIST_SUPPLIER,
            dataProviderClass = StaticProvider.class
    )
    public void test(Supplier<List<RouteCsv>> routeCsvListSupplier) throws Exception {
        List<RouteCsv> routes = routeCsvListSupplier.get();
        assertEquals(879, routes.size());

        RouteCsv routeCsv = routes.get(0);
        assertEquals("д", routeCsv.getRouteNumber());
        assertEquals("minsk", routeCsv.getAuthority());
        assertEquals("minsk", routeCsv.getCity());
        assertEquals("bus", routeCsv.getTransport());
        assertEquals("7 АП", routeCsv.getOperator());
        assertEquals("16041,", routeCsv.getValidityPeriods());
        assertEquals("0", routeCsv.getSpecialDates());
        assertEquals("", routeCsv.getRouteTag());
        assertEquals("A>B", routeCsv.getRouteType());
        assertEquals("A", routeCsv.getCommercial());
        assertEquals("Малышок (Уручье-2)", routeCsv.getRouteName());
        assertEquals("12345", routeCsv.getWeekDays());
        assertEquals("112532", routeCsv.getRouteId());
        assertEquals("", routeCsv.getEntry());
        assertEquals("14386,14384,14382,14648,14646,14644,14642,14376,14373,14371,14370,14368,69144", routeCsv.getRouteStops());
        assertEquals("d1-d5 - 02.12.2013", routeCsv.getDateStart());
    }
}

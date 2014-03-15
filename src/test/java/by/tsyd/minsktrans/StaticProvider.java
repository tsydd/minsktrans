package by.tsyd.minsktrans;

import by.tsyd.minsktrans.csv.route.RouteCsv;
import by.tsyd.minsktrans.csv.route.RouteCsvListSupplier;
import by.tsyd.minsktrans.csv.stop.StopCsv;
import by.tsyd.minsktrans.csv.stop.StopCsvListSupplier;
import by.tsyd.minsktrans.service.route.CsvBasedRouteListSupplier;
import by.tsyd.minsktrans.service.stop.CsvBasedStopListSupplier;
import by.tsyd.minsktrans.service.stop.index.StopByIdIndex;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 18.02.14.
 */
public class StaticProvider {

    public static final String FILE_STOP_CSV_SUPPLIER = "fileStopCsvList";
    public static final String FILE_ROUTE_CSV_LIST_SUPPLIER = "fileRouteCsvList";
    public static final String FILE_ROUTE_LIST_SUPPLIER = "fileRouteList";

    @DataProvider(name = FILE_STOP_CSV_SUPPLIER)
    public static Object[][] fileBasedStopCsvListSupplier() {
        return new Object[][]{{getStopCsvListFromFileSupplier()}};
    }

    public static Supplier<List<StopCsv>> getStopCsvListFromFileSupplier() {
        Supplier<InputStream> resourceProvider = () -> StaticProvider.class.getResourceAsStream("/stops.txt");
        return new StopCsvListSupplier(resourceProvider);
    }

    @DataProvider(name = FILE_ROUTE_CSV_LIST_SUPPLIER)
    public static Object[][] fileBasedRouteCsvListSupplier() {
        return new Object[][]{{getRouteCsvListSupplier()}};
    }

    @DataProvider(name = FILE_ROUTE_LIST_SUPPLIER)
    public static Object[][] fileBasedRouteListSupplier() {
        StopByIdIndex stopByIdIndex = new StopByIdIndex(new CsvBasedStopListSupplier(getStopCsvListFromFileSupplier()));
        return new Object[][]{{new CsvBasedRouteListSupplier(getRouteCsvListSupplier(), stopByIdIndex, (routeId) -> null)}};
    }

    private static Supplier<List<RouteCsv>> getRouteCsvListSupplier() {
        Supplier<InputStream> resourceProvider = () -> StaticProvider.class.getResourceAsStream("/routes.txt");
        return new RouteCsvListSupplier(resourceProvider);
    }
}

package by.tsyd.minsktrans;

import by.tsyd.minsktrans.csv.route.RouteCsvListSupplier;
import by.tsyd.minsktrans.csv.route.RouteCsv;
import by.tsyd.minsktrans.csv.stop.StopCsvListSupplier;
import by.tsyd.minsktrans.csv.stop.StopCsv;
import by.tsyd.minsktrans.service.route.CsvBasedRoutesProvider;
import by.tsyd.minsktrans.service.stop.CsvBasedStopsProvider;
import by.tsyd.minsktrans.service.stop.StopByIdIndex;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 18.02.14.
 */
public class StaticProvider {

    public static final String FILE_STOP_CSV_PROVIDER = "fileStopCsvList";
    public static final String FILE_STOP_PROVIDER = "fileStopList";
    public static final String FILE_ROUTE_CSV_LIST_PROVIDER = "fileRouteCsvList";
    public static final String FILE_ROUTE_LIST_PROVIDER = "fileRouteList";

    @DataProvider(name = FILE_STOP_CSV_PROVIDER)
    public static Object[][] fileBasedStopCsvListSupplier() {
        return new Object[][]{{getStopCsvListSupplier()}};
    }

    @DataProvider(name = StaticProvider.FILE_STOP_PROVIDER)
    public static Object[][] fileBasedStopListSupplier() {
        return new Object[][]{{new CsvBasedStopsProvider(getStopCsvListSupplier())}};
    }

    private static Supplier<List<StopCsv>> getStopCsvListSupplier() {
        InputStream inputStream = StaticProvider.class.getResourceAsStream("/stops.txt");
        ResourceProvider resourceProvider = new InputStreamBasedResourceProvider(inputStream);
        return new StopCsvListSupplier(resourceProvider);
    }

    @DataProvider(name = FILE_ROUTE_CSV_LIST_PROVIDER)
    public static Object[][] fileBasedRouteCsvListSupplier() {
        return new Object[][]{{getRouteCsvListSupplier()}};
    }

    @DataProvider(name = FILE_ROUTE_LIST_PROVIDER)
    public static Object[][] fileBasedRouteListSupplier() {
        StopByIdIndex stopByIdIndex = new StopByIdIndex(new CsvBasedStopsProvider(getStopCsvListSupplier()));
        return new Object[][]{{new CsvBasedRoutesProvider(getRouteCsvListSupplier(), stopByIdIndex)}};
    }

    private static Supplier<List<RouteCsv>> getRouteCsvListSupplier() {
        InputStream inputStream = StaticProvider.class.getResourceAsStream("/routes.txt");
        ResourceProvider resourceProvider = new InputStreamBasedResourceProvider(inputStream);
        return new RouteCsvListSupplier(resourceProvider);
    }
}

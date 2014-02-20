package by.tsyd.minsktrans.service.route;

import by.tsyd.minsktrans.csv.route.RouteCsv;
import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.service.stop.index.StopByIdIndex;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 16.02.14.
 */
public class CsvBasedRoutesProvider implements Supplier<List<Route>> {

    private final Supplier<List<RouteCsv>> csvRoutesProvider;
    private StopByIdIndex stopByIdIndex;

    public CsvBasedRoutesProvider(Supplier<List<RouteCsv>> csvRoutesProvider, StopByIdIndex stopByIdIndex) {
        this.csvRoutesProvider = csvRoutesProvider;
        this.stopByIdIndex = stopByIdIndex;
    }

    @Override
    public List<Route> get() {
        Function<RouteCsv, Route> routeCsvToRoute = new RouteCsvToRoute(stopByIdIndex);

        List<Route> result = new LinkedList<>();

        for (RouteCsv routeCsv : csvRoutesProvider.get()) {
            result.add(routeCsvToRoute.apply(routeCsv));
        }

        return result;
    }
}

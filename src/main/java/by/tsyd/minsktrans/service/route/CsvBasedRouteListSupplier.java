package by.tsyd.minsktrans.service.route;

import by.tsyd.minsktrans.csv.route.RouteCsv;
import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.Stop;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Tsydzik
 * @since Date: 16.02.14.
 */
public class CsvBasedRouteListSupplier implements Supplier<List<Route>> {

    private final Supplier<List<RouteCsv>> routeCsvListSupplier;
    private Function<Long, Stop> stopByIdIndex;

    public CsvBasedRouteListSupplier(Supplier<List<RouteCsv>> routeCsvListSupplier, Function<Long, Stop> stopByIdIndex) {
        this.routeCsvListSupplier = routeCsvListSupplier;
        this.stopByIdIndex = stopByIdIndex;
    }

    @Override
    public List<Route> get() {
        return routeCsvListSupplier.get().stream()
                .sequential()
                .map(new RouteCsvToRoute(stopByIdIndex))
                .collect(toList());
    }
}

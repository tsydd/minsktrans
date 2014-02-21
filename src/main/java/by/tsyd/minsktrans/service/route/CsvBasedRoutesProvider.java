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
public class CsvBasedRoutesProvider implements Supplier<List<Route>> {

    private final Supplier<List<RouteCsv>> csvRoutesProvider;
    private Function<Long, Stop> stopByIdIndex;

    public CsvBasedRoutesProvider(Supplier<List<RouteCsv>> csvRoutesProvider, Function<Long, Stop> stopByIdIndex) {
        this.csvRoutesProvider = csvRoutesProvider;
        this.stopByIdIndex = stopByIdIndex;
    }

    @Override
    public List<Route> get() {
        return csvRoutesProvider.get()
                .stream()
                .sequential()
                .map(new RouteCsvToRoute(stopByIdIndex))
                .collect(toList());
    }
}

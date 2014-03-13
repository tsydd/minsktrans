package by.tsyd.minsktrans.service.time;

import by.tsyd.minsktrans.csv.time.TimeCsv;
import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.RouteTimeConfig;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.03.14.
 */
public class CsvBasedRouteTimeConfigListSupplier implements Supplier<List<RouteTimeConfig>> {

    private final Supplier<List<TimeCsv>> timeCsvListSupplier;
    private final Function<TimeCsv, RouteTimeConfig> timeCsvToRouteTimeConfig;

    public CsvBasedRouteTimeConfigListSupplier(Supplier<List<TimeCsv>> timeCsvListSupplier,
                                               Function<Long, Route> routeByIdIndex) {
        this.timeCsvListSupplier = timeCsvListSupplier;
        timeCsvToRouteTimeConfig = new TimeCsvToRouteTimeConfig(routeByIdIndex);
    }

    @Override
    public List<RouteTimeConfig> get() {
        return timeCsvListSupplier.get().stream()
                .map(timeCsvToRouteTimeConfig)
                .collect(toList());
    }
}

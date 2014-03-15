package by.tsyd.minsktrans.service.time;

import by.tsyd.minsktrans.csv.time.TimeCsv;
import by.tsyd.minsktrans.domain.RouteConfigWithRouteId;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.03.14.
 */
public class CsvBasedRouteTimeConfigListSupplier implements Supplier<List<RouteConfigWithRouteId>> {

    private final Supplier<List<TimeCsv>> timeCsvListSupplier;

    public CsvBasedRouteTimeConfigListSupplier(Supplier<List<TimeCsv>> timeCsvListSupplier) {
        this.timeCsvListSupplier = timeCsvListSupplier;
    }

    @Override
    public List<RouteConfigWithRouteId> get() {
        return timeCsvListSupplier.get().stream()
                .map(new TimeCsvToRouteTimeConfig())
                .collect(toList());
    }
}

package by.tsyd.minsktrans.service.route.index;

import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.TransportType;
import by.tsyd.minsktrans.util.LazyValue;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author Dmitry Tsydzik
 * @since Date: 18.02.14.
 */
public class RoutesByTransportIndex implements Function<TransportType, List<Route>> {
    private final Supplier<Map<TransportType, List<Route>>> index;

    public RoutesByTransportIndex(Supplier<List<Route>> dataProvider) {
        index = new LazyValue<>(() -> dataProvider.get()
                .stream()
                .collect(groupingBy(Route::getTransport)));
    }

    @Override
    public List<Route> apply(TransportType transport) {
        return index.get().get(transport);
    }
}

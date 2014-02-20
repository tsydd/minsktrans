package by.tsyd.minsktrans.service.route.index;

import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.TransportType;
import by.tsyd.minsktrans.util.LazyValue;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Dmitry Tsydzik
 * @since Date: 19.02.14.
 */
public class RoutesByTransportAndRouteNumberIndex {

    private final Supplier<Map<TransportType, Map<String, List<Route>>>> index;


    public RoutesByTransportAndRouteNumberIndex(Supplier<List<Route>> routesSupplier) {
        index = new LazyValue<>(() -> routesSupplier.get().stream()
                .collect(Collectors.groupingBy(Route::getTransport, Collectors.groupingBy(Route::getRouteNumber))));
    }

    public List<Route> getByTransportAndRouteNumber(TransportType transport, String routeNumber) {
        return index.get().get(transport).get(routeNumber);
    }
}

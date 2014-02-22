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
 * @since Date: 18.02.14.
 */
public class TransportToRoutesIndex {
    private final Supplier<Map<TransportType, List<Route>>> index;

    public TransportToRoutesIndex(Supplier<List<Route>> routeListSupplier) {
        index = new LazyValue<>(() -> routeListSupplier.get().stream()
                .collect(Collectors.groupingBy(Route::getTransport)));
    }

    public Map<TransportType, List<Route>> getTransportToRoutes() {
        return index.get();
    }
}

package by.tsyd.minsktrans.service.route.index;

import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.util.LazyValue;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toMap;

/**
 * @author Dmitry Tsydzik
 * @since Date: 23.02.14.
 */
public class RouteByIdIndex implements Function<Long, Route> {

    private final Supplier<Map<Long, Route>> index;

    public RouteByIdIndex(Supplier<List<Route>> routeListSupplier) {
        index = new LazyValue<>(() -> routeListSupplier.get().stream()
                .collect(toMap(Route::getId, Function.<Route>identity()))
        );
    }

    @Override
    public Route apply(Long id) {
        return index.get().get(id);
    }
}

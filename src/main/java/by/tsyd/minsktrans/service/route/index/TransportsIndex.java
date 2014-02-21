package by.tsyd.minsktrans.service.route.index;

import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.TransportType;
import by.tsyd.minsktrans.util.LazyValue;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toSet;

/**
 * @author Dmitry Tsydzik
 * @since Date: 18.02.14.
 */
public class TransportsIndex implements Supplier<Set<TransportType>> {

    private final Supplier<Set<TransportType>> index;

    public TransportsIndex(Supplier<List<Route>> routes) {
        index = new LazyValue<>(() -> routes.get().stream()
                .map(Route::getTransport)
                .collect(toSet()));
    }

    @Override
    public Set<TransportType> get() {
        return index.get();
    }
}

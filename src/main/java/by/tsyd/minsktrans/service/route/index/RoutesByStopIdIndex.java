package by.tsyd.minsktrans.service.route.index;

import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.util.LazyValue;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.*;

/**
 * @author Dmitry Tsydzik
 * @since Date: 19.02.14.
 */
public class RoutesByStopIdIndex implements Function<Long, List<Route>> {

    private static class StopIdRoute {
        private Long stopId;
        private Route route;

        public Long getStopId() {
            return stopId;
        }

        public Route getRoute() {
            return route;
        }

        private StopIdRoute(Long stopId, Route route) {
            this.stopId = stopId;
            this.route = route;
        }
    }

    private Supplier<Map<Long, List<Route>>> index;

    public RoutesByStopIdIndex(Supplier<List<Route>> routeListSupplier) {
        index = new LazyValue<>(() -> routeListSupplier.get().stream()
                .flatMap(route -> route.getStops().stream()
                        .map(stop -> new StopIdRoute(stop.getId(), route)))
                .collect(groupingBy(StopIdRoute::getStopId,
                        mapping(StopIdRoute::getRoute,
                                toList())))
        );
    }

    @Override
    public List<Route> apply(Long stopId) {
        return index.get().get(stopId);
    }
}

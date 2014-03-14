package by.tsyd.minsktrans.service.stop.index;

import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.util.LazyValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 14.03.14.
 */
public class StopInRouteIndex implements BiFunction<Long, Long, Integer> {

    private final Supplier<Map<Long, Map<Long, Integer>>> routeStopIndexMap;

    public StopInRouteIndex(Supplier<List<Route>> routeListSupplier) {
        routeStopIndexMap = new LazyValue<>(() -> {
            Map<Long, Map<Long, Integer>> result = new HashMap<>();
            routeListSupplier.get().forEach(route -> {
                Map<Long, Integer> stopToIndexInRoute = new HashMap<>();
                for (int i = 0; i < route.getStops().size(); i++) {
                    stopToIndexInRoute.put(route.getStops().get(i).getId(), i);
                }
                result.put(route.getId(), stopToIndexInRoute);
            });
            return result;
        });
    }

    @Override
    public Integer apply(Long routeId, Long stopId) {
        Map<Long, Integer> stopToIndexMap = routeStopIndexMap.get().get(routeId);
        return stopToIndexMap == null ? null : stopToIndexMap.get(stopId);
    }
}

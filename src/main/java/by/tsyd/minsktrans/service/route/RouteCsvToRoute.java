package by.tsyd.minsktrans.service.route;

import by.tsyd.minsktrans.csv.route.RouteCsv;
import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.Stop;
import by.tsyd.minsktrans.domain.TransportType;
import by.tsyd.minsktrans.service.stop.StopByIdIndex;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Dmitry Tsydzik
 * @since Date: 18.02.14.
 */
public class RouteCsvToRoute implements Function<RouteCsv, Route> {

    private String routeNumber = null;
    private TransportType transport = null;
    private String operator = null;
    private StopByIdIndex stopByIdIndex;

    public RouteCsvToRoute(StopByIdIndex stopByIdIndex) {
        this.stopByIdIndex = stopByIdIndex;
    }

    private TransportType getTransportType(String transportType) {
        switch (transportType) {
            case "bus":
                return TransportType.BUS;
            case "metro":
                return TransportType.METRO;
            case "tram":
                return TransportType.TRAM;
            case "trol":
                return TransportType.TROLLEYBUS;
            default:
                throw new IllegalArgumentException("Transport type is not supported: " + transportType);
        }
    }

    @Override
    public Route apply(RouteCsv routeCsv) {
        if (!routeCsv.getTransport().isEmpty()) {
            transport = getTransportType(routeCsv.getTransport());
        }
        if (!routeCsv.getOperator().isEmpty()) {
            operator = routeCsv.getOperator();
        }
        if (!routeCsv.getRouteNumber().isEmpty()) {
            routeNumber = routeCsv.getRouteNumber();
        }

        Route route = new Route();
        route.setRouteNumber(routeNumber);
        route.setOperator(operator);
        route.setTransport(transport);
        route.setRouteName(routeCsv.getRouteName());
        route.setRouteType(routeCsv.getRouteType());

        String routeStops = routeCsv.getRouteStops();
        if (routeStops.isEmpty()) {
            route.setStops(Collections.emptyList());
        } else {

            List<Stop> stops = Arrays.stream(routeStops.split(","))
                    .map(Long::valueOf)
                    .map(stopByIdIndex::getStop)
                    .collect(Collectors.toList());

            route.setStops(stops);
        }

        return route;
    }

}

package by.tsyd.minsktrans.service.route;

import by.tsyd.minsktrans.csv.route.RouteCsv;
import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.Stop;
import by.tsyd.minsktrans.domain.TransportType;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitry Tsydzik
 * @since Date: 18.02.14.
 */
public class CsvBasedRouteListSupplierTest {

    @Test
    public void testConversion() throws Exception {
        RouteCsv routeCsv = new RouteCsv();
        routeCsv.setRouteId("1");
        routeCsv.setRouteNumber("route_number");
        routeCsv.setTransport("bus");
        routeCsv.setOperator("operator");
        routeCsv.setRouteName("name");
        routeCsv.setRouteType("type");
        routeCsv.setRouteStops("1");

        Stop stop = new Stop();
        stop.setId(1L);

        Supplier<List<Route>> routeListSupplier = new CsvBasedRouteListSupplier(
                () -> Arrays.asList(routeCsv),
                (stopId) -> stop,
                (routeId) -> null);
        List<Route> routes = routeListSupplier.get();
        Route route = routes.get(0);

        assertThat(route.getId()).isEqualTo(1);
        assertThat(route.getRouteNumber()).isEqualTo(routeCsv.getRouteNumber());
        assertThat(route.getTransport()).isEqualTo(TransportType.BUS);
        assertThat(route.getOperator()).isEqualTo(routeCsv.getOperator());
        assertThat(route.getRouteName()).isEqualTo(routeCsv.getRouteName());
        assertThat(route.getRouteType()).isEqualTo(routeCsv.getRouteType());
        assertThat(route.getStops()).containsExactly(stop);
    }

    @Test
    public void testPropagation() throws Exception {
        RouteCsv routeCsv1 = new RouteCsv();
        routeCsv1.setRouteId("1");
        routeCsv1.setRouteNumber("route_number");
        routeCsv1.setTransport("bus");
        routeCsv1.setOperator("operator");
        routeCsv1.setRouteStops("");

        RouteCsv routeCsv2 = new RouteCsv();
        routeCsv2.setRouteId("2");
        routeCsv2.setRouteNumber("");
        routeCsv2.setTransport("");
        routeCsv2.setOperator("");
        routeCsv2.setRouteStops("");

        Supplier<List<Route>> routeListSupplier = new CsvBasedRouteListSupplier(
                () -> Arrays.asList(routeCsv1, routeCsv2),
                (stopId) -> null,
                (routeId) -> null);
        List<Route> routes2 = routeListSupplier.get();
        Route route = routes2.get(1);

        assertThat(route.getRouteNumber()).isEqualTo(routeCsv1.getRouteNumber());
        assertThat(route.getTransport()).isEqualTo(TransportType.BUS);
        assertThat(route.getOperator()).isEqualTo(routeCsv1.getOperator());
    }
}

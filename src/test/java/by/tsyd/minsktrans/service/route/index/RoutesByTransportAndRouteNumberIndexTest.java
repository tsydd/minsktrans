package by.tsyd.minsktrans.service.route.index;

import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.TransportType;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitry Tsydzik
 * @since Date: 19.02.14.
 */
public class RoutesByTransportAndRouteNumberIndexTest {

    @Test
    public void test() throws Exception {
        Route route1 = new Route();
        route1.setTransport(TransportType.BUS);
        route1.setRouteNumber("route_number");

        Route route2 = new Route();
        route2.setTransport(TransportType.TROLLEYBUS);
        route2.setRouteNumber("another_number");

        Route route12 = new Route();
        route12.setTransport(route1.getTransport());
        route12.setRouteNumber(route2.getRouteNumber());

        Route route21 = new Route();
        route21.setTransport(route2.getTransport());
        route21.setRouteNumber(route1.getRouteNumber());

        BiFunction<TransportType, String, List<Route>> index = new RoutesByTransportAndRouteNumberIndex(
                () -> Arrays.asList(route1, route2, route12, route21)
        );

        assertThat(index.apply(route1.getTransport(), route1.getRouteNumber())).containsExactly(route1);
        assertThat(index.apply(route2.getTransport(), route2.getRouteNumber())).containsExactly(route2);
        assertThat(index.apply(route1.getTransport(), route2.getRouteNumber())).containsExactly(route12);
        assertThat(index.apply(route2.getTransport(), route1.getRouteNumber())).containsExactly(route21);
        assertThat(index.apply(route1.getTransport(), "yet_another_number")).isNull();
        assertThat(index.apply(TransportType.METRO, route1.getRouteNumber())).isNull();
    }
}

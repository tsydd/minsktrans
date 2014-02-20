package by.tsyd.minsktrans.service.route;

import by.tsyd.minsktrans.StaticProvider;
import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.Stop;
import by.tsyd.minsktrans.domain.TransportType;
import by.tsyd.minsktrans.service.route.index.RoutesByTransportAndRouteNumberIndex;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 19.02.14.
 */
public class RoutesByTransportAndRouteNumberIndexTest {

    @Test(
            dataProvider = StaticProvider.FILE_ROUTE_LIST_PROVIDER,
            dataProviderClass = StaticProvider.class
    )
    public void test(Supplier<List<Route>> routesSupplier) throws Exception {
        RoutesByTransportAndRouteNumberIndex index = new RoutesByTransportAndRouteNumberIndex(routesSupplier);
        List<Route> trolleybus33 = index.getByTransportAndRouteNumber(TransportType.TROLLEYBUS, "33");

        trolleybus33.stream()
                .map(Route::getRouteType)
                .forEach(System.out::println);

        Route mainRouteTrolleybus33 = trolleybus33.get(0);
        System.out.println(mainRouteTrolleybus33);

        mainRouteTrolleybus33.getStops().stream()
                .map(Stop::getName)
                .forEach(System.out::println);
    }
}

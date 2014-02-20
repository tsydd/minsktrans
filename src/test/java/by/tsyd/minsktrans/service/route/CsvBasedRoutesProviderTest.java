package by.tsyd.minsktrans.service.route;

import by.tsyd.minsktrans.StaticProvider;
import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.TransportType;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toSet;
import static org.testng.Assert.assertEquals;

/**
 * @author Dmitry Tsydzik
 * @since Date: 18.02.14.
 */
public class CsvBasedRoutesProviderTest {
    @Test(
            dataProvider = StaticProvider.FILE_ROUTE_LIST_PROVIDER,
            dataProviderClass = StaticProvider.class
    )
    public void test(Supplier<List<Route>> routeProvider) throws Exception {
        List<Route> routes = routeProvider.get();
        assertEquals(879, routes.size());

        Route route = routes.get(0);
        assertEquals("д", route.getRouteNumber());
        assertEquals(TransportType.BUS, route.getTransport());
        assertEquals("7 АП", route.getOperator());
        assertEquals("Малышок (Уручье-2)", route.getRouteName());
        assertEquals("A>B", route.getRouteType());

        System.out.println(route.getStops());

        System.out.println(routes.stream()
                .map(Route::getTransport)
                .collect(toSet()));

        System.out.println(routes.stream()
                .map(Route::getOperator)
                .collect(toSet()));

        System.out.println(routes.stream()
                .map(Route::getRouteType)
                .collect(toSet()));

    }
}

package by.tsyd.minsktrans.service.route.index;

import by.tsyd.minsktrans.StaticProvider;
import by.tsyd.minsktrans.domain.Route;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 20.02.14.
 */
public class RoutesByStopIdIndexTest {
    @Test(
            dataProvider = StaticProvider.FILE_ROUTE_LIST_PROVIDER,
            dataProviderClass = StaticProvider.class
    )
    public void test(Supplier<List<Route>> routesSupplier) throws Exception {
        Function<Long, List<Route>> index = new RoutesByStopIdIndex(routesSupplier);
//        14772
//        List<Route> routes = index.apply(14741L);
        List<Route> routes = index.apply(14772L);
        routes.forEach(System.out::println);
    }
}

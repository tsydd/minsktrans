package by.tsyd.minsktrans.integration;

import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.RouteTimeConfig;
import by.tsyd.minsktrans.domain.TransportType;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author Dmitry Tsydzik
 * @since Date: 13.03.14.
 */
public class StopScheduleIntegrationTest extends AbstractLocalResourcesIntegrationTest {

    @Autowired
    private BiFunction<TransportType, String, List<Route>> routesByTransportAndRouteNumberIndex;

    @Autowired
    private BiFunction<Long, Long, Integer> stopInRouteIndex;

    @Test
    public void testName() throws Exception {
        List<Route> routesTrolleybus33 = routesByTransportAndRouteNumberIndex.apply(TransportType.TROLLEYBUS, "33");
        Route directTrolleybus33Route = routesTrolleybus33.get(0);
        RouteTimeConfig routeTimeConfig = directTrolleybus33Route.getConfig();

//        long stopId = 14741; // Столетова
        long stopId = directTrolleybus33Route.getStops().get(0).getId();
        int stopIndex = stopInRouteIndex.apply(directTrolleybus33Route.getId(), stopId);
        int timeConfigCount = routeTimeConfig.getRaceConfigs().size();
        for (int i = 0; i < timeConfigCount; i++) {
            System.out.println(routeTimeConfig.getTimes().get(stopIndex * timeConfigCount + i));
        }
    }
}

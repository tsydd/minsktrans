package by.tsyd.minsktrans.service.route.index;

import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.TransportType;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * @author Dmitry Tsydzik
 * @since Date: 23.02.14.
 */
public class RoutesByTransportIndexTest {
    @Test
    public void test() throws Exception {
        Route route = new Route();
        route.setTransport(TransportType.BUS);

        Function<TransportType, List<Route>> index = new RoutesByTransportIndex(() -> Arrays.asList(route));
        assertEquals(Arrays.asList(route), index.apply(TransportType.BUS));
        assertNull(index.apply(TransportType.METRO));
    }
}

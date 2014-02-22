package by.tsyd.minsktrans.service.route.index;

import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.Stop;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

/**
 * @author Dmitry Tsydzik
 * @since Date: 20.02.14.
 */
public class RoutesByStopIdIndexTest {
    @Test
    public void test() throws Exception {
        Stop stop = new Stop();
        stop.setId(1L);

        Route route = new Route();
        route.setStops(Arrays.asList(stop));

        Function<Long, List<Route>> index = new RoutesByStopIdIndex(() -> Arrays.asList(route));
        assertNull(index.apply(0L));
        assertEquals(Arrays.asList(route), index.apply(stop.getId()));
    }
}

package by.tsyd.minsktrans.service.route.index;

import by.tsyd.minsktrans.domain.Route;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitry Tsydzik
 * @since Date: 23.02.14.
 */
public class RouteByIdIndexTest {

    @Test
    public void test() throws Exception {
        Route route = new Route();
        route.setId(1L);
        Function<Long, Route> index = new RouteByIdIndex(() -> Arrays.asList(route));

        assertThat(index.apply(route.getId())).isSameAs(route);
    }
}

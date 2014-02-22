package by.tsyd.minsktrans.service.stop.index;

import by.tsyd.minsktrans.domain.Stop;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.function.Function;

import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;

/**
 * @author Dmitry Tsydzik
 * @since Date: 23.02.14.
 */
public class StopByIdIndexTest {
    @Test
    public void test() throws Exception {
        Stop stop = new Stop();
        stop.setId(1L);

        Function<Long, Stop> index = new StopByIdIndex(() -> Arrays.asList(stop));
        assertNull(index.apply(0L));
        assertSame(stop, index.apply(stop.getId()));
    }
}

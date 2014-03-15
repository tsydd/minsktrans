package by.tsyd.minsktrans.service.stop.index;

import by.tsyd.minsktrans.domain.Stop;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(index.apply(0L)).isNull();
        assertThat(index.apply(stop.getId())).isSameAs(stop);
    }
}

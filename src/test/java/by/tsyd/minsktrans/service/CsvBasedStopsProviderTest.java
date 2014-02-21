package by.tsyd.minsktrans.service;

import by.tsyd.minsktrans.StaticProvider;
import by.tsyd.minsktrans.domain.Stop;
import by.tsyd.minsktrans.service.stop.index.StopByIdIndex;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.02.14.
 */
public class CsvBasedStopsProviderTest {

    @Test(
            dataProvider = StaticProvider.FILE_STOP_PROVIDER,
            dataProviderClass = StaticProvider.class
    )
    public void test(Supplier<List<Stop>> stopDataProvider) throws Exception {
        Function<Long, Stop> stopByIdIndex = new StopByIdIndex(stopDataProvider);

        Stop stop = stopByIdIndex.apply(14741L);
        System.out.println(stop);
        for (Stop stop1 : stop.getStops()) {
            System.out.println(stop1);
        }
    }
}

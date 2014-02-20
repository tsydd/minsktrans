package by.tsyd.minsktrans.service.stop;

import by.tsyd.minsktrans.csv.stop.StopCsv;
import by.tsyd.minsktrans.domain.Stop;
import com.google.common.base.Strings;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * @author Dmitry Tsydzik
 * @since Date: 20.02.14.
 */
public class StopCsvToStop implements Function<StopCsv, Stop> {

    private static final BigDecimal bd100000 = BigDecimal.valueOf(100000);
    private String name;

    @Override
    public Stop apply(StopCsv stopCsv) {
        if (!Strings.isNullOrEmpty(stopCsv.getName())) {
            name = stopCsv.getName();
        }
        Stop stop = new Stop();
        stop.setId(Long.valueOf(stopCsv.getId()));
        stop.setName(name);
        stop.setLatitude(new BigDecimal(stopCsv.getLatitude()).divide(bd100000));
        stop.setLongitude(new BigDecimal(stopCsv.getLongitude()).divide(bd100000));
        return stop;
    }
}

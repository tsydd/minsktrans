package by.tsyd.minsktrans.service.time;

import java.time.LocalTime;
import java.util.function.Function;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.03.14.
 */
public class MinutesToLocalTime implements Function<Long, LocalTime> {
    @Override
    public LocalTime apply(Long minutes) {
        return (minutes == null || minutes < 0)
                ? null
                : LocalTime.ofSecondOfDay(minutes * 60 % (24 * 60 * 60));
    }
}

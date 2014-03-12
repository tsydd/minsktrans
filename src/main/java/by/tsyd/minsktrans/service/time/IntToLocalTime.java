package by.tsyd.minsktrans.service.time;

import java.time.LocalTime;
import java.util.function.Function;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.03.14.
 */
public class IntToLocalTime implements Function<Integer, LocalTime> {
    @Override
    public LocalTime apply(Integer integer) {
        return (integer == null || integer < 0)
                ? null
                : LocalTime.ofSecondOfDay(integer * 60 % (24 * 60 * 60));
    }
}

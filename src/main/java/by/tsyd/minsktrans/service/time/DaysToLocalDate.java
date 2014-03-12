package by.tsyd.minsktrans.service.time;

import java.time.LocalDate;
import java.util.function.Function;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.03.14.
 */
public class DaysToLocalDate implements Function<Long, LocalDate> {
    @Override
    public LocalDate apply(Long days) {
        return (days == null || days.equals(0L))
                ? null
                : LocalDate.ofEpochDay(days);
    }
}

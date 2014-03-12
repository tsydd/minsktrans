package by.tsyd.minsktrans.service.time;

import java.time.DayOfWeek;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.03.14.
 */
public class StringToDaysOfWeek implements Function<String, List<DayOfWeek>> {
    @Override
    public List<DayOfWeek> apply(String s) {
        return s.chars().boxed()
                .map(c -> new char[]{(char) ((int) c)})
                .map(String::valueOf)
                .map(Integer::valueOf)
                .map(DayOfWeek::of)
                .collect(toList());
    }
}

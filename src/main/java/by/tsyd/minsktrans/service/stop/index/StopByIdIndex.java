package by.tsyd.minsktrans.service.stop.index;

import by.tsyd.minsktrans.domain.Stop;
import by.tsyd.minsktrans.util.LazyValue;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toMap;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.02.14.
 */
public class StopByIdIndex {

    private final LazyValue<Map<Long,Stop>> index;

    public StopByIdIndex(Supplier<List<Stop>> stopsProvider) {
        index = new LazyValue<>(() -> stopsProvider.get()
                .stream()
                .collect(toMap(Stop::getId, Function.<Stop>identity())));
    }

    public Stop getStop(long id) {
        return index.get().get(id);
    }
}

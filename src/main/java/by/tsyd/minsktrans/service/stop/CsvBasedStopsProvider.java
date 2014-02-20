package by.tsyd.minsktrans.service.stop;

import by.tsyd.minsktrans.csv.stop.StopCsv;
import by.tsyd.minsktrans.domain.Stop;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.02.14.
 */
public class CsvBasedStopsProvider implements Supplier<List<Stop>> {

    private final Supplier<List<StopCsv>> csvStopsProvider;

    public CsvBasedStopsProvider(Supplier<List<StopCsv>> csvStopsProvider) {
        this.csvStopsProvider = csvStopsProvider;
    }

    @Override
    public List<Stop> get() {
        List<StopCsv> stopCsvs = csvStopsProvider.get();

        Function<StopCsv, Stop> stopCsvToStop = new StopCsvToStop();

        List<Stop> stopList = stopCsvs.stream().sequential()
                .map(stopCsvToStop)
                .collect(toList());

        Map<Long, Stop> stopMap = stopList.stream()
                .collect(toMap(Stop::getId, Function.<Stop>identity()));

        stopCsvs.forEach(stopCsv -> stopMap.get(Long.valueOf(stopCsv.getId()))
                        .setStops(Arrays.stream(stopCsv.getStops().split(",", -1))
                                        .map(Long::valueOf)
                                        .map(stopMap::get)
                                        .collect(toList())
                        )
        );

        return stopList;
    }
}

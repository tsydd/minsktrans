package by.tsyd.minsktrans.service.stop;

import by.tsyd.minsktrans.csv.stop.StopCsv;
import by.tsyd.minsktrans.domain.Stop;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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
        List<Stop> stopList = new LinkedList<>();
        Map<Long, Stop> stopMap = new HashMap<>();

        String name = null;
        BigDecimal bd100000 = BigDecimal.valueOf(100000);

        for (StopCsv stopCsv : stopCsvs) {
            if (!Strings.isNullOrEmpty(stopCsv.getName())) {
                name = stopCsv.getName();
            }
            Stop stop = new Stop();
            stop.setId(stopCsv.getId());
            stop.setName(name);
            stop.setLatitude(new BigDecimal(stopCsv.getLatitude()).divide(bd100000));
            stop.setLongitude(new BigDecimal(stopCsv.getLongitude()).divide(bd100000));

            stopMap.put(stop.getId(), stop);
            stopList.add(stop);
        }

        Splitter splitter = Splitter.on(",").omitEmptyStrings();
        for (StopCsv stopCsv : stopCsvs) {
            List<Stop> stops = new LinkedList<>();
            for (String stopId : splitter.split(stopCsv.getStops())) {
                stops.add(stopMap.get(Long.valueOf(stopId)));
            }
            stopMap.get(stopCsv.getId()).setStops(stops);
        }

        return stopList;
    }
}

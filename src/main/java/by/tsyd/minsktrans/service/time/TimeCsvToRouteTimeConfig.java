package by.tsyd.minsktrans.service.time;

import by.tsyd.minsktrans.csv.time.TimeCsv;
import by.tsyd.minsktrans.domain.Route;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.03.14.
 */
public class TimeCsvToRouteTimeConfig implements Function<TimeCsv, RouteTimeConfig> {

    private final Function<String, List<DayOfWeek>> stringToDaysOfWeek = new StringToDaysOfWeek();
    private final Function<Integer, LocalTime> intToLocalTime = new IntToLocalTime();
    private final Function<Integer, LocalDate> intToLocalDate = new IntToLocalDate();
    private final Function<Long, Route> routeByIdIndex;

    public TimeCsvToRouteTimeConfig(Function<Long, Route> routeByIdIndex) {
        this.routeByIdIndex = routeByIdIndex;
    }

    @Override
    public RouteTimeConfig apply(TimeCsv timeCsv) {
        RouteTimeConfig routeTimeConfig = new RouteTimeConfig();
        routeTimeConfig.setRoute(routeByIdIndex.apply(timeCsv.getRouteId()));

        int w = timeCsv.getWorkDays().size();

        List<TimeConfig> timeConfigs = new ArrayList<>();
        for (int j = 0; j < w; j++) {
            TimeConfig cfg = new TimeConfig();

            cfg.setWorkDays(stringToDaysOfWeek.apply(timeCsv.getWorkDays().get(j)));
            cfg.setGround(timeCsv.getZeroGrounds().contains(j));
            cfg.setValidFrom(intToLocalDate.apply(timeCsv.getValidFrom().get(j)));
            cfg.setValidTo(intToLocalDate.apply(timeCsv.getValidTo().get(j)));

            timeConfigs.add(cfg);
        }
        routeTimeConfig.setTimeConfigs(timeConfigs);

        routeTimeConfig.setTimes(timeCsv.getTimeTable().stream()
                .map(intToLocalTime)
                .collect(toList()));

        return routeTimeConfig;
    }
}

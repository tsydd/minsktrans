package by.tsyd.minsktrans.service.time;

import by.tsyd.minsktrans.domain.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.03.14.
 */
public class TimesService {

    private final Function<Long, Route> routeByIdIndex;
    private final Function<Long, List<Route>> routeListByStopIdIndex;

    public TimesService(Function<Long, Route> routeByIdIndex, Function<Long, List<Route>> routeListByStopIdIndex) {
        this.routeByIdIndex = routeByIdIndex;
        this.routeListByStopIdIndex = routeListByStopIdIndex;
    }

    public List<TimeConfig> getTimes(TimesRequestObject parameters) {
        List<Route> routes = parameters.getRouteIds() == null
                ? routeListByStopIdIndex.apply(parameters.getStopId())
                : parameters.getRouteIds().stream().map(routeByIdIndex).collect(toList());

        List<TimeConfig> times = new ArrayList<>();
        routes.forEach(route -> {
            int stopIndex = 0;
            for (Stop stop : route.getStops()) {
                if (Objects.equals(stop.getId(), parameters.getStopId())) {
                    break;
                }
                stopIndex++;
            }
            int raceConfigCount = route.getConfig().getRaceConfigs().size();
            for (int raceIndex = 0; raceIndex < raceConfigCount; raceIndex++) {
                RaceConfig raceConfig = route.getConfig().getRaceConfigs().get(raceIndex);
                if (parameters.getDayOfWeek() != null && !raceConfig.getWorkDays().contains(parameters.getDayOfWeek())) {
                    continue;
                }

                LocalTime time = route.getConfig().getTimes().get(raceConfigCount * stopIndex + raceIndex);
                if (time.isAfter(parameters.getTimeTo()) || time.isBefore(parameters.getTimeFrom())) {
                    continue;
                }

                TimeConfig timeConfig = new TimeConfig();
                timeConfig.setRoute(route);
                timeConfig.setStopIndex(stopIndex);
                timeConfig.setRaceIndex(raceIndex);
                timeConfig.setTime(time);

                times.add(timeConfig);
            }
        });
        times.sort(Comparator.comparing(TimeConfig::getTime));
        return times;
    }
}

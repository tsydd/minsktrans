package by.tsyd.minsktrans.service.time;

import by.tsyd.minsktrans.csv.time.TimeCsv;
import by.tsyd.minsktrans.domain.RaceConfig;
import by.tsyd.minsktrans.domain.RouteConfigWithRouteId;
import by.tsyd.minsktrans.domain.RouteTimeConfig;

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
public class TimeCsvToRouteTimeConfig implements Function<TimeCsv, RouteConfigWithRouteId> {

    private final Function<String, List<DayOfWeek>> stringToDaysOfWeek = new StringToDaysOfWeek();
    private final Function<Long, LocalTime> minutesToLocalTime = new MinutesToLocalTime();
    private final Function<Long, LocalDate> daysToLocalDate = new DaysToLocalDate();

    public TimeCsvToRouteTimeConfig() {
    }

    @Override
    public RouteConfigWithRouteId apply(TimeCsv timeCsv) {
        RouteConfigWithRouteId routeConfigWithRouteId = new RouteConfigWithRouteId();
        routeConfigWithRouteId.setRouteId(timeCsv.getRouteId());

        RouteTimeConfig routeTimeConfig = new RouteTimeConfig();
        routeConfigWithRouteId.setConfig(routeTimeConfig);

        int w = timeCsv.getWorkDays().size();

        List<RaceConfig> raceConfigs = new ArrayList<>();
        for (int j = 0; j < w; j++) {
            RaceConfig raceConfig = new RaceConfig();

            raceConfig.setWorkDays(stringToDaysOfWeek.apply(timeCsv.getWorkDays().get(j)));
            raceConfig.setGround(timeCsv.getZeroGrounds().contains(j));
            raceConfig.setValidFrom(daysToLocalDate.apply(timeCsv.getValidFrom().get(j)));
            raceConfig.setValidTo(daysToLocalDate.apply(timeCsv.getValidTo().get(j)));

            raceConfigs.add(raceConfig);
        }
        routeTimeConfig.setRaceConfigs(raceConfigs);

        routeTimeConfig.setTimes(timeCsv.getTimeTable().stream()
                .map(minutesToLocalTime)
                .collect(toList()));

        return routeConfigWithRouteId;
    }
}

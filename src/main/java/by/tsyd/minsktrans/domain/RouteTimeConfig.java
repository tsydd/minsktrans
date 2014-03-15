package by.tsyd.minsktrans.domain;

import java.time.LocalTime;
import java.util.List;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.03.14.
 */
public class RouteTimeConfig {
    private List<LocalTime> times;
    private List<RaceConfig> raceConfigs;

    public List<LocalTime> getTimes() {
        return times;
    }

    public void setTimes(List<LocalTime> times) {
        this.times = times;
    }

    public List<RaceConfig> getRaceConfigs() {
        return raceConfigs;
    }

    public void setRaceConfigs(List<RaceConfig> raceConfigs) {
        this.raceConfigs = raceConfigs;
    }
}

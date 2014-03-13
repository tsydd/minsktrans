package by.tsyd.minsktrans.domain;

import java.time.LocalTime;
import java.util.List;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.03.14.
 */
public class RouteTimeConfig {
    private Route route;
    private List<LocalTime> times;
    private List<TimeConfig> timeConfigs;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<LocalTime> getTimes() {
        return times;
    }

    public void setTimes(List<LocalTime> times) {
        this.times = times;
    }

    public List<TimeConfig> getTimeConfigs() {
        return timeConfigs;
    }

    public void setTimeConfigs(List<TimeConfig> timeConfigs) {
        this.timeConfigs = timeConfigs;
    }
}

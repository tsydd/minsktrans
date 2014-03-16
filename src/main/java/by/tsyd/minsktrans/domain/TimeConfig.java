package by.tsyd.minsktrans.domain;

import java.time.LocalTime;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.03.14.
 */
public class TimeConfig {
    private LocalTime time;
    private Route route;
    private int raceIndex;
    private int stopIndex;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getRaceIndex() {
        return raceIndex;
    }

    public void setRaceIndex(int raceIndex) {
        this.raceIndex = raceIndex;
    }

    public int getStopIndex() {
        return stopIndex;
    }

    public void setStopIndex(int stopIndex) {
        this.stopIndex = stopIndex;
    }
}

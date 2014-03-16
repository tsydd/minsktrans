package by.tsyd.minsktrans.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Collection;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.03.14.
 */
public class TimesRequestObject {
    private DayOfWeek dayOfWeek;
    private Long stopId;
    private Collection<Long> routeIds;
    private LocalTime timeFrom;
    private LocalTime timeTo;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Long getStopId() {
        return stopId;
    }

    public void setStopId(Long stopId) {
        this.stopId = stopId;
    }

    public Collection<Long> getRouteIds() {
        return routeIds;
    }

    public void setRouteIds(Collection<Long> routeIds) {
        this.routeIds = routeIds;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }
}

package by.tsyd.minsktrans.csv.time;

import java.util.List;
import java.util.Set;

/**
 * @author Dmitry Tsydzik
 * @since Date: 01.03.14.
 */
public class TimeCsv {

    private Long routeId;
    private List<Long> timeTable;
    private List<Long> validFrom;
    private List<Long> validTo;
    private List<String> workDays;
    private Set<Integer> zeroGrounds;

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public List<Long> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<Long> timeTable) {
        this.timeTable = timeTable;
    }

    public List<Long> getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(List<Long> validFrom) {
        this.validFrom = validFrom;
    }

    public List<Long> getValidTo() {
        return validTo;
    }

    public void setValidTo(List<Long> validTo) {
        this.validTo = validTo;
    }

    public List<String> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(List<String> workDays) {
        this.workDays = workDays;
    }

    public Set<Integer> getZeroGrounds() {
        return zeroGrounds;
    }

    public void setZeroGrounds(Set<Integer> zeroGrounds) {
        this.zeroGrounds = zeroGrounds;
    }
}

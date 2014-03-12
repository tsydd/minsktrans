package by.tsyd.minsktrans.csv.time;

import java.util.List;
import java.util.Set;

/**
 * @author Dmitry Tsydzik
 * @since Date: 01.03.14.
 */
public class TimeCsv {

    private Long routeId;
    private List<Integer> timeTable;
    private List<Integer> validFrom;
    private List<Integer> validTo;
    private List<String> workDays;
    private Set<Integer> zeroGrounds;

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public List<Integer> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<Integer> timeTable) {
        this.timeTable = timeTable;
    }

    public List<Integer> getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(List<Integer> validFrom) {
        this.validFrom = validFrom;
    }

    public List<Integer> getValidTo() {
        return validTo;
    }

    public void setValidTo(List<Integer> validTo) {
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

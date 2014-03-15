package by.tsyd.minsktrans.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collection;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.03.14.
 */
public class RaceConfig {
    private Collection<DayOfWeek> workDays;
    private boolean ground;
    private LocalDate validFrom;
    private LocalDate validTo;

    public Collection<DayOfWeek> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(Collection<DayOfWeek> workDays) {
        this.workDays = workDays;
    }

    public boolean isGround() {
        return ground;
    }

    public void setGround(boolean ground) {
        this.ground = ground;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }
}

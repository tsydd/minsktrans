package by.tsyd.minsktrans.service.time;

import by.tsyd.minsktrans.domain.TimeConfig;
import by.tsyd.minsktrans.domain.TimesRequestObject;
import by.tsyd.minsktrans.integration.AbstractLocalResourcesIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.03.14.
 */
public class TimesServiceTest extends AbstractLocalResourcesIntegrationTest {

    @Autowired
    private TimesService timesService;

    @Test
    public void test() throws Exception {
        TimesRequestObject timesRequestObject = new TimesRequestObject();
        timesRequestObject.setStopId(14741L);
        timesRequestObject.setDayOfWeek(DayOfWeek.SATURDAY);

        LocalTime now = LocalTime.now();
        timesRequestObject.setTimeFrom(now.minusMinutes(10));
        timesRequestObject.setTimeTo(now.plusMinutes(30));

        List<TimeConfig> times = timesService.getTimes(timesRequestObject);
        times.forEach(time -> System.out.printf("%s - %s: %s, %s%n",
                        time.getRoute().getTransport(),
                        time.getRoute().getRouteNumber(),
                        time.getTime(),
                        time.getRoute().getConfig().getRaceConfigs().get(time.getRaceIndex()).getWorkDays())
        );
    }
}

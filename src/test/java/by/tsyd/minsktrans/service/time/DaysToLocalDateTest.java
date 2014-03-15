package by.tsyd.minsktrans.service.time;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Dmitry Tsydzik
 * @since Date: 13.03.14.
 */
public class DaysToLocalDateTest {

    private DaysToLocalDate daysToLocalDate = new DaysToLocalDate();

    @DataProvider(name = "data")
    static Object[][] getData() {
        return new Object[][] {
                {null, null},
                {0L, null},
                {16039L, LocalDate.of(2013, 11, 30)}
        };
    }

    @Test(dataProvider = "data")
    public void test(Long days, LocalDate expected) throws Exception {
        assertThat(expected).isEqualTo(daysToLocalDate.apply(days));
    }
}

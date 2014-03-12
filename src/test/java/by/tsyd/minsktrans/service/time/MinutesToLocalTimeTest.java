package by.tsyd.minsktrans.service.time;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalTime;

import static org.testng.Assert.assertEquals;

/**
 * @author Dmitry Tsydzik
 * @since Date: 13.03.14.
 */
public class MinutesToLocalTimeTest {

    private MinutesToLocalTime minutesToLocalTime = new MinutesToLocalTime();

    @DataProvider(name = "data")
    static Object[][] getData() {
        return new Object[][] {
                {-1L, null},
                {null, null},
                {(long) (13 * 60 + 35), LocalTime.of(13, 35)},
                {(long) (13 * 60 + 35) + (24 * 60 * 60), LocalTime.of(13, 35)}
        };
    }

    @Test(dataProvider = "data")
    public void test(Long input, LocalTime expected) throws Exception {
        assertEquals(minutesToLocalTime.apply(input), expected);
    }
}

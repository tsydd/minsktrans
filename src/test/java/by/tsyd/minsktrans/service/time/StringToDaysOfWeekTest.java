package by.tsyd.minsktrans.service.time;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

import static java.time.DayOfWeek.*;
import static org.testng.Assert.assertEquals;

/**
 * @author Dmitry Tsydzik
 * @since Date: 12.03.14.
 */
public class StringToDaysOfWeekTest {

    private StringToDaysOfWeek stringToDaysOfWeek = new StringToDaysOfWeek();

    @DataProvider(name = "data")
    public static Object[][] getData() {
        return new Object[][] {
                {"12345", Arrays.asList(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY)}
        };
    }

    @Test(dataProvider = "data")
    public void test(String input, List<DayOfWeek> expected) throws Exception {
        assertEquals(stringToDaysOfWeek.apply(input), expected);
    }
}

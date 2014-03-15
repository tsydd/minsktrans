package by.tsyd.minsktrans.csv.time;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.joining;
import static org.testng.Assert.assertEquals;

/**
 * @author Dmitry Tsydzik
 * @since Date: 24.02.14.
 */
public class DataUnpackingIteratorTest {

    @DataProvider(name = "data")
    public static Object[][] getData() {
        return new Object[][] {
                {"1", 1, "1"}
                , {"1", 0, "1"}
                , {"1", 2, "11"}
                , {"1,1,2", 2, "12"}
                , {"1,2,3", 3, "113"}
                , {"1,2,3", 4, "1133"}
                , {"1,2,3,2,5", 5, "11335"}
        };
    }

    @Test(dataProvider = "data")
    public void test(String data, int count, String result) throws Exception {
        Iterator<String> iterator = new DataUnpackingIterator(data, count);
        assertEquals(result, StreamSupport.stream(Spliterators.spliterator(iterator, count, 0), false)
                        .collect(joining()));
    }
}

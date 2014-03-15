package by.tsyd.minsktrans.csv.time;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        String[] resultArray = result.split("(?<=[^^])");
        assertThat(new DataUnpackingIterator(data, count)).containsExactly(resultArray);
    }
}

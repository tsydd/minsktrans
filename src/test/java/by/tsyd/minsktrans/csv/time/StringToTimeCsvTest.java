package by.tsyd.minsktrans.csv.time;

import by.tsyd.minsktrans.util.ScannerLineIterator;
import by.tsyd.minsktrans.util.StreamUtils;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;
import static org.testng.Assert.assertEquals;

/**
 * @author Dmitry Tsydzik
 * @since Date: 23.02.14.
 */
public class StringToTimeCsvTest {

    public static final String TIMES_TXT = "/times.txt";
    public static final String VALID_UNPACKED_TIMES = "/validUnpackedTimes.txt";

    @Test
    public void test() throws Exception {
        if (StringToTimeCsvTest.class.getResource(VALID_UNPACKED_TIMES) == null) {
            throw new SkipException(VALID_UNPACKED_TIMES + " is absent. Use unpackTimes.js to generate it");
        }
        Function<String, TimeCsv> stringToTimeCsv = new StringToTimeCsv();

        try (InputStream inputStream = getClass().getResourceAsStream(TIMES_TXT);
             Scanner scanner = new Scanner(inputStream);
             InputStream out = getClass().getResourceAsStream(VALID_UNPACKED_TIMES);
             Scanner outScanner = new Scanner(out)) {
            ScannerLineIterator lineIterator = new ScannerLineIterator(outScanner);
            StreamUtils.asStream(new ScannerLineIterator(scanner)).forEach(line ->
                            validateLineData(line, lineIterator.next(), stringToTimeCsv)
            );
        }
    }

    private void validateLineData(String line, String validDataLine, Function<String, TimeCsv> converter) {
        String[] validDataTokens = validDataLine.split(";", -1);

        TimeCsv timeCsv = converter.apply(line);
        assertEquals(timeCsv.getRouteId().toString(), validDataTokens[0]);

        List<Integer> validTimetable = intList(validDataTokens[2]);
        assertEquals(timeCsv.getTimeTable(), validTimetable, line);

        char[] chars = validDataTokens[3].toCharArray();
        Set<Integer> validZeroGroundIndexes = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                validZeroGroundIndexes.add(i);
            }
        }
        assertEquals(timeCsv.getZeroGrounds(), validZeroGroundIndexes);
        assertEquals(timeCsv.getValidFrom(), intList(validDataTokens[4]));
        assertEquals(timeCsv.getValidTo(), intList(validDataTokens[5]));
        assertEquals(timeCsv.getWorkDays(), Arrays.stream(validDataTokens[1].split(", "))
                .sequential()
                .collect(toList()));
    }

    private List<Integer> intList(String string) {
        return Arrays.stream(string.split(", "))
                .sequential()
                .map(Integer::valueOf)
                .collect(toList());
    }
}

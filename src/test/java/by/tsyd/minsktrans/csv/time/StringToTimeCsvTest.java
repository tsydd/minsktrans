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
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(timeCsv.getRouteId()).isEqualTo(Long.valueOf(validDataTokens[0]));

        List<Long> validTimetable = longList(validDataTokens[2]);
        assertThat(timeCsv.getTimeTable()).isEqualTo(validTimetable);

        char[] chars = validDataTokens[3].toCharArray();
        Set<Integer> validZeroGroundIndexes = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                validZeroGroundIndexes.add(i);
            }
        }
        assertThat(timeCsv.getZeroGrounds()).isEqualTo(validZeroGroundIndexes);
        assertThat(timeCsv.getValidFrom()).isEqualTo(longList(validDataTokens[4]));
        assertThat(timeCsv.getValidTo()).isEqualTo(longList(validDataTokens[5]));
        assertThat(timeCsv.getWorkDays()).containsExactly(validDataTokens[1].split(", "));
    }

    private List<Long> longList(String string) {
        return Arrays.stream(string.split(", "))
                .sequential()
                .map(Long::valueOf)
                .collect(toList());
    }
}

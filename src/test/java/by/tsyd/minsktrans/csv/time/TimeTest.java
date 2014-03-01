package by.tsyd.minsktrans.csv.time;

import by.tsyd.minsktrans.util.ScannerLineIterator;
import by.tsyd.minsktrans.util.StreamUtils;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;
import static org.testng.Assert.assertEquals;

/**
 * @author Dmitry Tsydzik
 * @since Date: 23.02.14.
 */
public class TimeTest {

    public static final String BLOCK_SEPARATOR = ",,";
    public static final int BLOCK_COUNT = 5;
    public static final String TIMES_TXT = "/times.txt";
    public static final String VALID_UNPACKED_TIMES = "/validUnpackedTimes.txt";

    @Test
    public void test() throws Exception {
        if (TimeTest.class.getResource(VALID_UNPACKED_TIMES) == null) {
            throw new SkipException(VALID_UNPACKED_TIMES + " is absent. Use unpackTimes.js to generate it");
        }

        try (InputStream inputStream = getClass().getResourceAsStream(TIMES_TXT);
             Scanner scanner = new Scanner(inputStream);
             InputStream out = getClass().getResourceAsStream(VALID_UNPACKED_TIMES);
             Scanner outScanner = new Scanner(out)) {
            ScannerLineIterator lineIterator = new ScannerLineIterator(outScanner);
            StreamUtils.asStream(new ScannerLineIterator(scanner)).forEach(line ->
                            validateLineData(line, lineIterator.next())
            );
        }
    }

    private void validateLineData(String line, String validDataLine) {
        String[] validDataTokens = validDataLine.split(";", -1);

        // splitting route id and other data
        int firstCommaIndex = line.indexOf(',');

        Long id = Long.valueOf(line.substring(0, firstCommaIndex));
        assertEquals(validDataTokens[0], id.toString());

        String dataString = line.substring(firstCommaIndex + 1);
        // splitting data to 5 blocks:
        // 1 - times data
        // 2 - valid from
        // 3 - valid to
        // 4 - days of week
        // 5 - intervals
        String[] blocks = dataString.split(BLOCK_SEPARATOR, BLOCK_COUNT);

        //parsing first block
        String[] timesData = blocks[0].split(DataUnpackingIterator.VALUE_SEPARATOR);
        int maxIndex = timesData.length;

        // time data
        List<Integer> timetable = getTimeTable(timesData, blocks[4]);
        List<Integer> validTimetable = intList(validDataTokens[2]);
        assertEquals(timetable, validTimetable, line);

        // zeroGrounds
        List<Integer> zeroGroundsIndexes = getZeroGroundsIndexes(timesData);
        char[] chars = validDataTokens[3].toCharArray();
        List<Integer> validZeroGroundIndexes = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                validZeroGroundIndexes.add(i);
            }
        }
        assertEquals(zeroGroundsIndexes, validZeroGroundIndexes, blocks[0]);

        // valid from
        List<Integer> validFrom = getValidDates(blocks[1], maxIndex);
        List<Integer> validValidFrom = intList(validDataTokens[4]);
        assertEquals(validFrom, validValidFrom);

        // valid to
        List<Integer> validTo = getValidDates(blocks[2], maxIndex);
        List<Integer> validValidTo = intList(validDataTokens[5]);
        assertEquals(validTo, validValidTo);

        // weekdays
        List<String> weekdays = StreamUtils.asStream(new DataUnpackingIterator(blocks[3], maxIndex), maxIndex)
                .collect(toList());
        List<String> validWeekdays = Arrays.stream(validDataTokens[1].split(", "))
                .sequential()
                .collect(toList());
        assertEquals(weekdays, validWeekdays);

    }

    private List<Integer> intList(String string) {
        return Arrays.stream(string.split(", "))
                .sequential()
                .map(Integer::valueOf)
                .collect(toList());
    }

    private List<Integer> getValidDates(String block, int maxIndex) {
        return StreamUtils.asStream(new DataUnpackingIterator(block, maxIndex), maxIndex)
                .map(Integer::valueOf)
                .collect(toList());
    }

    private List<Integer> getZeroGroundsIndexes(String[] timesData) {
        List<Integer> zeroGroundsIndexes = new ArrayList<>();
        for (int i = 0; i < timesData.length; i++) {
            String time = timesData[i];
            char tag = time.charAt(0);
            if ('+' == tag || ('-' == tag && '0' == time.charAt(1))) {
                zeroGroundsIndexes.add(i);
            }
        }
        return zeroGroundsIndexes;
    }

    private List<Integer> getTimeTable(String[] timesData, String intervals) {
        int timesDataLength = timesData.length;

        List<Integer> timetable = new ArrayList<>();
        int previousTime = 0;
        for (String token : timesData) {
            previousTime += Integer.valueOf(token);
            timetable.add(previousTime);
        }

        for (String intervalBlocks : intervals.split(BLOCK_SEPARATOR, -1)) {
            if (intervalBlocks.isEmpty()) {
                continue;
            }
            String[] deltas = intervalBlocks.split(",", -1);
            int delta = 0;
            int left = 0;
            for (int i = 0; i < deltas.length; i++) {
                if (left <= 0) {
                    delta = 5;
                    left = timesDataLength;
                }

                delta += Integer.valueOf(deltas[i++]) - 5;

                int repeatTimes = (i == deltas.length) ? left : Integer.valueOf(deltas[i]);
                left -= repeatTimes;

                for (int j = 0; j < repeatTimes; j++) {
                    timetable.add(timetable.get(timetable.size() - timesDataLength) + delta);
                }
            }
        }

        return timetable;
    }
}

package by.tsyd.minsktrans.csv.time;

import by.tsyd.minsktrans.util.StreamUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Tsydzik
 * @since Date: 01.03.14.
 */
public class StringToTimeCsv implements Function<String, TimeCsv> {

    public static final String BLOCK_SEPARATOR = ",,";
    public static final int BLOCK_COUNT = 5;

    @Override
    public TimeCsv apply(String line) {
        TimeCsv timeCsv = new TimeCsv();

        // splitting route id and other data
        int firstCommaIndex = line.indexOf(',');

        String routeIdString = line.substring(0, firstCommaIndex);
        timeCsv.setRouteId(Long.valueOf(routeIdString));

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

        timeCsv.setTimeTable(getTimeTable(timesData, blocks[4]));
        timeCsv.setZeroGrounds(getZeroGroundsIndexes(timesData));
        timeCsv.setValidFrom(getValidDates(blocks[1], maxIndex));
        timeCsv.setValidTo(getValidDates(blocks[2], maxIndex));
        timeCsv.setWorkDays(StreamUtils.asStream(new DataUnpackingIterator(blocks[3], maxIndex), maxIndex)
                .collect(toList()));
        return timeCsv;
    }

    private List<Long> getValidDates(String block, int maxIndex) {
        return StreamUtils.asStream(new DataUnpackingIterator(block, maxIndex), maxIndex)
                .map(Long::valueOf)
                .collect(toList());
    }

    private Set<Integer> getZeroGroundsIndexes(String[] timesData) {
        Set<Integer> zeroGroundsIndexes = new HashSet<>();
        for (int i = 0; i < timesData.length; i++) {
            String time = timesData[i];
            char tag = time.charAt(0);
            if ('+' == tag || ('-' == tag && '0' == time.charAt(1))) {
                zeroGroundsIndexes.add(i);
            }
        }
        return zeroGroundsIndexes;
    }

    private List<Long> getTimeTable(String[] timesData, String intervals) {
        int timesDataLength = timesData.length;

        List<Long> timetable = new ArrayList<>();
        long previousTime = 0;
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

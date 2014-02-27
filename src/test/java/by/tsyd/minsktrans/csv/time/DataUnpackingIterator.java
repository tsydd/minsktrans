package by.tsyd.minsktrans.csv.time;

import com.google.common.collect.AbstractIterator;

/**
* @author Dmitry Tsydzik
* @since Date: 24.02.14.
*/
class DataUnpackingIterator extends AbstractIterator<String> {

    private final String[] values;
    private final int[] times;
    private int arrayIndex;
    private int indexInArray;

    public DataUnpackingIterator(String dataString, int totalCount) {
        String[] tokens = dataString.split(TimeTest.VALUE_SEPARATOR);
        int size = tokens.length / 2 + 1;

        values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = tokens[i * 2];
        }

        times = new int[size];
        for (int i = 0; i < size - 1; i++) {
            times[i] = Integer.valueOf(tokens[i * 2 + 1]);
        }

        int sum = 0;
        for (int time : times) {
            sum += time;
        }

        times[size - 1] = totalCount - sum;
    }

    @Override
    protected String computeNext() {
        if (arrayIndex >= values.length) {
            return endOfData();
        }

        String result = values[arrayIndex];

        if (++indexInArray >= times[arrayIndex]) {
            arrayIndex++;
            indexInArray = 0;
        }

        return result;
    }
}

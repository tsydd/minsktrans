package by.tsyd.minsktrans.util;

import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Dmitry Tsydzik
 * @since Date: 27.02.14.
 */
public final class StreamUtils {
    private StreamUtils() {
    }

    public static <T> Stream<T> asStream(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
    }

    public static <T> Stream<T> asStream(Iterator<T> iterator, long size) {
        return StreamSupport.stream(Spliterators.spliterator(iterator, size, 0), false);
    }
}

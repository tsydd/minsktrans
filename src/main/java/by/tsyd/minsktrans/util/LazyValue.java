package by.tsyd.minsktrans.util;

import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 17.02.14.
 */
public class LazyValue<T> implements Supplier<T> {

    private volatile T value;
    private final Supplier<T> loader;

    public LazyValue(Supplier<T> loader) {
        this.loader = loader;
    }

    @Override
    public T get() {
        if (value == null) {
            synchronized (this) {
                if (value == null) {
                    value = loader.get();
                }
            }
        }
        return value;
    }
}

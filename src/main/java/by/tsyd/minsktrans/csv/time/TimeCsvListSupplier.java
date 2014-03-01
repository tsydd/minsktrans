package by.tsyd.minsktrans.csv.time;

import by.tsyd.minsktrans.util.ScannerLineIterator;
import by.tsyd.minsktrans.util.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Tsydzik
 * @since Date: 01.03.14.
 */
public class TimeCsvListSupplier implements Supplier<List<TimeCsv>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeCsvListSupplier.class);

    private final Supplier<InputStream> inputStreamSupplier;
    private final Function<String, TimeCsv> stringToTimeCsv = new StringToTimeCsv();

    public TimeCsvListSupplier(Supplier<InputStream> inputStreamSupplier) {
        this.inputStreamSupplier = inputStreamSupplier;
    }

    @Override
    public List<TimeCsv> get() {
        try (InputStream inputStream = inputStreamSupplier.get();
             Scanner scanner = new Scanner(inputStream)) {
            return StreamUtils.asStream(new ScannerLineIterator(scanner))
                    .map(stringToTimeCsv)
                    .collect(toList());
        } catch (IOException e) {
            LOGGER.error("failed to parse times", e);
            throw new RuntimeException(e);
        }
    }
}

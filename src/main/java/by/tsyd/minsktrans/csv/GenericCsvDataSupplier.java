package by.tsyd.minsktrans.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 20.02.14.
 */
public class GenericCsvDataSupplier<T> implements Supplier<List<T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericCsvDataSupplier.class);
    private static final String SEPARATOR = ";";

    private final Supplier<InputStream> inputStreamSupplier;
    private final Supplier<T> itemFactory;
    private final Function<T, Map<String, Consumer<String>>> headerToSetterForItem;

    public GenericCsvDataSupplier(Supplier<InputStream> inputStreamSupplier,
                                  Supplier<T> itemFactory,
                                  Function<T, Map<String, Consumer<String>>> headerToSetterForItem) {
        this.inputStreamSupplier = inputStreamSupplier;
        this.itemFactory = itemFactory;
        this.headerToSetterForItem = headerToSetterForItem;
    }

    private Map<String, Integer> buildHeaderToIndexMap(String headersLine) {
        return Arrays.stream(headersLine.split(SEPARATOR)).sequential()
                .collect((Supplier<Map<String, Integer>>) HashMap::new,
                        (map, header) -> map.put(header, map.size()),
                        Map::putAll);
    }

    @Override
    public List<T> get() {
        List<T> result = new LinkedList<>();
        try (InputStream inputStream = inputStreamSupplier.get();
             Scanner scanner = new Scanner(inputStream)) {
            Map<String, Integer> columnToIndex = buildHeaderToIndexMap(scanner.nextLine());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] values = line.split(SEPARATOR, -1);
                T item = itemFactory.get();

                headerToSetterForItem.apply(item).forEach((columnName, propertySetter) ->
                                propertySetter.accept(values[columnToIndex.get(columnName)])
                );

                result.add(item);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to read all the data", e);
            throw new RuntimeException(e);
        }
        return result;
    }

}

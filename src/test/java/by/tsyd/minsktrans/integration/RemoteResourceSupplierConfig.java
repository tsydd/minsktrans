package by.tsyd.minsktrans.integration;

import by.tsyd.minsktrans.MinskTransHttpConstants;
import by.tsyd.minsktrans.csv.HttpCsvInputStreamSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 14.03.14.
 */
@Configuration
public class RemoteResourceSupplierConfig {

    @Bean
    public Supplier<InputStream> timesInputStreamSupplier() {
        return new HttpCsvInputStreamSupplier(MinskTransHttpConstants.DEFAULT_TIMES_URL);
    }

    @Bean
    public Supplier<InputStream> routesInputStreamSupplier() {
        return new HttpCsvInputStreamSupplier(MinskTransHttpConstants.DEFAULT_ROUTES_URL);
    }

    @Bean
    public Supplier<InputStream> stopsInputStreamSupplier() {
        return new HttpCsvInputStreamSupplier(MinskTransHttpConstants.DEFAULT_STOPS_URL);
    }
}

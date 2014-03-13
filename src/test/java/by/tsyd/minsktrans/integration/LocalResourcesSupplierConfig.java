package by.tsyd.minsktrans.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 13.03.14.
 */
@Configuration
public class LocalResourcesSupplierConfig {

    @Bean
    public Supplier<InputStream> timesInputStreamSupplier() {
        return () -> getClass().getResourceAsStream("/times.txt");
    }

    @Bean
    public Supplier<InputStream> routesInputStreamSupplier() {
        return () -> getClass().getResourceAsStream("/routes.txt");
    }

    @Bean
    public Supplier<InputStream> stopsInputStreamSupplier() {
        return () -> getClass().getResourceAsStream("/stops.txt");
    }
}

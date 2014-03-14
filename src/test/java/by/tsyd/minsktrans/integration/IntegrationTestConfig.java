package by.tsyd.minsktrans.integration;

import by.tsyd.minsktrans.csv.route.RouteCsv;
import by.tsyd.minsktrans.csv.route.RouteCsvListSupplier;
import by.tsyd.minsktrans.csv.stop.StopCsv;
import by.tsyd.minsktrans.csv.stop.StopCsvListSupplier;
import by.tsyd.minsktrans.csv.time.TimeCsv;
import by.tsyd.minsktrans.csv.time.TimeCsvListSupplier;
import by.tsyd.minsktrans.domain.Route;
import by.tsyd.minsktrans.domain.RouteTimeConfig;
import by.tsyd.minsktrans.domain.Stop;
import by.tsyd.minsktrans.domain.TransportType;
import by.tsyd.minsktrans.service.route.CsvBasedRouteListSupplier;
import by.tsyd.minsktrans.service.route.index.RouteByIdIndex;
import by.tsyd.minsktrans.service.route.index.RoutesByStopIdIndex;
import by.tsyd.minsktrans.service.route.index.RoutesByTransportAndRouteNumberIndex;
import by.tsyd.minsktrans.service.route.index.RoutesByTransportIndex;
import by.tsyd.minsktrans.service.stop.CsvBasedStopListSupplier;
import by.tsyd.minsktrans.service.stop.index.StopByIdIndex;
import by.tsyd.minsktrans.service.stop.index.StopInRouteIndex;
import by.tsyd.minsktrans.service.time.CsvBasedRouteTimeConfigListSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.InputStream;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Dmitry Tsydzik
 * @since Date: 13.03.14.
 */
@Configuration
@Import(LocalResourcesSupplierConfig.class)
public class IntegrationTestConfig {

    @Autowired
    private Supplier<InputStream> stopsInputStreamSupplier;

    @Autowired
    private Supplier<InputStream> routesInputStreamSupplier;

    @Autowired
    private Supplier<InputStream> timesInputStreamSupplier;

    // CSV

    @Bean
    public Supplier<List<StopCsv>> stopCsvListSupplier() {
        return new StopCsvListSupplier(stopsInputStreamSupplier);
    }

    @Bean
    public Supplier<List<RouteCsv>> routeCsvListSupplier() {
        return new RouteCsvListSupplier(routesInputStreamSupplier);
    }

    @Bean
    public Supplier<List<TimeCsv>> timeCsvListSupplier() {
        return new TimeCsvListSupplier(timesInputStreamSupplier);
    }

    // Stop

    @Bean
    public Supplier<List<Stop>> stopListSupplier() {
        return new CsvBasedStopListSupplier(stopCsvListSupplier());
    }

    @Bean
    public Function<Long, Stop> stopByIdIndex() {
        return new StopByIdIndex(stopListSupplier());
    }

    // Routes

    @Bean
    public Supplier<List<Route>> routeListSupplier() {
        return new CsvBasedRouteListSupplier(routeCsvListSupplier(), stopByIdIndex());
    }

    @Bean
    public Function<Long, Route> routeByIdIndex() {
        return new RouteByIdIndex(routeListSupplier());
    }

    @Bean
    public Function<Long, List<Route>> routesByStopIdIndex() {
        return new RoutesByStopIdIndex(routeListSupplier());
    }

    @Bean
    public BiFunction<TransportType, String, List<Route>> routesByTransportAndRouteNumberIndex() {
        return new RoutesByTransportAndRouteNumberIndex(routeListSupplier());
    }

    @Bean
    public Function<TransportType, List<Route>> routesByTransportIndex() {
        return new RoutesByTransportIndex(routeListSupplier());
    }

    @Bean BiFunction<Long, Long, Integer> stopInRouteIndex() {
        return new StopInRouteIndex(routeListSupplier());
    }

    // Time

    @Bean
    public Supplier<List<RouteTimeConfig>> routeTimeConfigListSupplier() {
        return new CsvBasedRouteTimeConfigListSupplier(timeCsvListSupplier(), routeByIdIndex());
    }

}

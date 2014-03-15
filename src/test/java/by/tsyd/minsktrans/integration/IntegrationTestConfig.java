package by.tsyd.minsktrans.integration;

import by.tsyd.minsktrans.csv.route.RouteCsv;
import by.tsyd.minsktrans.csv.route.RouteCsvListSupplier;
import by.tsyd.minsktrans.csv.stop.StopCsv;
import by.tsyd.minsktrans.csv.stop.StopCsvListSupplier;
import by.tsyd.minsktrans.csv.time.TimeCsv;
import by.tsyd.minsktrans.csv.time.TimeCsvListSupplier;
import by.tsyd.minsktrans.domain.*;
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

import static java.util.stream.Collectors.toMap;

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

    // Time

    @Bean
    public Supplier<List<RouteConfigWithRouteId>> routeTimeConfigWithRouteIdListSupplier() {
        return new CsvBasedRouteTimeConfigListSupplier(timeCsvListSupplier());
    }

    @Bean
    public Function<Long, RouteTimeConfig> routeConfigByRouteId() {
        return routeTimeConfigWithRouteIdListSupplier().get().parallelStream()
                .collect(toMap(RouteConfigWithRouteId::getRouteId, RouteConfigWithRouteId::getConfig))
                ::get;
    }

    // Routes

    @Bean
    public Supplier<List<Route>> routeListSupplier() {
        return new CsvBasedRouteListSupplier(routeCsvListSupplier(), stopByIdIndex(), routeConfigByRouteId());
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
}

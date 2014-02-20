package by.tsyd.minsktrans.csv.route;

import by.tsyd.minsktrans.ResourceProvider;
import by.tsyd.minsktrans.csv.GenericCsvDataSupplier;
import com.google.common.collect.ImmutableMap;

import java.util.function.Consumer;

/**
 * @author Dmitry Tsydzik
 * @since Date: 15.02.14.
 */
public class RouteCsvListSupplier extends GenericCsvDataSupplier<RouteCsv> {

    public RouteCsvListSupplier(ResourceProvider resourceProvider) {
        super(resourceProvider,
                RouteCsv::new,
                routeCsv -> ImmutableMap.<String, Consumer<String>>builder()
                        .put("RouteNum", routeCsv::setRouteNumber)
                        .put("Authority", routeCsv::setAuthority)
                        .put("City", routeCsv::setCity)
                        .put("Transport", routeCsv::setTransport)
                        .put("Operator", routeCsv::setOperator)
                        .put("ValidityPeriods", routeCsv::setValidityPeriods)
                        .put("SpecialDates", routeCsv::setSpecialDates)
                        .put("RouteTag", routeCsv::setRouteTag)
                        .put("RouteType", routeCsv::setRouteType)
                        .put("Commercial", routeCsv::setCommercial)
                        .put("RouteName", routeCsv::setRouteName)
                        .put("Weekdays", routeCsv::setWeekDays)
                        .put("RouteID", routeCsv::setRouteId)
                        .put("Entry", routeCsv::setEntry)
                        .put("RouteStops", routeCsv::setRouteStops)
                        .put("Datestart", routeCsv::setDateStart)
                        .build()
        );
    }
}
